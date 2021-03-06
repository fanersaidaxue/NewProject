/**
 * 
 */
package com.uvsq.Jung;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import net.rootdev.jenajung.JenaJungGraph;
import net.rootdev.jenajung.Transformers;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.VCARD;
import com.uvsq.Interface.MainForm;
import com.uvsq.Model.TupleRDF;
import com.uvsq.SPARQL.SPARQL;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.algorithms.transformation.DirectionTransformer;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * @author Bruce GONG
 *
 */
public class JUNG {
	static String vcardUri="";	
	public static Graph<RDFNode, TupleRDF> graphGros = new SparseMultigraph<RDFNode, TupleRDF>();
	
	public static void createTreeCompl(Object[][] result, String title){
		if(result.length == 0)
		{
			return;
		}
		//get a internal connected network by organizing the existing RDFNodes. 
		List<TupleRDF> tsFinal = new ArrayList<TupleRDF>();
		List<TupleRDF> ts = convertirTuples(result); 
		tsFinal.addAll(ts);
		Model model = ModelFactory.createDefaultModel();
		//get a root
		TupleRDF root = null;
		for(int i = 0; i < ts.size(); i++){
			root = ts.get(i);
			RDFNode rootNode = model.createResource(root.getSujet().toString());
			//build a map
			for(TupleRDF t : ts)
			{
				RDFNode nodeTarget = model.createResource(t.getSujet().toString());
				List<TupleRDF> tsChemin = findShortestPath(rootNode, nodeTarget);
				tsFinal.addAll(tsChemin);
			}
		}
		//eliminate the duplications
		tsFinal = connectSimply(tsFinal);
		tsFinal = SPARQL.enrichGraph(tsFinal);
		tsFinal = eliminateDuplication(tsFinal);
		//create a model
		model = convertirModel(tsFinal);
		createGraph(model, title);
	}
	
	//find the shortest path on the network of RDFNode.
	public static List<TupleRDF> findShortestPath(RDFNode n1, RDFNode n2)
	{
		DijkstraShortestPath<RDFNode,TupleRDF> alg = new DijkstraShortestPath(JUNG.graphGros);
        List<TupleRDF> ts = alg.getPath(n1 , n2);
        return ts;
	}
	
	public static void createTree(Object[][] result, String title){
		if(result.length == 0)
		{
			return;
		}
		List<TupleRDF> ts = convertirTuples(result);
		ts = eliminateDuplication(ts);
		ts = connectSimply(ts);
		Model model = convertirModel(ts);
		createGraph(model, title);
	}
	
	//transformer Object[][] en List<TupleRDF>
	public static List<TupleRDF> convertirTuples(Object[][] result)
	{
		List<TupleRDF> ts = new ArrayList<TupleRDF>();
		for(Object[] ligne : result){
			TupleRDF t = new TupleRDF(ligne[0], ligne[1], ligne[2]);
			ts.add(t);
		}
		return ts;
	}

	//eliminate the duplications
	public static List<TupleRDF> eliminateDuplication(List<TupleRDF> ts)
	{
		Set<TupleRDF> noDup = new LinkedHashSet<TupleRDF>(ts);
		ts.clear();
		ts = new ArrayList<TupleRDF>(noDup);
		return ts;
	}

	//connect simply according to the texts.
	public static List<TupleRDF> connectSimply(List<TupleRDF> ts)
	{
		Hashtable htSub = new Hashtable();
		for(TupleRDF t : ts)
		{
			htSub.put(t.getSujet().toString(), t);
		}
		List<TupleRDF> tsClone = new ArrayList<TupleRDF>();
		tsClone.addAll(ts);
		for(TupleRDF t : tsClone)
		{
			if(htSub.containsKey(t.getLiteral().toString())){
				System.out.println("Build a simple connection in search result.");
				TupleRDF t1 = (TupleRDF) htSub.get(t.getLiteral().toString());
				t.setLiteral(t1);
				t.setHasSubNode(true);
				//ts.remove(t1);
				//System.out.println("TS size : " + ts.size());
			}
		}
		return ts;
	}
	
	//generate a model from List<TupleRDF>
	public static Model convertirModel(List<TupleRDF> ts)
	{
		Model model = ModelFactory.createDefaultModel();      
		for(TupleRDF t : ts)
		{
			createRes(model, t);
		}
		return model;
	}

	//creer la resource recursivement.
	public static Resource createRes(Model model, TupleRDF t)
	{
		Resource res = model.createResource(t.getSujet().toString());
		//System.out.println(t.toString());
		if(t.isHasSubNode())
		{
			Resource res1 = createRes(model, (TupleRDF)t.getLiteral());
			res.addProperty(model.createProperty(vcardUri, t.getPredicat().toString()), res1);
		}else{
			res.addProperty(model.createProperty(vcardUri, t.getPredicat().toString()), t.getLiteral().toString());
		}
		return res;
	}
	
	//create a graph by giving the model and title
	public static void createGraph(Model model, String title)
	{		
		int width = 1350;
        int height = 700;
        
        //Model model = null;		//FileManager.get().loadModel(resource);*-
        Graph<RDFNode, Statement> g = new JenaJungGraph(model);

        Layout<RDFNode, Statement> layout = new FRLayout(g);
        layout.setSize(new Dimension(width, height));
        VisualizationViewer<RDFNode, Statement> viz =
                new VisualizationViewer<RDFNode, Statement>(layout);
        viz.setPreferredSize(new Dimension(width, height));
        
        Transformer<RDFNode,Paint> vertexPaint = new Transformer<RDFNode,Paint>() {
        	public Paint transform(RDFNode i) {
        	return Color.GREEN;
        	}
        };  
        
        RenderContext context = viz.getRenderContext();
        context.setEdgeLabelTransformer(Transformers.EDGE); // property label
        context.setVertexLabelTransformer(Transformers.NODE); // node label
        //set colors of Vertex
        context.setVertexFillPaintTransformer(vertexPaint);
        //set the position of vertex's label.
        viz.getRenderer().getVertexLabelRenderer().setPosition(Position.S);
     // Create a graph mouse and add it to the visualization component
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        //gm.setMode(Mode.TRANSFORMING);
        gm.setMode(Mode.PICKING);
        viz.setGraphMouse(gm);
        
        JFrame app = new JFrame(title);
        app.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        app.getContentPane().add(viz);
        app.pack();
        app.setVisible(true);
	}
}

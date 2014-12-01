/**
 * 
 */
package com.uvsq.Model;

/**
 * @author Bruce GONG
 *
 */
public class TupleRDF {
	private Object Sujet;
	private Object Predicat;
	private Object Literal;
	private boolean hasSubNode;
	public TupleRDF(Object sujet, Object predicat, Object literal) {
		super();
		Sujet = sujet;
		Predicat = predicat;
		Literal = literal;
		this.hasSubNode = false;
	}
	/**
	 * @return the sujet
	 */
	public Object getSujet() {
		return Sujet;
	}
	/**
	 * @param sujet the sujet to set
	 */
	public void setSujet(Object sujet) {
		Sujet = sujet;
	}
	/**
	 * @return the predicat
	 */
	public Object getPredicat() {
		return Predicat;
	}
	/**
	 * @param predicat the predicat to set
	 */
	public void setPredicat(Object predicat) {
		Predicat = predicat;
	}
	/**
	 * @return the literal
	 */
	public Object getLiteral() {
		return Literal;
	}
	/**
	 * @param literal the literal to set
	 */
	public void setLiteral(Object literal) {
		Literal = literal;
	}
	/**
	 * @return the hasSubNode
	 */
	public boolean isHasSubNode() {
		return hasSubNode;
	}
	/**
	 * @param hasSubNode the hasSubNode to set
	 */
	public void setHasSubNode(boolean hasSubNode) {
		this.hasSubNode = hasSubNode;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TupleRDF [Sujet=" + Sujet + ", Predicat=" + Predicat + ", Literal=" + Literal + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public TupleRDF clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		TupleRDF t = new TupleRDF(this.Sujet,this.Predicat,this.Literal);
		return t;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int)this.Sujet.toString().hashCode()
				*this.Predicat.toString().hashCode()
				*this.Literal.toString().hashCode();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		TupleRDF other = (TupleRDF) obj;
		if(this.Sujet.toString().equals(other.Sujet.toString())
				//&& this.Predicat.toString().equals(other.Predicat.toString())
				&& this.Literal.toString().equals(other.Literal.toString())){
			//System.out.println("Find same.");
			return true;
		}else{
			return false;
		}
	}	
}

package fr.ufrv.interfaces;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author CHAABANI Oussama
 */
public class Interface_prin extends javax.swing.JFrame {


	private static final long serialVersionUID = 1L;
	/**
	 * Creates new form Interface_prin
	 */
	public Interface_prin() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		cmd_enter = new javax.swing.JButton();
		cmd_quit = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setLayout(null);

		cmd_enter.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
		cmd_enter.setText("Enter");
		cmd_enter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmd_enterActionPerformed(evt);
			}
		});
		jPanel1.add(cmd_enter);
		cmd_enter.setBounds(240, 320, 460, 60);

		cmd_quit.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
		cmd_quit.setText("Quit");
		cmd_quit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cmd_quitActionPerformed(evt);
			}
		});
		jPanel1.add(cmd_quit);
		cmd_quit.setBounds(240, 400, 460, 60);

		jSeparator1.setForeground(new java.awt.Color(0, 102, 102));
		jPanel1.add(jSeparator1);
		jSeparator1.setBounds(240, 390, 460, 20);

		jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/wallpaper65818_p.jpg"))); // NOI18N
		jPanel1.add(jLabel1);
		jLabel1.setBounds(0, 0, 940, 527);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
				);

		setSize(new java.awt.Dimension(956, 568));
		setLocationRelativeTo(null);
	}// </editor-fold>                        

	private void cmd_quitActionPerformed(java.awt.event.ActionEvent evt) {                                         
		int p= JOptionPane.showConfirmDialog(null, "Do you really want to quit??","Quit",JOptionPane.YES_NO_OPTION);
		if(p==0){
			System.exit(0);
		}
	}                                        

	private void cmd_enterActionPerformed(java.awt.event.ActionEvent evt) {                                          

		close();
		Interf_rdf r=new Interf_rdf();
		r.setVisible(true);
	}                                         

	public void close(){
		WindowEvent e=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(e);

	}
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Interface_prin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Interface_prin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Interface_prin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Interface_prin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Interface_prin().setVisible(true);
			}
		});
	}
	// Variables declaration - do not modify                     
	private javax.swing.JButton cmd_enter;
	private javax.swing.JButton cmd_quit;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JSeparator jSeparator1;
	// End of variables declaration                   
}
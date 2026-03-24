/**
 * 
 */
package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 */
public class login_view extends JFrame {
	private JTextField Usuario;
	private JTextField textField;

	public login_view (String titulo) {
		super(titulo);
		configInicial();
		inicializarComponentes();
	    }
	
	
	private void configInicial() {
		// Ventana se cierra con la x
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
						
		// AbsoluteLayout (ponemos los componentes donde nos dé la gana 
		getContentPane().setLayout(null);
		
		// Tamaño de la ventana 
		setSize(400,300);
			}
	

	private void inicializarComponentes() {
		// TODO Auto-generated method stub
		Usuario = new JTextField();
		Usuario.setToolTipText("Usuario ");
		Usuario.setBounds(48, 69, 299, 38);
		getContentPane().add(Usuario);
		Usuario.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(49, 33, 111, 24);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cotraseña");
		lblNewLabel_1.setBounds(48, 127, 104, 16);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setToolTipText("Usuario ");
		textField.setColumns(10);
		textField.setBounds(48, 153, 299, 38);
		getContentPane().add(textField);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(141, 214, 117, 29);
		getContentPane().add(btnNewButton);
		
	}
}
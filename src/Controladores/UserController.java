package Controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Usuarios;
import views.Login;
import views.MenuUsuario;

public class UserController implements ActionListener{
	private Login loginFrame;
	
	public UserController(Login loginFrame) {
		this.loginFrame = loginFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String nombre = loginFrame.getUsuario();
		String contrasena = loginFrame.getContrasena();
		
		if(Usuarios.validarUser(nombre, contrasena)) {
			MenuUsuario menuFrame = new MenuUsuario();
			menuFrame.setVisible(true);
			loginFrame.dispose();
		} else {
			JOptionPane.showMessageDialog(loginFrame, "usuario o contraseña no validos");
		}
	}
}

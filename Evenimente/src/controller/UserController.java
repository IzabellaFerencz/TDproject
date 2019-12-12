package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserController {
	@FXML
	private Button btnLogIn;
	@FXML
	private TextField txtUsername;
	@FXML
	private PasswordField txtPassword;

	@FXML
	void initialize() {
		System.out.println("Application is running!");
		txtUsername.setText("Introduceti username");
		txtPassword.setText("Introduceti password");

	}
}

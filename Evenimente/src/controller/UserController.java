package controller;

import java.io.BufferedWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.LoginModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import application.SocketClientCallable;

public class UserController 
{
	@FXML
	private Button btnLogIn;
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	
	private int port = 1345;
	
    @FXML 
    protected void handleSubmitButtonAction(ActionEvent event) 
    {
    	String username = usernameField.getText();
    	String password = passwordField.getText();
    	
    	try 
    	{

			ObjectMapper objectMapper = new ObjectMapper();

			LoginModel login = new LoginModel();
			login.setUsername(username);
			login.setPassword(password);
			
			String json = objectMapper.writeValueAsString(login);
			System.out.println(json);
			   
    		ExecutorService es = Executors.newCachedThreadPool();
    		SocketClientCallable cmd = new SocketClientCallable("localhost", port, "login", json);
    		String reply = cmd.call();

		} 
    	catch (Exception e) 
    	{
			e.printStackTrace();
		}
    }
}

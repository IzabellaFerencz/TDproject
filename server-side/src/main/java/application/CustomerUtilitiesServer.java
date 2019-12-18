package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import service.UserService;

public class CustomerUtilitiesServer implements Runnable
{
	private ServerSocket serverSocket;
	private UserService userService;

	public CustomerUtilitiesServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(250);
	}
	
	public void accept() throws IOException 
	{
		String reply = "";
		System.out.println("Accepting connections on port " + serverSocket.getLocalPort());
		
		while (!Thread.interrupted()) 
		{
			try (Socket socket = serverSocket.accept()) 
			{
				System.out.println("connection accepted");
				
				BufferedWriter bufferedOutputWriter = new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream()));
				
				BufferedReader bufferedInputReader = new BufferedReader(
						new InputStreamReader(socket.getInputStream(), "UTF-8"));
				
				String clientCommand = bufferedInputReader.readLine();
				String data_server = bufferedInputReader.readLine();
				System.out.println("Command received:  " + clientCommand);
				System.out.println("Data received:  " + data_server);

				boolean ok = false;

				switch (clientCommand) 
				{
					case "login":
						ObjectMapper mapper = new ObjectMapper();
						User loginModel = mapper.readValue(data_server, User.class);
						System.out.println("Login Model Username:  " + loginModel.getUsername());
						User user = userService.findUser(loginModel.getUsername());
						
						if (loginModel.getUsername().equals(loginModel.getUsername()))
						{
							System.out.println("Hello" + " " + loginModel.getUsername());
							Map<String, String> inputMap = new HashMap<String, String>();
							inputMap.put("username", loginModel.getUsername());
							inputMap.put("password", loginModel.getPassword());
							inputMap.put("nume", loginModel.getUsername());
							inputMap.put("email", loginModel.getEmail());
							String jsonResp = mapper.writeValueAsString(inputMap);
							mapper.writeValueAsString(jsonResp);
							bufferedOutputWriter.write(jsonResp);
							bufferedOutputWriter.flush();
							reply = "is ok";
							System.out.print(reply);
						} 
						else 
						{
							reply = "no user";
							System.out.print(reply);
						}

						ok = true;
						break;

					default:
						break;
				}

				if (ok == true)
				{
					bufferedOutputWriter.write("    " + reply);
					bufferedOutputWriter.newLine();
					bufferedOutputWriter.flush();
				}

			} 
			catch (SocketTimeoutException ste) 
			{
			}

		}
		System.out.println("Done accepting");
	}
	
	@Override
	public void run() 
	{
		try 
		{
			accept();
		} 
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
	}

}

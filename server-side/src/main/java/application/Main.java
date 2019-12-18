package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;

public class Main {

    private static int port = 1345;
    
    public static void main(String[] args) throws ClassNotFoundException 
    {

		ExecutorService exSrv = Executors.newCachedThreadPool();
		try 
		{
			CustomerUtilitiesServer server = new CustomerUtilitiesServer(port);
			exSrv.submit(server);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

    
}
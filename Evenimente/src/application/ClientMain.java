package application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClientMain {

	public static String serverResponse;

	public static void main(String[] args) {

		String user = "victor";
		String pass = "victor5556";

		String concat = user + " " + pass;

		ExecutorService es = Executors.newCachedThreadPool();

		System.out.println("Sending command to server");
		SocketClientCallable commandWithSocket = new SocketClientCallable(
				"localhost", 9001, "login", concat);

		Future<String> response = es.submit(commandWithSocket);
		try {
			serverResponse = response.get();

			System.out.println(serverResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (serverResponse.equals("fail")) {
			System.out.println("Fail login");

		}
	}
}

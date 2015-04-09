package br.ufc.quixada.concorrencia.test;

import java.io.IOException;

import br.ufc.quixada.concorrencia.client.Client;
import br.ufc.quixada.concorrencia.server.Server;

public class TestClient {
	 private static int timeOut = 10000; // mileseconds
	 private static int port = 8085;
	 private static String adress = "127.0.0.1";
	public static void main(String[] args) {
		try {
			//abre uma thread para o servidor
			Server server = new Server(port,timeOut);
			Thread serverThread = new Thread(server);
			serverThread.start();
			
			//abre uma thread para o client
			//let the games beginn
			Client client = new Client(adress, port);
			Thread clientThread = new Thread(client);
			clientThread.start();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package br.ufc.quixada.concorrencia.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


public class Server implements Runnable{
	ServerSocket serverSocket;
	volatile boolean keepProcessing = true;
	
	public Server(int port, int millisecondsTimeout) throws IOException{
		serverSocket = new ServerSocket(port);
		//serverSocket.setSoTimeout(millisecondsTimeout);
	}
	
	public void run(){
		System.out.println("Server starting");
		
		while(keepProcessing){
			try {
				System.out.println("accepting client...");
				Socket socket = serverSocket.accept();
				System.out.println("got client");
				process(socket);
			} catch (Exception e) {
				// TODO: handle exception
				handle(e);
			}
		}
	}
	
	public void handle(Exception e){
		if(!(e instanceof SocketException))
			e.printStackTrace();
	}
	
	public void stopProcess(){
		keepProcessing = false;
		closeIgnoringException(serverSocket);
	}
	
	void process(Socket socket){
		if(socket == null)
			return;
		try {
			String message = MessageUtils.getMessage(socket);
			System.out.println("Client says: " + message);
			Thread.sleep(1000);
			MessageUtils.sendMessage(socket, "Hi client");
			message = MessageUtils.getMessage(socket);
			System.out.println("Client says: " + message);
			Thread.sleep(1000);
			MessageUtils.sendMessage(socket, "Bye client!!");
			closeIgnoringException(socket);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void closeIgnoringException(Socket socket){
		if(socket != null){
			try {
				socket.close();
			} catch (IOException ignore) {
				// TODO: handle exception
			}
		}
	}
	
	private void closeIgnoringException(ServerSocket serverSocket){
		if(serverSocket != null){
			try {
				serverSocket.close();
			} catch (IOException ignore) {
				// TODO: handle exception
			}
		}
	}
}

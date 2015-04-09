package br.ufc.quixada.concorrencia.client;

import java.io.IOException;
import java.net.Socket;

import br.ufc.quixada.concorrencia.util.MessageUtils;

public class Client implements Runnable {
	private Socket socket;
	private String adress;
	private int port;
	public Client(String adress, int port) {
		
		try{
			this.adress = adress;
			this.port = port;
			socket = new Socket(adress, port);			
		}catch(Exception e){
			System.out.println("fudeu");
			e.printStackTrace();
		}
		
	}
	@Override
	public void run() {
		handShake();
		
	}
	
	public void handShake(){
		try {
			MessageUtils.sendMessage(socket, "Hi server");
			System.out.println("Server says: " + MessageUtils.getMessage(socket));
			Thread.sleep(1000);
			MessageUtils.sendMessage(socket, "Bye server!!");
			System.out.println("Server says: " + MessageUtils.getMessage(socket));
			socket.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
}

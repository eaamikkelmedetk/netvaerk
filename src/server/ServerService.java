package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import game2016.Player;

public class ServerService {
	private Socket selectedSocket;
	private PrintWriter out;

	public ServerService() {

	}
	
	public synchronized void setSocket(Socket selectedSocket) {
		this.selectedSocket = selectedSocket;
		try {
			out = new PrintWriter(selectedSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendDataToClient(String response) {
		out.write(response + "\n");
		out.flush();
	}
	
}

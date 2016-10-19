package server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import game2016.Player;

public class PlayerService extends Thread {
	private Socket s;
	private ServerService serverService;
	private Socket opponent;
	private Scanner in;
	private PrintWriter out;

	
	public PlayerService(Socket aSocket, ServerService serverService) throws IOException {
		this.s = aSocket;
		this.serverService = serverService;
		this.serverService = serverService;
		in = new Scanner(s.getInputStream());
		out = new PrintWriter(s.getOutputStream());
	}
	
	public void setOpponent(Socket opponent) {
		this.opponent = opponent;
	}

	public void run() {
		try {
			try {
				doService();
			} finally {
//				s.close();
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public void doService() throws IOException {
		while (true) {
			if (!in.hasNext()) {
				return;
			}
			String command = in.next();
			if (command.equals("QUIT")) {
				return;
			} else
				recieveFromClient(command);
		}
	}
	
	public void recieveFromClient(String command) throws IOException {
		System.out.println(command);
		if(command.equals("spawnServer")) {
			String newPlayerName = in.next();
			int xPos = in.nextInt();
			int yPos = in.nextInt();
			String direction = in.next();
			sendDataToOpponent("addOpponent " + newPlayerName + " " + xPos + " " +  yPos + " " + direction);
		} else if(command.equals("move")) {
			String playerName = in.next();
			int xPos = in.nextInt();
			int yPos = in.nextInt();
			String direction = in.next();
			sendDataToOpponent("move " + playerName + " " + xPos + " " + yPos + " " + direction);
		}
	}
	
	public void sendDataToClient(String response) {
		out.write(response + "\n");
		out.flush();
	}
	
	public void sendDataToOpponent(String response) {
		serverService.setSocket(opponent);
		serverService.sendDataToClient(response + "\n");
	}
	
	}


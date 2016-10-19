package game2016;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Timer;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventTarget;

public class RecieverAndSender extends Observable implements Runnable{
	
	private Socket s;
	private InputStream instream;
	private OutputStream outstream;
	private Scanner in;
	private PrintWriter out;
	private Player player;
	
	public RecieverAndSender(Player player) throws Exception {
		this.player = player;
		s = new Socket("localhost", 6789);
		System.out.println("Connection established");
		instream = s.getInputStream();
		outstream = s.getOutputStream();
		
		in = new Scanner(instream);
		out = new PrintWriter(outstream);
	}
	
	@Override
	public void run() {
				try {
					doService();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {					
					try {
//						s.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
				recieveFromServer(command);
		}
	}
	
	public void recieveFromServer(String command) throws IOException {
		System.out.println(command);
		if(command.equals("ready")) {
			sendCommandToServer("spawnServer " + player.getName() + " " + player.getXpos() + " " + player.getYpos() + " " + player.getDirection());
		} else if(command.equals("addOpponent")) {
			String playerName = in.next();
			int xPos = in.nextInt();
			int yPos = in.nextInt();
			String direction = in.next();
			setChanged();
			notifyObservers(new GameEvent("addOpponent", playerName, xPos, yPos, direction));
		} else if(command.equals("move")) {
			String playerName = in.next();
			int xPos = in.nextInt();
			int yPos = in.nextInt();
			String direction = in.next();
			setChanged();
			notifyObservers(new GameEvent("move", playerName, xPos, yPos, direction));
		}
	}
	
	public void sendCommandToServer(String response) {
		out.write(response + "\n");
		out.flush();
	}

}

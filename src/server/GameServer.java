package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class GameServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(6789);
		ServerService serverService = new ServerService();
		System.out.println("Waiting for clients to connect . . . ");
		
		Socket s1 = server.accept();
		System.out.println("Clients connected.");
		PlayerService servicep1 = new PlayerService(s1, serverService);
		Thread t1 = new Thread(servicep1);
		Socket s2 = server.accept();
		System.out.println("Clients connected.");
		PlayerService servicep2 = new PlayerService(s2, serverService);
		Thread t2 = new Thread(servicep2);
		servicep1.setOpponent(s2);
		servicep2.setOpponent(s1);
		t1.start();
		t2.start();
		servicep1.sendDataToOpponent("ready");
		servicep2.sendDataToOpponent("ready");
	}
}

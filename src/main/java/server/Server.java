/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;


public class Server extends Thread {
	private static final CopyOnWriteArrayList<Client> clients = new CopyOnWriteArrayList<>();

	private final Socket Soc;
	
	public Server(Socket Soc) {
	this.Soc = Soc;
	}
        public void update_groupes(String groupe,String[] users){
            for(String user :users){
                for(Client client:clients){
                    if (user.equals(client.id_user)){
                        client.pw.println("listen_groupe");                 
                        client.pw.println(groupe);
                    }
                }
            }
            
        }
        public void update_messages(String message,String[] users){
            for(String user :users){
                for(Client client:clients){
                    if (user.equals(client.id_user)){
                        client.pw.println("listen_message");                 
                        client.pw.println(message);
                    }
                }
            }
            
        }
	@Override
	public void run() {	
	try { 
        
	BufferedReader bf = new BufferedReader(new InputStreamReader(Soc.getInputStream()));

	PrintWriter pw = new PrintWriter(Soc.getOutputStream(), true);
        ServerHandle handle = new ServerHandle();
        while(true){
        String request=bf.readLine();
        if (request== null) continue;
            switch (request) {
                case "login" ->                     {
                        String username = bf.readLine();
                        String password = bf.readLine();
                        String id = handle.login_verif(username, password);
                        if(!id.equals("-1")){
                            clients.add(new Client(id,username,pw));
                        }           
                        pw.println(id);
                    }
                case "register" ->                     {
                        String username = bf.readLine();
                        String password = bf.readLine();
                        String id = handle.register_verif(username, password);
                        pw.println(id);
                    }
                case "create_reserv" ->                     {
                        String id_user = bf.readLine();
                        String date = bf.readLine();
                        String post = bf.readLine();
                        String username = handle.create_reserv(id_user, date,post);
                    }
                case "del_reserv" ->                     {
                        String id = bf.readLine();
                        String text = handle.del_reserv(id);
                        pw.println(text);
                    }
                case "get_reserv" ->                     {
                        String username=bf.readLine();
                        String reservs= handle.get_reserv(username); 
                        pw.println(reservs);
                    }
                case "get_posts" ->                     {
                     String date = bf.readLine();
                        String text = handle.get_posts(date);  
                        pw.println(text);
                    }
                 case "add_group" ->                     {
                        String Title = bf.readLine();
                        String ids = bf.readLine();
                        String id_groupe = handle.add_group(Title ,ids);
                        String[] members = ids.split(",");
                        for(String member:members){
                            handle.add_member(id_groupe ,member);
                        }
                        update_groupes(id_groupe+":"+Title,members);
                    }
                default -> pw.println("-1");
            }
        }

        }  
		catch (IOException bilal) {
	}
}
                
	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(5640);
		while(true) {
		Socket Soc = ss.accept();
		Server T = new Server(Soc);
		T.start();
            }
    }
}

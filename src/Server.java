import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private ServerSocket ServSock;

    Server() {
        try {
            
            ServSock = new ServerSocket(33333);
            System.out.println("Server running at port 33333");
            while (true) {

                ServerThread m = new ServerThread(ServSock.accept());
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public static void main(String args[]) throws UnknownHostException, IOException {
        Server objServer = new Server();
    }
}

class ServerThread implements Runnable {

    private Socket ClientSock;
    private Thread thr;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    static ServerThread[] st = new ServerThread[10];

   
    ServerThread(Socket client) {
        try {
            this.ClientSock = client;
            st[client_count] = this;
            client_count++;
            oos = new ObjectOutputStream(ClientSock.getOutputStream());
            ois = new ObjectInputStream(ClientSock.getInputStream());
            this.thr = new Thread(this);
//            //adding the current thread's name to arryList
//            threadNames.add(this.thr.getName());
//            //printing total clients number and names
//            System.out.println("Current online "+ client_count +"\nNames: " + threadNames +"\n");
//            //sending to all client that new client is added
//            for (int i = 0; i < client_count; i++) {
//                st[i].oos.writeObject(this.thr.getName()+" is added");
//            }
            
            thr.start();          
        } catch (Exception ex) {

        }
    }

    public void run() {
    	try {
    		String name =(String) ois.readObject();
    		this.thr.setName(name);
    		System.out.println("New client is added- "+ this.thr.getName());
    		
    		//to show all clients that new client is added (sent to all clients)
    		for (int i = 0; i < client_count; i++) {
                st[i].oos.writeObject(this.thr.getName()  +"  is added");

            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	

        while (true) {
            try {
                Object t = ois.readObject();
                if (t != null) {
                   System.out.println("From Client "+"("+(this.thr.getName())+"): "+t);
                }

                for (int i = 0; i < client_count; i++) {
                    st[i].oos.writeObject("From Server: "+"("+(this.thr.getName())+"): "+t);

                }
            } catch (Exception ex) {

            }

        }

    }
}

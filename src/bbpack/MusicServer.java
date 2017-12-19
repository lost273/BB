
package bbpack;
import java.io.*;
import java.net.*;
import java.util.*;

public class MusicServer {
    ArrayList<ObjectOutputStream> clientOutputStreams;
    public static void main(String[] args){
        new MusicServer().go();
    }
    public class ClientHandler implements Runnable{
        ObjectInputStream in;
        Socket clientSocket;
        
        public ClientHandler(Socket socket){
            try{
                clientSocket = socket;
                in = new ObjectInputStream(clientSocket.getInputStream());
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
        public void run(){
            Object o2 = null;
            Object o1 = null;
            try{
                while((o1 = in.readObject()) != null){
                    o2 = in.readObject();
                    System.out.println("read two objects");
                    tellEveryone(o1, o2);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

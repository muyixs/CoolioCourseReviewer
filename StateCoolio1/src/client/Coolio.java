package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

//import pages.MainStrategy;

public class Coolio {
	  public static int getPort(String[] args) {
           try {
        return (Integer.parseInt(args[0]));
           }
           catch (Exception e){
                System.out.println("Usage: CoolioClient <port_number> \n defaulting to port 7000   ");
               return 7000;
           }
    }
	public  static void main(String args[])  throws  Exception {
            
           
		try {
		InetAddress local = InetAddress.getLoopbackAddress();
		
		 Socket s = new Socket(local,getPort(  args));
		 
		 InputStream in = s.getInputStream();
		 
	     DataInputStream dis = new DataInputStream(in);
	     
	     
	      BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
	      DataOutputStream out = new DataOutputStream(s.getOutputStream());
	      
	      String fromServer;
	      while(!s.isClosed()){
                  //TODO
                  
	    	  fromServer = dis.readUTF();
	    	  System.out.println(fromServer);
                  
	    	  System.out.print(":: ");
                   if (!s.isClosed()){
                  String toServer = console.readLine();
                  //System.out.println(toServer);
	    	  out.writeUTF(toServer);}
	    	   
	    		  
	      }
	     
	}
        
        catch (Exception e) {
                System.exit(1);
            }
        }

}

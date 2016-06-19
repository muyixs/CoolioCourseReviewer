package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

 

public class ServerCommunications {
	
	ServerSocket listener;
	Socket client_socket;
	 int port;
	OutputStream toClient ;
	DataOutputStream dos;
	InputStream fromClient;
	DataInputStream dis;
	String response;
	String messageTo;
	    private static final ServerCommunications instance = new ServerCommunications( );
	 
 public ServerCommunications( ){
	 
	
 }
	public static ServerCommunications getInstance() {
		 
	    return ServerCommunications.instance;
	}
	
	public void setPort(int portIn) {
		this.port = portIn;
		
	}
public void createSocket( ) {
	 
	try {  
		listener = new ServerSocket(this.port);
		 client_socket = listener.accept();
		 toClient = client_socket.getOutputStream();
		 dos = new DataOutputStream(toClient);
		 
			
	   fromClient = client_socket.getInputStream();
		dis = new DataInputStream(fromClient);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

     
	
}


public void write(String message) {
try {
	dos.writeUTF(message);
	 response = "";
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

}
 

public String read() {
try {response = dis.readUTF();

return response;
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	return "Error";
}

}
 
public String getResponse(){
	
	return this.response;
	
}

public void setMessageTo(String pagePrint){
	
	this.messageTo = pagePrint;
	
}
	public void shutDown() {
            
            try {
                client_socket.close();
            } catch ( Exception ex) {
               
            }
            
        }
	
public void talk() {
   // String test = "From server ";
    
    
  
 
  try {
	 // dos.writeUTF("You Are Connected!!");
//	  ServerEngine engine = new ServerEngine();
//	  MainStrategy mainpage = new MainStrategy();
	
	  while(!(response += dis.readUTF()).equals("exit")){
		   dos.writeUTF(this.messageTo);
		   response = "";
	 
	   }
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   
  
	 
 
   	 
   	
}
 
}
	 
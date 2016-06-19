package state;

import java.io.File;
import server.ServerCommunications;

public class Context {

    private static Context context = new Context();  // singleton
    private State _state;
    private ServerCommunications _server;
    private String messageFromServer;
    private String messageFromClient;
    public final String myResources =  this.getClass().getClassLoader().getResource("").getPath()+File.separator+"resources";
    private Context() {}
    

    

    public static Context getInstance() {
        return context;
    }
    /* Other methods protected by singleton-ness */

   
    public void setState(State state) {
        this._state = state;
    }

    public State getState() {
        return this._state;
    }

    public void setServer(ServerCommunications newServer) {
        this._server = newServer;
    }

    public ServerCommunications getServer() {
        return this._server;
    }
    
    
    
    public void setMessageFromServer(String message) {
        this.messageFromServer = message;
    }
    
    public String getMessageFromServer() {
        return this.messageFromServer;
    }
    
    public void sendMessageToClient() {
       this.getServer().write(this.getMessageFromServer());
    }

    public void updateServerMessage() {
       this.getState().call();
    }
    
    public void receiveMessageFromClient() {
        this.getServer().read();
     this.messageFromClient = this.getServer().getResponse();
    }
    
    public void executeClientCommand() {
     this.getState().setState(this.messageFromClient);
    }
    
    public  void createResource() {
        File base = new File(myResources);
        File schools = new File(myResources+File.separator+"schools");
        File depaul = new File(myResources+File.separator+"schools"+File.separator+"DePaul University");
        File loyola = new File(myResources+File.separator+"schools"+File.separator+"Loyola University");
        File uc = new File(myResources+File.separator+"schools"+File.separator+"University of Chicago");
        File columbia = new File(myResources+File.separator+"schools"+File.separator+"Columbia University");
        File password = new File(myResources+File.separator+"password");
        if (!base.exists()) {
            
            base.mkdir();
            password.mkdir();
            schools.mkdir();
            depaul.mkdir();
            loyola.mkdir();
            uc.mkdir();
            columbia.mkdir();
            
        }
        
    }
    
}

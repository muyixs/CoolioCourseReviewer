package driver;

import server.ServerCommunications;
import state.Context;
import state.ExitState;
import state.MainPageState;

public class Coolio {

    public static void main(String[] args) {
        System.out.println("------------------------------------------------------------");
        System.out.println("CSC 376 COOLIO  ");
        System.out.println("------------------------------------------------------------");

        createServer(getPort(args));  //gets input data from user
        //  firstWords();

        //selectSort(hw5); // choose sort, calls the algorithm chosen by user and displays running time
        System.exit(0);

    }

    public static int getPort(String[] args) {
        try {
            return (Integer.parseInt(args[0]));
        } catch (Exception e) {
            System.out.println("Usage: CoolioClient <port_number> \n defaulting to port 7000   ");

            return 7000;
        }
    }

    public static void createServer(int port) {

        ServerCommunications server = new ServerCommunications();
        server.setPort(port);
        Context.getInstance().createResource();
        Context.getInstance().setServer(server);

        Context.getInstance().getServer().createSocket();
        firstWords();

    }

    public static void firstWords() {

        Context.getInstance().setState(new MainPageState());
        Context.getInstance().updateServerMessage();
        Context.getInstance().sendMessageToClient();
        Context.getInstance().receiveMessageFromClient();
        Context.getInstance().executeClientCommand();
        smallTalk();
    }

    public static void smallTalk() {

        while (!(Context.getInstance().getState() instanceof ExitState)) {

            Context.getInstance().updateServerMessage();
            Context.getInstance().sendMessageToClient();
            Context.getInstance().receiveMessageFromClient();
            Context.getInstance().executeClientCommand();

        }

        Context.getInstance().updateServerMessage();
        Context.getInstance().sendMessageToClient();
        Context.getInstance().executeClientCommand();

    }

}

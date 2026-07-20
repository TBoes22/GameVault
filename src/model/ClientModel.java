package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientModel {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private String username;
    private int playerId;
    private boolean loggedIn;

    public boolean connect(){
        try{
            socket = new Socket(InetAddress.getLocalHost(), 8080);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Successfully connected to Server!");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect(){
        try{
            if(socket != null && !socket.isClosed()){
                socket.close();
                System.out.println("Successfully disconnected from Server!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObjectOutputStream getOut(){
        return out;
    }
    public ObjectInputStream getIn() {
        return in;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}

package networks;
import database.GameHandler;
import database.PlayerHandler;
import database.ReviewHandler;
import database.WishlistHandler;
import security.LoginHandler;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8080;

    GameHandler gameHandler = new GameHandler();
    PlayerHandler playerHandler = new PlayerHandler();
    LoginHandler loginHandler = new LoginHandler();
    WishlistHandler wishlistHandler = new WishlistHandler();
    ReviewHandler reviewHandler = new ReviewHandler();

    public void start(){
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Server is running on " + PORT);
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Connection to new Client established!");
                ClientHandler handler = new ClientHandler(socket, gameHandler, playerHandler, loginHandler, wishlistHandler, reviewHandler);
                new Thread(handler).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Server().start();
    }
}

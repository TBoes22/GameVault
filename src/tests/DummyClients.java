package tests;
import networks.GameClient;

public class DummyClients {
    public static void main(String[] args){
        Thread client1 = new Thread(() -> {
            System.out.println("Starting Client 1!");
            new GameClient().start();
        });

        Thread client2 = new Thread(() -> {
            System.out.println("Starting Client 2!");
            new GameClient().start();
        });

        client1.start();
        client2.start();

        try{
            client1.join();
            client2.join();
            System.out.println("All Clients finished!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

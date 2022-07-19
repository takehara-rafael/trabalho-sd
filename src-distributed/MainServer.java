import Sockets.Server;


public class MainServer {
    public static void main(String[] args) {
        Server server = new Server();
        long start = System.nanoTime();
        server.ServerProcess();
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        server.ServerTerminate();
        System.out.print("Time elapsed in nanoseconds:");
        System.out.println(timeElapsed);
    }
}
import Sockets.Client;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client(args, 6789);
        for (int i = 0; i < 10000; i++) {
            client.ClientRequest();
        }
        client.ClientTerminate();
    }
}

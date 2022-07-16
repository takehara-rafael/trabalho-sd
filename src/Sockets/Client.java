package Sockets;

import java.io.IOException;
import java.net.*;

public class Client {
    DatagramSocket socket;
    InetAddress host;
    int port;
    byte[] m;

    public Client(String[] host, int port) {
        try {
            socket = new DatagramSocket();
            m = host[0].getBytes();
            this.host = InetAddress.getByName(host[1]);
            this.port = port;
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void ClientRequest() {
        try {
            DatagramPacket request = new DatagramPacket(m, m.length, host, port);
            socket.send(request);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ClientTerminate() {
        if(socket!=null) {
            socket.close();
        }
    }

}

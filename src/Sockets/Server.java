package Sockets;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    DatagramSocket socket;
    byte[] buffer;

    public Server() {
        try {
            socket = new DatagramSocket(6789);
            buffer = new byte[1000];
            ServerReceive();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket!= null) {
                socket.close();
            }
        }
    }

    public void ServerReceive() {
        try {
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                socket.send(reply);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package Sockets;

import java.io.*;
import java.net.*;

public class Client {
    DatagramSocket socket;
    InetAddress host;
    int port;
    byte[] m;
    int[][] matrixR;
    int[][] matrixG;
    int[][] matrixB;
    int size;
    int numLines;

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
            ByteArrayInputStream baos = new ByteArrayInputStream(buffer);
            ObjectInputStream oos = new ObjectInputStream(baos);
            matrixR = (int[][]) oos.readObject();

            socket.receive(reply);
            baos = new ByteArrayInputStream(buffer);
            oos = new ObjectInputStream(baos);
            matrixG = (int[][]) oos.readObject();

            socket.receive(reply);
            baos = new ByteArrayInputStream(buffer);
            oos = new ObjectInputStream(baos);
            matrixB = (int[][]) oos.readObject();

            socket.receive(reply);
            baos = new ByteArrayInputStream(buffer);
            oos = new ObjectInputStream(baos);
            size = (int) oos.readObject();

            socket.receive(reply);
            baos = new ByteArrayInputStream(buffer);
            oos = new ObjectInputStream(baos);
            numLines = (int) oos.readObject();

            ClientProcess();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void ClientProcess() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            DatagramPacket packet;

            oos.writeObject(calcMedian(matrixR, numLines, size));
            byte[] data = baos.toByteArray();
            packet = new DatagramPacket(data, data.length);
            DatagramPacket reply = new DatagramPacket(packet.getData(), packet.getLength(), host, socket.getPort());
            socket.send(reply);

            oos.writeObject(calcMedian(matrixG, numLines, size));
            data = baos.toByteArray();
            packet = new DatagramPacket(data, data.length);
            reply = new DatagramPacket(packet.getData(), packet.getLength(), host, socket.getPort());
            socket.send(reply);

            oos.writeObject(calcMedian(matrixB, numLines, size));
            data = baos.toByteArray();
            packet = new DatagramPacket(data, data.length);
            reply = new DatagramPacket(packet.getData(), packet.getLength(), host, socket.getPort());
            socket.send(reply);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ClientTerminate() {
        if(socket!=null) {
            socket.close();
        }
    }

    public int[][] calcMedian(int[][] matrix, int lin, int col) {
        for (int i = 1; i < lin-1; i++) {
            for (int j = 1; j < col-1; j++) {
                matrix[i][j] = (matrix[i][j] + matrix[i-1][j] + matrix[i+1][j] + matrix[i][j-1] + matrix[i][j+1])/5;
            }
        }
        return matrix;
    }
}

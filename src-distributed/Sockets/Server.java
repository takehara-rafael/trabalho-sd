package Sockets;

import Matrix.*;
import FileManager.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    DatagramSocket socket;
    byte[] buffer;
    int numClients;
    int numLines;
    Matrix matrix;
    File fileMatrix;
    DataManager manager;
    List<InetAddress> addressList;

    public Server() {
        try {
            socket = new DatagramSocket(6789);
            buffer = new byte[1000];
            numClients = 2;
            fileMatrix = new File("img.txt");
            manager = new DataManager(fileMatrix, matrix);
            addressList = new LinkedList<InetAddress>();
            numLines = matrix.getSize()/numClients;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ServerProcess() {
        try {
            int start = 0;
            int end = numLines;
            while(true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                if (!addressList.contains(request.getAddress())) {
                    addressList.add(request.getAddress());
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                DatagramPacket packet;
                for (int k = 0; k < 10000; k++) {
                    for (int i = 0; i < numClients; i++) {
                        oos.writeObject(matrix.getMatrixR(start, end, numLines));
                        byte[] data = baos.toByteArray();
                        packet = new DatagramPacket(data, data.length);
                        DatagramPacket reply = new DatagramPacket(packet.getData(), packet.getLength(), addressList.get(i), socket.getPort());
                        socket.send(reply);

                        oos.writeObject(matrix.getMatrixG(start, end, numLines));
                        data = baos.toByteArray();
                        packet = new DatagramPacket(data, data.length);
                        reply = new DatagramPacket(packet.getData(), packet.getLength(), addressList.get(i), socket.getPort());
                        socket.send(reply);

                        oos.writeObject(matrix.getMatrixB(start, end, numLines));
                        data = baos.toByteArray();
                        packet = new DatagramPacket(data, data.length);
                        reply = new DatagramPacket(packet.getData(), packet.getLength(), addressList.get(i), socket.getPort());
                        socket.send(reply);

                        oos.writeObject(matrix.getSize());
                        data = baos.toByteArray();
                        packet = new DatagramPacket(data, data.length);
                        reply = new DatagramPacket(packet.getData(), packet.getLength(), addressList.get(i), socket.getPort());
                        socket.send(reply);

                        oos.writeObject(numLines);
                        data = baos.toByteArray();
                        packet = new DatagramPacket(data, data.length);
                        reply = new DatagramPacket(packet.getData(), packet.getLength(), addressList.get(i), socket.getPort());
                        socket.send(reply);


                        socket.receive(reply);
                        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                        ObjectInputStream ois = new ObjectInputStream(bais);
                        int[][] matrixRec = (int[][]) ois.readObject();
                        for (int m = start; m<end; m++) {
                            for (int n = 0; n<matrix.getSize(); n++) {
                                matrix.setImageR(m, n, matrixRec[m-start][n]);
                            }
                        }

                        socket.receive(reply);
                        bais = new ByteArrayInputStream(buffer);
                        ois = new ObjectInputStream(bais);
                        matrixRec = (int[][]) ois.readObject();
                        for (int m = start; m<end; m++) {
                            for (int n = 0; n<matrix.getSize(); n++) {
                                matrix.setImageG(m, n, matrixRec[m-start][n]);
                            }
                        }

                        socket.receive(reply);
                        bais = new ByteArrayInputStream(buffer);
                        ois = new ObjectInputStream(bais);
                        matrixRec = (int[][]) ois.readObject();
                        for (int m = start; m<end; m++) {
                            for (int n = 0; n<matrix.getSize(); n++) {
                                matrix.setImageB(m, n, matrixRec[m-start][n]);
                            }
                        }

                        start = start + numLines;
                        end = numLines + numLines;
                    }
                    start = 0;
                    end = numLines;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void ServerTerminate() {
        try {
            manager.save(new File("output.txt"), matrix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

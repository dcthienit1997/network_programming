package practice5_01.task2;

import javax.xml.crypto.Data;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 1/4/2020.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ClientTest {
    public static void main(String[] args) throws IOException {
        InetAddress inetAddress = InetAddress.getByName("localhost");
        int port = 2000;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String line = console.readLine().trim();
                StringTokenizer stringTokenizer = new StringTokenizer(line);
                String command = stringTokenizer.nextToken();
                if (command.equalsIgnoreCase("quit")) break;
                switch (command) {
                    case "XOSO":
                        try {
                            String city = stringTokenizer.nextToken();
                            byte[] data = city.getBytes();

                            DatagramSocket socket = new DatagramSocket();
                            DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
                            socket.send(packet);
                            socket.close();
                        } catch (NoSuchElementException e) {
                            e.getCause();
                        }
                        break;
                    default:
                        break;
                }
                byte[] buffer = new byte[1024 * 10];
                DatagramPacket packetRECEIVE = new DatagramPacket(buffer, buffer.length);
                DatagramSocket socketRECEIVE = new DatagramSocket(port);
                socketRECEIVE.receive(packetRECEIVE);
                socketRECEIVE.close();
                System.out.println(new String(buffer));

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
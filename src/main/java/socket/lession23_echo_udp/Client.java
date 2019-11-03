package socket.lession23_echo_udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @project network-programming
 * @user DinhChiThien on 10/30/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Client {

    byte[] buffer;
    int length;
    InetAddress inetAddress;
    int port;

    public Client(byte[] buffer, int length, String hostName, int port) throws UnknownHostException {
        try {
            this.buffer = buffer;
            this.length = length;
            this.inetAddress = InetAddress.getByName(hostName);
            this.port = port;
        } catch (UnknownHostException e) {
            throw new UnknownHostException("The address is not exist");
        }
    }

    public void connect() {
        try {

            while (true) {

                // 1. Enter data from key board.
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "utf-8"));
                String line = br.readLine();

                // 2. Create DatagramPacket
                buffer = line.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);

                // 3. Create DatagramSocket to send data
                DatagramSocket datagramSocketSend = new DatagramSocket();

                // 4. Send packet
                datagramSocketSend.send(datagramPacket);
                datagramSocketSend.close();

                if (line.equalsIgnoreCase("exit")) {
                    break;
                }

                // 5. Create Processed DatagramPacket
                byte[] echoBuffer = new byte[65535];
                DatagramPacket echoDatagramPacket = new DatagramPacket(echoBuffer, echoBuffer.length);

                // 6. Create DatagramSocket to receive data
                DatagramSocket datagramSocketReceive = new DatagramSocket(port);
                datagramSocketReceive.receive(echoDatagramPacket);
                datagramSocketReceive.close();

                // 7. Print echo data
                String echoData = getData(echoBuffer);
                System.out.println(echoData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getData(byte[] echoBuffer) {
        if (echoBuffer == null) {
            return null;
        } else {
            String s = new String(echoBuffer);
            return s;
        }
    }
}

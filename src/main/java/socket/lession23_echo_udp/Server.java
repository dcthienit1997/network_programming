package socket.lession23_echo_udp;

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
public class Server {

    byte[] buffer;
    int length;
    InetAddress inetAddress;
    int port;

    public Server(byte[] buffer, int length, String hostName, int port) throws UnknownHostException {

        try {
            this.buffer = buffer;
            this.length = length;
            this.port = port;
            this.inetAddress = InetAddress.getByName(hostName);
        } catch (UnknownHostException e) {
            throw new UnknownHostException("The address is not exist");
        }
    }

    public void openConnect() {
        try {
            while (true) {
                // 1. Create DatagramPacket
                DatagramPacket datagramPacketReceive = new DatagramPacket(buffer, buffer.length);

                // 2. Create DatagramSocket
                DatagramSocket datagramSocketReceive = new DatagramSocket(port);

                // 3. Receive data
                datagramSocketReceive.receive(datagramPacketReceive);
                datagramSocketReceive.close();

                String data = getData(buffer);
                if (data.trim().equalsIgnoreCase("exit")) {
                    break;
                } else {
                    // 4. Insert "Echo" into data
                    data = "Echo " + data;
                    byte[] echoBuffer;
                    echoBuffer = data.getBytes();

                    // 5. Create DatagramPacket to send
                    DatagramPacket datagramPacketSend = new DatagramPacket(echoBuffer, echoBuffer.length, inetAddress, port);

                    // 6. Create DatagramSocket to send
                    DatagramSocket datagramSocketSend = new DatagramSocket();

                    // 7. Send data
                    datagramSocketSend.send(datagramPacketSend);
                    datagramSocketSend.close();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getData(byte[] buffer) {
        if (buffer == null) {
            return null;
        } else {
            String s = new String(buffer);
            return s;
        }
    }
}

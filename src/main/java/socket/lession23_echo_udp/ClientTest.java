package socket.lession23_echo_udp;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @project network-programming
 * @user DinhChiThien on 10/30/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ClientTest {
    public static void main(String[] args)  {
        byte[] buffer = new byte[1024];
        int length = 1024;
        String hostName = "localhost";
        int port = 555;
        try {
            Client client = new Client(buffer, length, hostName, port);
            client.connect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

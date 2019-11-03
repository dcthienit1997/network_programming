package socket.lession23_echo_udp;

/**
 * @project network-programming
 * @user DinhChiThien on 10/30/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ServerTest {
    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        int length = 1024;
        int port = 555;
        String hostName = "localhost";

        try {
            Server server = new Server(buffer, length, hostName, port);
            server.openConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

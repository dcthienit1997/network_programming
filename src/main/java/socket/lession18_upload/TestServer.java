package socket.lession18_upload;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @project network-programming
 * @user DinhChiThien on 10/21/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TestServer {

    public static void main(String[] args) throws IOException {
        int port = 13;
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        System.out.println("Server is connecting by port 13");
        server.start();
    }
}

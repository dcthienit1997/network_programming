package socket.lession20_net_calculator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @project network-programming
 * @user DinhChiThien on 10/24/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Server {
    ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void openConnect() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            TheConnect connect = new TheConnect(socket);
            connect.start();
        }
    }

}

package socket.lession19_download;

import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 10/21/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TestServer {
    public static void main(String[] args) throws IOException {
        int port = 7;
        Server server = new Server();
        server.openConnect(port);
    }
}

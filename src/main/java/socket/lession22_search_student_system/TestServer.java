package socket.lession22_search_student_system;

import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 11/11/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TestServer {
    public static void main(String[] args) throws IOException {
        int port = 23;
        Server server = new Server(port);
        server.openConnect();
    }
}

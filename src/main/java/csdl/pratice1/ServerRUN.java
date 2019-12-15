package csdl.pratice1;

import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 12/2/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ServerRUN {
    public static void main(String[] args) throws IOException {
        int port = 1234;
        Server server = new Server(port);
        server.openConnect();
    }
}

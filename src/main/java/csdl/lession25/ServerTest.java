package csdl.lession25;

import java.io.IOException;
import java.sql.*;

/**
 * @project network-programming
 * @user DinhChiThien on 11/25/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ServerTest {
    public static void main(String[] args) throws IOException {
        String url = "";
        int port = 1234;
        Server server = new Server(port);
        server.openConnect();
    }
}

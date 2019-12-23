package practice1;

import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 12/22/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ServerRUN {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.openConnect();
    }
}

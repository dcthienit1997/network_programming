package practice5_01.task2;

import java.net.UnknownHostException;

/**
 * @project network-programming
 * @user DinhChiThien on 1/4/2020.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ServerRUN {
    public static void main(String[] args) throws UnknownHostException {
        Server server = new Server();
        server.start();
    }
}

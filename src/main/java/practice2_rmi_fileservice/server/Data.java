package practice2_rmi_fileservice.server;

import java.util.ArrayList;

/**
 * @project network-programming
 * @user DinhChiThien on 12/26/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
class Data {

    ArrayList<User> users;

    Data() {
        users = new ArrayList<>();
        User user1 = new User("admin", "admin");
        User user2 = new User("admin", "0000");
        User user3 = new User("root", "root");
        User user4 = new User("root", "0000");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    boolean findUsername(String username) {
        for (User user : users) {
            if (user.username.equals(username)) return true;
        }
        return false;
    }

    public boolean checkPass(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}

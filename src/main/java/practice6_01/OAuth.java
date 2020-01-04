package practice6_01;

import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 1/5/2020.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class OAuth {

    User user;
    Dao dao;

    OAuth() throws IOException {
        dao = new Dao();
    }

    public boolean findUsername(String username) {
        if (dao.findUsername(username)) {
            user = new User(username, null);
            return true;
        } else {
            user = null;
            return false;
        }
    }

    public boolean checkPass(String password) {
        if (user == null) return false;
        user = dao.comparePassword(user.username, password);
        return true;
    }

    public void deposit(double money) {
        dao.deposit(user, money);
    }

    public boolean withdraw(double amount) {
        return dao.withdraw(user, amount);
    }

    public boolean transfer(String otherAccount, double amount) {
        return dao.transfer(user, amount, otherAccount);
    }

    public void logout() {
        user = null;
    }
}

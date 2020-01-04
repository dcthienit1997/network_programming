package practice6_01;

import org.apache.xmlbeans.impl.regex.REUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 1/5/2020.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Dao {

    String path = "D:\\workspace\\network-programming\\src\\main\\java\\practice6_01\\user.txt";
    ArrayList<User> users;

    public Dao() throws IOException {
        users = new ArrayList<>();
        napDuLieuBanDau();
    }

    public void napDuLieuBanDau() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(line, "\t");
            String username = stringTokenizer.nextToken();
            String password = stringTokenizer.nextToken();
            String bankAccount = stringTokenizer.nextToken();
            double amount = Double.parseDouble(stringTokenizer.nextToken());
            User user = new User(username, password, bankAccount, amount);
            this.users.add(user);
        }
    }

    public boolean findUsername(String username) {
        boolean exist = false;
        for (User user : users) {
            if (user.username.equals(username)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public User comparePassword(String username, String password) {
        User account = null;
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                account = user;
                break;
            }
        }
        return account;
    }

    public void deposit(User account, double money) {
        for (User user : users) {
            if (user.getUsername().equals(account.getUsername())) {
                user.setAmount(user.getAmount() + money);
                break;
            }
        }
    }

    public boolean withdraw(User account, double amount) {
        for (User user : users) {
            if (user.getUsername().equals(account.getUsername())) {
                double remain = user.getAmount();
                if ((remain - amount) >= 0) {
                    user.setAmount(remain - amount);
                    return true;
                } else return false;
            }
        }
        return false;
    }

    public boolean transfer(User account, double amount, String otherAccount) {
        // check other account
        if (findBankAccount(otherAccount)) {
            if (withdraw(account, amount)) {
                User otherUser = new User(otherAccount, null);
                deposit(otherUser, amount);
                System.out.println("true");
                return true;
            } else return false;
        } else return false;
    }

    private boolean findBankAccount(String otherAccount) {
        boolean exist = false;
        for (User user : users) {
            if (user.getBankAccount().equals(otherAccount)) {
                exist = true;
                break;
            }
        }
        return exist;
    }

}

package practice6_01;

/**
 * @project network-programming
 * @user DinhChiThien on 1/5/2020.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class User {
    String username;
    String password;
    String bankAccount;
    double amount;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String bankAccount, double amount) {
        this.username = username;
        this.password = password;
        this.bankAccount = bankAccount;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

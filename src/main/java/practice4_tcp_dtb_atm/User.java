package practice4_tcp_dtb_atm;

/**
 * @project network-programming
 * @user DinhChiThien on 1/2/2020.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class User {
    String uName;
    double uAmount;

    public User(String uName, double uAmount) {
        this.uName = uName;
        this.uAmount = uAmount;
    }

    @Override
    public String toString() {
        return "User{" +
                "uName='" + uName + '\'' +
                ", uAmount=" + uAmount +
                '}';
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public double getuAmount() {
        return uAmount;
    }

    public void setuAmount(double uAmount) {
        this.uAmount = uAmount;
    }
}

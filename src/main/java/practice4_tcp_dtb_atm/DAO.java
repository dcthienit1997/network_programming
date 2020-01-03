package practice4_tcp_dtb_atm;

import org.apache.xmlbeans.impl.regex.REUtil;

import java.sql.*;

/**
 * @project network-programming
 * @user DinhChiThien on 12/29/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class DAO {

    Connection connection;
    PreparedStatement preparedStatement;

    public DAO() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connection = DriverManager.getConnection("jdbc:odbc:BankSourceName");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    public boolean transfer(String thisName, double money, String thatName) throws SQLException {
        connection.setAutoCommit(false);
        try {
            if (withdraw(thisName, money) != 0) {
                if (deposit(thatName, money) != 0) {
                    connection.commit();
                    return true;
                }
            }
        } catch (Exception e) {
            connection.rollback();
            System.out.println(e.getMessage());
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    int deposit(String name, double parseDouble) throws SQLException {
        double remain = getBalance(name);
        if (remain != -1) {
            String statement = "UPDATE USER SET AMOUNT = ? WHERE NAME = ?";
            try {
                preparedStatement = connection.prepareStatement(statement);
                preparedStatement.setDouble(1, parseDouble + remain);
                preparedStatement.setString(2, name);
                return preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            } finally {
                preparedStatement.close();
            }
        } else return 0;
    }

    double getBalance(String name) throws SQLException {
        String statement = "SELECT * FROM USER WHERE NAME LIKE ?";
        preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        while (resultSet.next()) {
            String uName = resultSet.getString(2);
            double uAmount = resultSet.getDouble(3);
            user = new User(uName, uAmount);
        }

        resultSet.close();
        preparedStatement.close();

        // handle result
        if (user != null) {
            return user.getuAmount();
        } else return -1;
    }

    int withdraw(String name, double money) throws SQLException {
        double remain = getBalance(name);
        if ((remain - money) > 50) {
            String statement = "UPDATE USER SET AMOUNT = ? WHERE NAME = ?";
            try {
                preparedStatement = connection.prepareStatement(statement);
                preparedStatement.setDouble(1, remain - money);
                preparedStatement.setString(2, name);
                return preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            } finally {
                preparedStatement.close();
            }
        } else return 0;
    }

    public void close() throws SQLException {
        connection.close();
    }
}

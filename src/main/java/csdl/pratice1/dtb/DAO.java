package csdl.pratice1.dtb;

import csdl.pratice1.Customer;

import java.sql.*;

/**
 * @project network-programming
 * @user DinhChiThien on 12/2/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class DAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public DAO() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url = "jdbc:odbc:NetworkProgrammingSN";
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public double checkBalanceOfCustomer(String name) throws SQLException {
        String statement = "SELECT * FROM CUSTOMER WHERE name LIKE ?";
        preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer customer = null;
        while (resultSet.next()) {
            int cus_id = resultSet.getInt(1);
            String cus_name = resultSet.getString(2);
            double cus_balance = resultSet.getDouble(3);
            customer = new Customer(cus_id, cus_name, cus_balance);
        }
        resultSet.close();
        preparedStatement.close();
        return customer.getBalance();

    }

    public int deposit(String name, double amount) throws SQLException {
        double originMoney = checkBalanceOfCustomer(name);
        String statement = "UPDATE CUSTOMER SET balance = ? WHERE name = ?";
        preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(2, name);
        preparedStatement.setDouble(1, amount + originMoney);
        return preparedStatement.executeUpdate();
    }

    public int withdraw(String name, double amount) throws SQLException {
        double originMoney = checkBalanceOfCustomer(name);
        if ((originMoney - amount) >= 50.0) {
            String statement = "UPDATE CUSTOMER SET balance = ? WHERE name = ?";
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(1, originMoney - amount);
            return preparedStatement.executeUpdate();
        } else return 0;


    }

    public int transfer(String thisName, String thatName, double amount) throws SQLException {
        connection.setAutoCommit(false);
        double thisMoney = checkBalanceOfCustomer(thisName);
        int responseCode = 0;
        if ((thisMoney - amount) >= 50.0) {
            try {
                withdraw(thisName, amount);
                deposit(thatName, amount);
                connection.commit();
                responseCode = 1;
            } catch (Exception e) {
                connection.rollback();
                System.out.println(e.getMessage());
            }
        }
        connection.setAutoCommit(true);
        return responseCode;
    }
}

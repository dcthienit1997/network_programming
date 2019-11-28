package csdl.lession25;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @project network-programming
 * @user DinhChiThien on 11/28/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class DAO {
    private Connection connection;
    private PreparedStatement preparedStatement;

    DAO() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connection = DriverManager.getConnection("jdbc:odbc:NetworkProgrammingSN");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    List<Student> getStudents(String name) throws SQLException {
        List<Student> students = new ArrayList<>();
        String statement = "SELECT * FROM STUDENT WHERE student_name LIKE ?";
        preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setString(1, "%" + name + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int student_id = resultSet.getInt(1);
            String student_name = resultSet.getString(2);
            String student_address = resultSet.getString(3);
            int student_age = resultSet.getInt(4);
            Student student = new Student(student_id, student_name, student_address, student_age);
            students.add(student);
        }
        resultSet.close();
        preparedStatement.close();
        return students;
    }

    List<Student> getStudents(int age) throws SQLException {
        List<Student> students = new ArrayList<>();
        String statement = "SELECT * FROM STUDENT WHERE student_age <= ?";
        preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setInt(1, age);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int student_id = resultSet.getInt(1);
            String student_name = resultSet.getString(2);
            String student_address = resultSet.getString(3);
            int student_age = resultSet.getInt(4);
            Student student = new Student(student_id, student_name, student_address, student_age);
            students.add(student);
        }
        return students;
    }

}

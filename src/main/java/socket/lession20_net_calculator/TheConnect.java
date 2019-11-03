package socket.lession20_net_calculator;

import javax.activity.ActivityCompletedException;
import java.io.*;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * @project network-programming
 * @user DinhChiThien on 10/24/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class TheConnect extends Thread {
    BufferedReader netIn;
    PrintWriter netOut;
    char operator;
    double num1, num2;

    public TheConnect(Socket socket) throws IOException {
        netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        netOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        try {
            netOut.println("Welcome to Calculator !");
            String command;

            while (true) {

                // 1. read command from client
                netOut.print(">");
                command = netIn.readLine();
                if ("EXIT".equalsIgnoreCase(command.toUpperCase())) {
                    break;
                }

                // 2. analysis command
                try {
                    analysis(command);

                    // 3. calculating
                    double result = getResult();
                    if (Double.isInfinite(result)) {
                        throw new NumberFormatException("Value is infinite! Please check again...!");
                    }

                    // 4. send result
                    netOut.println(command + " = " + result);
                } catch (Exception e) {
                    netOut.println(e.getMessage());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private double getResult() {
        double result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                break;
        }
        return result;
    }

    private void analysis(String command) throws NumberFormatException, NoSuchElementException, ActivityCompletedException {
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(command, "+-*/");
            if (stringTokenizer.countTokens() > 2) {
                throw new ActivityCompletedException("105:   ...");
            }
            String snum1 = stringTokenizer.nextToken();
            String snum2 = stringTokenizer.nextToken();
            operator = command.charAt(snum1.length());
            num1 = Double.parseDouble(snum1);
            num2 = Double.parseDouble(snum2);

        } catch (NumberFormatException e) {
            throw new NumberFormatException("101: Operand is not number!");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("102: No such operand in...");
        }


    }

}

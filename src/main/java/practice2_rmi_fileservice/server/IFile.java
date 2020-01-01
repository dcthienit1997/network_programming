package practice2_rmi_fileservice.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @project network-programming
 * @user DinhChiThien on 12/25/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public interface IFile extends Remote {
    boolean findUsername(String username) throws RemoteException;

    String checkPass(String password) throws RemoteException;

    // GET
    void openSource(String uuid, String sf) throws RemoteException;

    byte[] readData(String uuid) throws RemoteException;

    void closeSource(String uuid) throws RemoteException;

    // SEND
    void createDestinationFile(String uuid, String df) throws RemoteException;

    void writeData(String uuid, byte[] buffer, int length) throws RemoteException;

    void closeDestination(String uuid) throws RemoteException;
}

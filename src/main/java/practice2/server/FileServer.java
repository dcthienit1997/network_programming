package practice2.server;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @project network-programming
 * @user DinhChiThien on 12/26/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class FileServer {
    public static void main(String[] args) throws RemoteException {
        IFile file = new FileImpl();
        Registry server = LocateRegistry.createRegistry(12345);
        server.rebind("file", file);
    }
}

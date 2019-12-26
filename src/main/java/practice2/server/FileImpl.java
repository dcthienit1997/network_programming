package practice2.server;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.UUID;

/**
 * @project network-programming
 * @user DinhChiThien on 12/25/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class FileImpl extends UnicastRemoteObject implements IFile {

    private static final long serialVersionUID = 1L;
    private Data data;
    private User user;
    private BufferedOutputStream bos;
    private HashMap<String, BufferedInputStream> threadsIn;
    private HashMap<String, BufferedOutputStream> threadsOut;

    protected FileImpl() throws RemoteException {
        super();
        data = new Data();
        user = null;
        threadsIn = new HashMap<>();
        threadsOut = new HashMap<>();
    }

    @Override
    public boolean findUsername(String username) throws RemoteException {
        if (data.findUsername(username)) {
            user = new User(username, null);
            return true;
        } else {
            user = null;
            return false;
        }
    }

    @Override
    public String checkPass(String password) throws RemoteException {
        if (user == null) {
            throw new RemoteException("Please enter your username account!");
        }
        if (data.checkPass(user.username, password)) {
            user.setPassword(password);
            return UUID.randomUUID().toString();
        } else {
            user = null;
            return null;
        }
    }

    @Override
    public void createDestinationFile(String uuid, String df) throws RemoteException {
        try {
            threadsOut.put(uuid, new BufferedOutputStream(new FileOutputStream(df)));
        } catch (FileNotFoundException e) {
            throw new RemoteException("Can't create destination file!");
        }
    }

    @Override
    public void writeData(String uuid, byte[] buffer, int length) throws RemoteException {
        try {
            threadsOut.get(uuid).write(buffer, 0, length);
            threadsOut.get(uuid).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeDestination(String uuid) throws RemoteException {
        try {
            threadsOut.get(uuid).flush();
            threadsOut.get(uuid).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openSource(String uuid, String sf) throws RemoteException {
        try {
            threadsIn.put(uuid, new BufferedInputStream(new FileInputStream(sf)));
        } catch (FileNotFoundException e) {
            throw new RemoteException("Can't open source file!");
        }

    }

    @Override
    public byte[] readData(String uuid) throws RemoteException {
        try {
            byte[] buffer = new byte[1024 * 10];
            int length = threadsIn.get(uuid).read(buffer);
            if (length == -1) return null;
            byte[] result = new byte[length];
            System.arraycopy(buffer, 0, result, 0, length);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void closeSource(String uuid) throws RemoteException {
        try {
            threadsIn.get(uuid).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

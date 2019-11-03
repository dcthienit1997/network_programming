package fileclass;

import java.io.File;
import java.util.Objects;

/**
 * @project network-programming
 * @user DinhChiThien on 10/24/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class ShowCapacityOfFolders {
    public static void main(String[] args) {
        String path = "E:\\Học ngoại ngữ";
        new ShowCapacityOfFolders().show(path);
    }

    private void show(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        } else {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                System.out.println(subFile.getName() + ":  " + capacities(subFile)/1024/1024/1024 + "GB");
            }
        }
    }

    private long capacities(File file) {
        long size = 0;
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (Objects.requireNonNull(subFiles).length != 0) {
                for (File subFile : Objects.requireNonNull(file.listFiles())) {
                    if (subFile.getName().equalsIgnoreCase("S-1-5-18")) {
                        break;
                    }
                    size += capacities(subFile);
                }
            }
        }
        if (file.isFile()) {
            size += file.length();
        }
        return size;
    }
}

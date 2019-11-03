package fileclass;

import java.io.File;
import java.util.Objects;

/**
 * @project network-programming
 * @user DinhChiThien on 9/18/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lesson1 {
    public boolean delete(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            return isDelete(file, true);
        }
        return false;
    }

    private boolean isDelete(File file, boolean deleted) {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isDirectory()) {
                if (!isDelete(subFile, true)) {
                    deleted = false;
                }
            }
            if (!subFile.delete()) {
                deleted = false;
            }
        }
        return deleted;
    }

    public boolean deleteExtend(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            return isDeleteExtend(file, true);
        }
        return false;
    }

    private boolean isDeleteExtend(File file, boolean deleted) {
        for (File subFile : Objects.requireNonNull(file.listFiles())) {
            if (subFile.isDirectory()) {
                if (!isDeleteExtend(subFile, true)) {
                    deleted = false;
                }
            } else if (!subFile.delete()) {
                deleted = false;
            }
        }
        return deleted;
    }

}

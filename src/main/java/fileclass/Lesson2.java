package fileclass;

import java.io.File;
import java.util.Objects;

/**
 * @project network-programming
 * @user DinhChiThien on 9/18/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lesson2 {
    public boolean findFirst(String path, String pattern) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        } else {
            return isContainPattern(file, pattern);
        }

    }

    private boolean isContainPattern(File file, String pattern) {

        if (file.getName().contains(pattern)) {
            System.out.println(file.getAbsolutePath());
            return true;
        }
        if (file.isDirectory()) {
            for (File subFile: Objects.requireNonNull(file.listFiles())) {
                if (isContainPattern(subFile, pattern)) {
                    return true;
                }
            }
        }
        return false;
    }
}

package io;

import java.io.File;
import java.util.Objects;

/**
 * @project network-programming
 * @user DinhChiThien on 9/23/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lession9 {

    public boolean folderCopy(String sFolder, String destFolder, boolean moved) {
        File source = new File(sFolder);
        File destination = new File(destFolder);
        if (!source.exists() || !source.isDirectory()) {
            return false;
        } else if (destination.exists() && !destination.isDirectory()) {
            return false;
        } else {
            if (copy(source, destination, moved)) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean copy(File source, File destination, boolean moved) {
        if (source.isDirectory()) {
            if (!destination.exists()) {
                if (!destination.mkdirs()) {
                    return false;
                }
            }
            for (File subSource : Objects.requireNonNull(source.listFiles())) {
                File subDestination = new File(destination, subSource.getName());
                if (!copy(subSource, subDestination, moved)) {
                    return false;
                }
                if (moved) {
                    source.delete();
                }
            }
            return true;
        } else {
            if (new Lession8().fileCopy(source.getPath(), destination.getParent(), moved)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        String path = "E:\\KLTN\\RAW_data\\OwnCollection\\non-vehicles\\Far";
        String path2 = "E:\\KLTN\\PROCESSED_data\\non-vehicles\\back\\middle";
        long starTime = System.currentTimeMillis();
        System.out.println(new Lession9().folderCopy(path, path2, true));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - starTime);
    }
}

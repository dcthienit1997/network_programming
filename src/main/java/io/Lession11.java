package io;

import java.io.File;

/**
 * @project network-programming
 * @user DinhChiThien on 9/30/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Lession11 {
    public static void main(String[] args) {
        String fname = "";
        new Lession11().fileType(fname);
    }

    private String fileType(String fname) {
        File source = new File(fname);
        if (!source.exists() || source.isDirectory()) {
            return "Not format";
        } else {
            
        }

        return "";
    }
}

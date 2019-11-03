package io.lession16;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @project network-programming
 * @user DinhChiThien on 10/14/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Student {
    String ID_ST;
    String NAME_ST;
    String CLASS_ST;
    double SCORE_ST;

    public Student() {
    }

    public Student(String ID_ST, String NAME_ST, String CLASS_ST, double SCORE_ST) {
        this.ID_ST = ID_ST;
        this.NAME_ST = NAME_ST;
        this.CLASS_ST = CLASS_ST;
        this.SCORE_ST = SCORE_ST;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "ID_ST='" + ID_ST + '\'' +
                ", NAME_ST='" + NAME_ST + '\'' +
                ", CLASS_ST='" + CLASS_ST + '\'' +
                ", SCORE_ST=" + SCORE_ST +
                '}';
    }

    public String getID_ST() {
        return ID_ST;
    }

    public void setID_ST(String ID_ST) {
        this.ID_ST = ID_ST;
    }

    public String getNAME_ST() {
        return NAME_ST;
    }

    public void setNAME_ST(String NAME_ST) {
        this.NAME_ST = NAME_ST;
    }

    public String getCLASS_ST() {
        return CLASS_ST;
    }

    public void setCLASS_ST(String CLASS_ST) {
        this.CLASS_ST = CLASS_ST;
    }

    public double getSCORE_ST() {
        return SCORE_ST;
    }

    public void setSCORE_ST(double SCORE_ST) {
        this.SCORE_ST = SCORE_ST;
    }

    public void write(BufferedWriter bufferedWriter, String delimiter) throws IOException {
        bufferedWriter.write(ID_ST + delimiter + NAME_ST + delimiter + CLASS_ST + delimiter + String.format("%.2f", SCORE_ST));
        bufferedWriter.newLine();
    }

    public void read(String data, String delimiter) {
        String[] values = data.split(delimiter);
        ID_ST = values[0];
        NAME_ST = values[1];
        CLASS_ST = values[2];
        SCORE_ST = Double.parseDouble(values[3]);
    }
    public String getHeader(String delimiter) {
        return "ID_ST" + delimiter + "NAME_ST" + delimiter + "CLASS_ST" + delimiter + "SCORE_ST";
    }

    public void writeCellValue(Row row) {
        Cell idCell = row.createCell(0);
        Cell nameCell = row.createCell(1);
        Cell classCell = row.createCell(2);
        Cell scoreCell = row.createCell(3);

        idCell.setCellValue(ID_ST);
        nameCell.setCellValue(NAME_ST);
        classCell.setCellValue(CLASS_ST);
        scoreCell.setCellValue(SCORE_ST);
    }

    public void readCellValue(Row row) {
        DataFormatter dataFormatter = new DataFormatter();
        ID_ST = dataFormatter.formatCellValue(row.getCell(0));
        NAME_ST = dataFormatter.formatCellValue(row.getCell(1));
        CLASS_ST = dataFormatter.formatCellValue(row.getCell(2));
        SCORE_ST = Double.parseDouble(dataFormatter.formatCellValue(row.getCell(3)));
    }
}

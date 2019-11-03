package io;

import java.io.*;

/**
 * @project network-programming
 * @user DinhChiThien on 10/7/2019.
 * @student_id 15130173
 * @email 15130173@st.hcmuaf.edu.vn
 **/
public class Student {
    String id;
    String name;
    Double score;

    public Student(String id, String name, Double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Student() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    protected void save(DataOutput dataOutput) throws IOException {

        dataOutput.writeUTF(id);
        dataOutput.writeUTF(name);
        dataOutput.writeDouble(score);
    }

    public void load(DataInputStream dis) throws IOException {
        id = dis.readUTF();
        name = dis.readUTF();
        score = dis.readDouble();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public void saveRAF(RandomAccessFile raf) throws IOException {
        raf.writeUTF(id);
        while (name.length() < 50) {
            name += " ";
        }
        raf.writeChars(name);
        raf.writeDouble(score);
    }

    public void loadRAF(RandomAccessFile raf) throws IOException {
        id = raf.readUTF();
        name = "";
        for (int i = 0; i < 50; i++) {
            name += raf.readChar();
        }
        name = name.trim();
        score = raf.readDouble();
    }

    public void write(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.write(id + "\t" + name + "\t" + String.format("%.2f", score));
        bufferedWriter.newLine();
    }

    public void read(String data) {
        String[] value = data.split("\t");
        id = value[0];
        name = value[1];
        score = Double.valueOf(value[2]);


    }
}

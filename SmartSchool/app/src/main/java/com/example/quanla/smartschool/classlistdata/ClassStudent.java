package com.example.quanla.smartschool.classlistdata;

import com.example.quanla.smartschool.studentdata.Student;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by DUC THANG on 3/10/2017.
 */

public class ClassStudent implements Serializable {

    @Override
    public String toString() {
        return "ClassStudent{" +
                "persongroupid='" + persongroupid + '\'' +
                ", name='" + name + '\'' +
                ", classRoom='" + classRoom + '\'' +
                ", students=" + students +
                '}';
    }

    @SerializedName("personGroupId")
    private String persongroupid;

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    @SerializedName("name")

    private String name;
    @SerializedName("userData")
    private String classRoom;
    private List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public ClassStudent(String persongroupid, String name, String userdata) {
        this.persongroupid = persongroupid;
        this.name = name;
        this.classRoom = userdata;

    }

    public ClassStudent(String name, String classRoom) {
        this.name = name;
        this.classRoom = classRoom;
        students = new Vector<>();
    }

    public void setPersongroupid(String persongroupid) {
        this.persongroupid = persongroupid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getPersongroupid() {

        return persongroupid;
    }

    public String getName() {
        return name;
    }

    public String getClassRoom() {
        return classRoom;
    }
}

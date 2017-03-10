package com.example.quanla.smartschool.database.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Vector;

/**
 * Created by tranh on 3/11/2017.
 */

public class ClassStudent {
    @SerializedName("personGroupId")
    private String persongroupid;

    public List<Student> getStudents() {
        return students;
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
        students=new Vector<>();

    }

    public ClassStudent(String name, String classRoom) {
        this.name = name;
        this.classRoom = classRoom;
        students=new Vector<>();
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

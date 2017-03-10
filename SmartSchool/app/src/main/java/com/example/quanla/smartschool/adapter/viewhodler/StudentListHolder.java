package com.example.quanla.smartschool.adapter.viewhodler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanla.smartschool.R;
import com.example.quanla.smartschool.database.model.Student;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tranh on 3/11/2017.
 */

public class StudentListHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_nameStudent)
    TextView tvName;
    @BindView(R.id.tv_idStudent)
    TextView tvIdStudent;
    @BindView(R.id.cb_checkin)
    CheckBox cbCheckin;
    @BindView(R.id.iv_student_img)
    ImageView ivStudent;
    public StudentListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void bind(Student student){
        tvName.setText(student.getName());
        tvIdStudent.setText(student.getIdStudent());
    }
}

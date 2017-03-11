package com.example.quanla.smartschool.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quanla.smartschool.R;
import com.example.quanla.smartschool.adapter.StudentListAdapter;
import com.example.quanla.smartschool.eventbus.GetDataFaildedEvent;
import com.example.quanla.smartschool.eventbus.GetDataSuccusEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentListActivity extends AppCompatActivity {
    private static final String TAG = StudentListActivity.class.toString();
    ProgressDialog progress;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.fab_check)
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        progress = ProgressDialog.show(this, "Loading",
                "Please waiting...", true);
        progress.show();
        addListener();
    }

    private void addListener() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudentListActivity.this,UploadActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onDataLoadComplete(GetDataSuccusEvent event){
        progress.dismiss();
        StudentListAdapter studentListAdapter=new StudentListAdapter();
        rv_list.setAdapter(studentListAdapter);
        rv_list.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv_list.addItemDecoration(dividerItemDecoration);
        Log.e(TAG, String.format("onDataLoadComplete: dsadksa") );
        EventBus.getDefault().removeStickyEvent(GetDataSuccusEvent.class);

    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void getDataFail(GetDataFaildedEvent event){
        progress.dismiss();
        EventBus.getDefault().removeStickyEvent(GetDataFaildedEvent.class);
        Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

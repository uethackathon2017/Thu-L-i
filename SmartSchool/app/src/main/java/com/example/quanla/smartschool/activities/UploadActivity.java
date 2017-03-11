package com.example.quanla.smartschool.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.quanla.smartschool.R;
import com.example.quanla.smartschool.database.request.UrlImage;
import com.example.quanla.smartschool.database.respon.FaceId;
import com.example.quanla.smartschool.database.respon.IndentifyRespon;
import com.example.quanla.smartschool.networks.NetContextMicrosoft;
import com.example.quanla.smartschool.networks.jsonModels.IndentifyBody;
import com.example.quanla.smartschool.networks.services.FaceService;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity {
    private static final String TAG = UploadActivity.class.toString();
    @BindView(R.id.btn_capture)
    Button btCapture;
    @BindView(R.id.btn_fromLocal)
    Button btFromLocal;
    @BindView(R.id.img_photo)
    ImageView ivPhoto;
    Map uploadResult;

    private static final int GALLERY_REQUEST = 1;
    private static final int REQUEST_TAKE_PHOTO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
        addListener();
    }
    String path;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            path = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bmp = BitmapFactory.decodeFile(path);
            ivPhoto.setImageBitmap(bmp);
        }else if (requestCode==REQUEST_TAKE_PHOTO&&resultCode==RESULT_OK){
             path="sdcard/camera_app/cam_image.jpg";
            Log.e(TAG, String.format("onActivityResult: %s", (new File(path)).getTotalSpace()) );
            ivPhoto.setImageDrawable(Drawable.createFromPath(path));
        }
        if(path!=null) {
            Retrievedata retrievedata = new Retrievedata();
            retrievedata.execute(path);
        }
    }

    private void addListener() {
        btFromLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, GALLERY_REQUEST);
            }
        });
        btCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            }
        });
    }
    public void getUrlImage(File file){
        Cloudinary cloudinary = new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", "dhtl",
                        "api_key", "767781774363334",
                        "api_secret", "AC5_uhn8LY2JaiWPeONIhz6ZLPg")
        );
        String s=cloudinary.url().imageTag(file.getName());
        Log.e(TAG, String.format("getUrlImage: %s", s) );
    }

    private File getFile() {
        File foder = new File("sdcard/camera_app");
        if (!foder.exists()) {
            foder.mkdir();
        }
        File file = new File(foder, "cam_image.jpg");
        return file;
    }

    public void setBtSummit(File file) {
        Cloudinary cloudinary = new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", "dhtl",
                        "api_key", "767781774363334",
                        "api_secret", "AC5_uhn8LY2JaiWPeONIhz6ZLPg")
        );
         uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            String url= (String) uploadResult.get("url");
            UrlImage urlImage=new UrlImage(url);
            FaceService faceService= NetContextMicrosoft.instance.create(FaceService.class);
            faceService.detectFace(urlImage).enqueue(new Callback<FaceId>() {
                @Override
                public void onResponse(Call<FaceId> call, Response<FaceId> response) {
                    identifyFace("1",response.body());
                }

                @Override
                public void onFailure(Call<FaceId> call, Throwable t) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void identifyFace(String idGroup,FaceId faceId){
        FaceService faceService=NetContextMicrosoft.instance.create(FaceService.class);
        faceService.identifyFace(new IndentifyBody(idGroup,faceId)).enqueue(new Callback<List<IndentifyRespon>>() {
            @Override
            public void onResponse(Call<List<IndentifyRespon>> call, Response<List<IndentifyRespon>> response) {
                Log.e(TAG, String.format("onResponse: %s", response.body().toString()) );
            }

            @Override
            public void onFailure(Call<List<IndentifyRespon>> call, Throwable t) {

            }
        });
    }

    class Retrievedata extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            setBtSummit(new File(params[0]));
            getUrlImage(new File(path));
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }
}
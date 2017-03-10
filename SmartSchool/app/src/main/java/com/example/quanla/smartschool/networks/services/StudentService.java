package com.example.quanla.smartschool.networks.services;

import com.example.quanla.smartschool.database.model.Student;
import com.example.quanla.smartschool.database.model.StudentRespon;
import com.example.quanla.smartschool.database.request.UrlImage;
import com.example.quanla.smartschool.database.respon.PersionFaceId;
import com.example.quanla.smartschool.database.respon.PersionId;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tranh on 3/11/2017.
 */

public interface StudentService {
    @POST("{personGroupId}/persons")
    Call<PersionId> createPersion(@Path("personGroupId") String personGroupId, Student student);

    @POST("{personGroupId}/persons/{personId}/persistedFaces")
    Call<PersionFaceId> addPersionFace(@Path("personGroupId") String personGroupId, @Path("personId") String personId, @Body UrlImage url);

    @GET("{personGroupId}/persons")
    Call<List<StudentRespon>> getAllPersonInGroup(@Path("personGroupId") String personGroupId);
}

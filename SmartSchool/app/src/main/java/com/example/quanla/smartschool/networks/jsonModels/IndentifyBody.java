package com.example.quanla.smartschool.networks.jsonModels;

import com.example.quanla.smartschool.database.respon.FaceId;
import com.example.quanla.smartschool.database.respon.PersionFaceId;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranh on 3/11/2017.
 */

public class IndentifyBody {

    @SerializedName("personGroupId")
    public String persongroupid;
    @SerializedName("faceIds")
    public FaceId faceid;
    @SerializedName("maxNumOfCandidatesReturned")
    public int maxnumofcandidatesreturned;
    @SerializedName("confidenceThreshold")
    public double confidencethreshold;

    public IndentifyBody(String persongroupid, FaceId faceid) {
        this.persongroupid = persongroupid;
        this.faceid = faceid;
        maxnumofcandidatesreturned=1;
        confidencethreshold=0.5;
    }

    public void setPersongroupid(String persongroupid) {
        this.persongroupid = persongroupid;
    }


    public void setMaxnumofcandidatesreturned(int maxnumofcandidatesreturned) {
        this.maxnumofcandidatesreturned = maxnumofcandidatesreturned;
    }

    public void setConfidencethreshold(double confidencethreshold) {
        this.confidencethreshold = confidencethreshold;
    }
}

package com.example.quanla.smartschool.database.respon;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tranh on 3/11/2017.
 */

public class IndentifyRespon {

    @SerializedName("faceId")
    private String faceid;
    @SerializedName("candidates")
    private List<Candidates> candidates;

    private   class Candidates {
        @SerializedName("personId")
        private String personid;
        @SerializedName("confidence")
        private double confidence;

        public String getPersonid() {
            return personid;
        }

        public double getConfidence() {
            return confidence;
        }

        public Candidates(String personid, double confidence) {

            this.personid = personid;
            this.confidence = confidence;
        }
    }

    public String getFaceid() {
        return faceid;
    }

    public List<Candidates> getCandidates() {
        return candidates;
    }

    public IndentifyRespon(String faceid, List<Candidates> candidates) {

        this.faceid = faceid;
        this.candidates = candidates;
    }
}

package android.example.homecinema.network;

import android.example.homecinema.data.Review;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {


    @SerializedName("results")
    List<Review> rResult ;
    public List<Review> getResult() {
        return rResult;
    }
}

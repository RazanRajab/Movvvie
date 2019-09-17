package android.example.homecinema.network;

import android.example.homecinema.data.Trailer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailerResponse {

    @SerializedName("results")
    List<Trailer> uResult ;

    public List<Trailer> getuResult() {
        return uResult;
    }

}

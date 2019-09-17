package android.example.homecinema.network;

import android.example.homecinema.data.Trailer;
import android.example.homecinema.network.ReviewResponse;
import android.example.homecinema.network.TrailerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@SuppressWarnings("ALL")

public interface TrailerService {

    @GET("movie/{id}/reviews")
    Call<ReviewResponse> getReviews(@Path("id") int movieId, @Query("api_key") String token);
    @GET("movie/{id}/videos")
    Call<TrailerResponse> getTrailers(@Path("id") int movieId, @Query("api_key") String token);


   /* @GET("movie/{id}/reviews")   //whats behind the domain name
    Call <ReviewResponse> getReviews (@Query("id") int movieId,  @Query("api_ley")String token);
    @GET("movie/{id}/reviews")   //whats behind the domain name
    Call <TrailerResponse> getTrailers (@Query("id") int movieId, @Query("api_ley")String token);
*/

}

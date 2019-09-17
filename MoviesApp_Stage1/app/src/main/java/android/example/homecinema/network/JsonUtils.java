package android.example.homecinema.network;

import android.example.homecinema.data.Movie;
import android.example.homecinema.data.Movies;
import android.util.Log;
import androidx.annotation.NonNull;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static JSONObject response= null;
    private static JSONArray movies = null;
    private static List<Movie> movies_list;

    //parse response
    public static Movies parseJson(String json)  {
        try{
            response = new JSONObject(json);
            movies = response.getJSONArray("results");
            movies_list = parseList(movies);
            //add the list of movies
            Movies movies = new Movies();
            movies.setMovies(movies_list);
            return movies;
        }
        catch(Exception exception){
            return null;
        }

    }

    //add to list
    @NonNull
    private static List<Movie> parseList(JSONArray array) {
        List<Movie> list = new ArrayList<>();
        Movie temp;
        for (int i = 0; i < array.length(); i++) {
            try{
                JSONObject movie = array.getJSONObject(i);
                temp = parseMovie(movie);
            }
            catch(Exception exception){
                return null;
            }
            list.add(temp);
        }//end for
        return list;
    }

    //parse the item
    @NonNull
    private static Movie parseMovie(JSONObject object) {
        Movie picked = new Movie();
        try{

            //addd the id today
            picked.setId(object.getInt("id"));
            picked.setPoster(object.getString("poster_path"));
            picked.setPlot(object.getString("overview"));
            picked.setDate(object.getString("release_date"));
            picked.setTitle(object.getString("title"));
            picked.setRating(object.getString("vote_average"));
        }
        catch(Exception exception){
            Log.e("json", exception.getMessage());
            return null;
        }
        return picked;
    }



}// end class

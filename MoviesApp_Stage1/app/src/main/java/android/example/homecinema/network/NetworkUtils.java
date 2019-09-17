package android.example.homecinema.network;

import android.net.Uri;
import android.util.Log;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public static URL buildUrl(String key, String choice) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority("api.themoviedb.org").appendPath("3").appendPath("movie").appendPath(choice).appendQueryParameter("api_key", key);
        URL url = null;
        try {
            url = new URL(builder.build().toString());
        }
        catch (Exception exception) {
            return null;
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            }
            else {
                return null;
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.d("con", e.getMessage()); 
            return null;
        }
    }


    public static URL buildReviewUrl(String key, String id) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority("api.themoviedb.org").appendPath("3").appendPath("movie").appendPath(id).appendPath("reviews").appendQueryParameter("api_key", key);
        URL url = null;
        try {
            url = new URL(builder.build().toString());
        }
        catch (Exception exception) {
            return  null;
        }
        return url;
    }


    public static URL buildTrailerUrl(String key, String id) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").authority("api.themoviedb.org").appendPath("3").appendPath("movie").appendPath(id).appendPath("videos").appendQueryParameter("api_key", key);
        URL url = null;
        try {
            url = new URL(builder.build().toString());
        }
        catch (Exception exception) {
            return  null;
        }
        return url;
    }


    public static String buildYTUrl(String key) {
        return "https://www.youtube.com/watch?v=" + key;
    }





}// end class

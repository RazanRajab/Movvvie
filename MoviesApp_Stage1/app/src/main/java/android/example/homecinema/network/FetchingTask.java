package android.example.homecinema.network;

import android.example.homecinema.data.Movie;
import android.example.homecinema.data.Movies;
import android.example.homecinema.data.RVAdapter;
import android.os.AsyncTask;
import android.util.Log;
import java.net.URL;
import java.util.List;

   public class FetchingTask extends AsyncTask<String, Void, List<Movie>> {

        public static final String KEY = "a793ce5a8e7d8d881703b3ea83aac969";
        private RVAdapter rvadapter;

        public FetchingTask(RVAdapter adapter) {
            this.rvadapter = adapter;
        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            URL url = null;

            //pass the sorting method
            try {
                 url = NetworkUtils.buildUrl(KEY, params[0]);
            }
            catch(Exception ex){
                Log.d("url", ex.getMessage());
            }
            String response;
            try {
                response = NetworkUtils.getResponseFromHttpUrl(url);
                Movies movies = JsonUtils.parseJson(response);
                return movies.getMovies();
            }
            catch (Exception e) {
                Log.d("ee", e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            //only on exe
            if (movies != null) {
                rvadapter.setList(movies);
                Log.d("post", "done");
            }
        }

    }// end class

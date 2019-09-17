package android.example.homecinema.data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.example.homecinema.database.Model;
import android.example.homecinema.network.NetworkUtils;
import android.example.homecinema.R;
import android.example.homecinema.network.ReviewResponse;
import android.example.homecinema.network.TrailerResponse;
import android.example.homecinema.network.TrailerService;
import android.example.homecinema.database.CreateResponeDataBase;
import android.example.homecinema.database.Executors;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailsForMovie extends AppCompatActivity {

    private ImageView poster;
    private TextView title;
    private TextView date;
    private TextView plot;
    private TextView rating;
    private ImageView add_to_fav;
    static private LinearLayout reviews_list;
    static private LinearLayout trailers_list;
    private Movie movie;
    private boolean found ;
    private int id;
    private List<Trailer> trailers_list_values;
    private List<Review> reviews_list_values;
    private Model model;
    private LiveData<Movie> m;

    private CreateResponeDataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_for_movie);

        poster = findViewById(R.id.poster);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        plot = findViewById(R.id.plot);
        rating = findViewById(R.id.rating);
        add_to_fav = findViewById(R.id.addtofav);
        reviews_list = findViewById(R.id.reviews_list);
        trailers_list = findViewById(R.id.trailers_list);

        dataBase = CreateResponeDataBase.getInstance(DetailsForMovie.this);

        //show details for a movie
        final Bundle data = getIntent().getExtras();
        movie = data.getParcelable("picked");
        String full_poster_path = "http://image.tmdb.org/t/p/w500/" + movie.getPoster();
        Picasso.with(poster.getContext())
                .load(full_poster_path).placeholder(R.mipmap.ic_launcher).into(poster);
        try {
            id = movie.getId();
            title.setText(movie.getTitle());
            date.setText(movie.getDate());
            plot.setText(movie.getPlot());
            rating.setText(movie.getRating());

            //error sol: add declare object model today
         /*   model = new Model();
            model.setPoster(movie.getPoster());
            ///
            model.setId(movie.getId());
            model.setTitle(movie.getTitle());
            model.setDate(movie.getDate());
            model.setPlot(movie.getPlot());
            model.setRating(movie.getRating());*/
        } catch (Exception exception) {
            Toast.makeText(getApplicationContext(), "Error in loading data", Toast.LENGTH_SHORT).show();
        }

        if (found) {
            add_to_fav.setImageResource(R.drawable.filled);
        }

        add_to_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // updated today
                Executors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (found) {    //delete
                            dataBase.getData().delete(movie);

                        } else {
                            //insert
                            dataBase.getData().addMovie(movie);
                        }
                    }
                });


                if (found) {
                    add_to_fav.setImageResource(R.drawable.empty);
                    Toast.makeText(DetailsForMovie.this, movie.getTitle() + " Delete From Favorite",
                            Toast.LENGTH_SHORT).show();
                } else {
                    add_to_fav.setImageResource(R.drawable.filled);
                    Toast.makeText(DetailsForMovie.this, movie.getTitle() + " ADD to Favorite",
                            Toast.LENGTH_SHORT).show();
                }
            }//on click
        });

        //add trailers and reviews based on id
        fetchTrailers();
        fetchReviews();

    }//end create

    @Override
    protected void onResume(){
        super.onResume();

        //update today
        m = dataBase.getData().CheckMovies(movie.getId());
        m.observe(this, new Observer<Movie>(){

            @Override
            public void onChanged(Movie model) {
                if(m.getValue()!=null){
                    found = true;
                    add_to_fav.setImageResource(R.drawable.filled);
                }
                else {
                    found = false;
                }
            }
        });
    }

    public static LinearLayout getReviewsLayout() {
        return reviews_list;
    }


    public static LinearLayout getTrailersLayout() {
        return trailers_list;
    }

    public void fetchTrailers() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        TrailerService service = retrofit.create(TrailerService.class);
        service.getTrailers(id, "a793ce5a8e7d8d881703b3ea83aac969").enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                if (response.isSuccessful()) {
                    TrailerResponse tresponse = response.body();
                    trailers_list_values = tresponse.getuResult();

                    //add to linear
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.trailer_item, DetailsForMovie.getTrailersLayout(), false);
                    TextView name = view.findViewById(R.id.name);
                    try {
                        for (int i = 0; i < trailers_list_values.size(); i++) {
                            final Trailer temp2 = trailers_list_values.get(i);
                            ///change method here today
                            name.setText(trailers_list_values.get(i).getmName_Trailaer());
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.setData(Uri.parse(NetworkUtils.buildYTUrl(temp2.getMkey())));
                                    getApplicationContext().startActivity(intent);
                                }
                            });
                        }//end for
                    } catch (Exception e) {
                        Log.d("list", e.getMessage());
                    }
                    DetailsForMovie.getTrailersLayout().addView(view);
                }
                return;
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                Toast.makeText(DetailsForMovie.this, t.getMessage(), Toast.LENGTH_SHORT);
            }
        });

    }

    public void fetchReviews() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(GsonConverterFactory.create()).build();
        TrailerService service = retrofit.create(TrailerService.class);
        service.getReviews(id, "a793ce5a8e7d8d881703b3ea83aac969").enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                if (response.isSuccessful()) {
                    ReviewResponse rresponse = response.body();
                    reviews_list_values = rresponse.getResult();

                    //add to linear
                    LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                    View view = inflater.inflate(R.layout.review_item, DetailsForMovie.getReviewsLayout(), false);
                    TextView author = view.findViewById(R.id.author);
                    TextView text = view.findViewById(R.id.textt);

                    try {
                        for (int i = 0; i < reviews_list_values.size(); i++) {

                            //Ghange methods here today
                            author.setText(reviews_list_values.get(i).getmAuthor());
                            text.setText(reviews_list_values.get(i).getmContent());
                        }//end for
                    } catch (Exception e) {
                        Log.d("list", e.getMessage());
                    }

                    DetailsForMovie.getReviewsLayout().addView(view);

                }
                return;
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                Toast.makeText(DetailsForMovie.this, t.getMessage(), Toast.LENGTH_SHORT);
            }
        });


    }//end func


}//end class

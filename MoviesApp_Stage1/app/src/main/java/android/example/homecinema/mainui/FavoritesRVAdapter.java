package android.example.homecinema.mainui;

import android.content.Context;
import android.content.Intent;
import android.example.homecinema.R;
import android.example.homecinema.data.DetailsForMovie;
import android.example.homecinema.data.Movie;
import android.example.homecinema.database.Model;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;


public class FavoritesRVAdapter  extends RecyclerView.Adapter<FavoritesRVAdapter.FavoritesRVAdapterViewHolder> {

    public List<Movie> fav_movies;

    public FavoritesRVAdapter() {
    }

   /* public void setList(List<Movie> movies){
        this.fav_movies=movies;
        notifyDataSetChanged();
    }*/

    public void setListModels(List<Movie> models){
        this.fav_movies=models;
        notifyDataSetChanged();
    }

    @Override
    public FavoritesRVAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.moive_item, viewGroup, false);
        FavoritesRVAdapterViewHolder viewHolder = new FavoritesRVAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavoritesRVAdapterViewHolder holder, int position) {

        String poster = fav_movies.get(holder.getAdapterPosition()).getPoster();
        Picasso.with(holder.movie_poster.getContext())
                .load("http://image.tmdb.org/t/p/w500/" + poster)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.movie_poster);
    }

    @Override
    public int getItemCount() {
       return this.fav_movies.size();
    }


    public class FavoritesRVAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView movie_poster;

        public FavoritesRVAdapterViewHolder(View itemView) {
            super(itemView);
            movie_poster = (ImageView) itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetailsForMovie.class);
            int position=getAdapterPosition();
            Movie fav_movie = new Movie();
            fav_movie.setId(fav_movies.get(position).getId());
            fav_movie.setPoster(fav_movies.get(position).getPoster());
            fav_movie.setTitle(fav_movies.get(position).getTitle());
            fav_movie.setPlot(fav_movies.get(position).getPlot());
            fav_movie.setDate(fav_movies.get(position).getDate());
            fav_movie.setRating(fav_movies.get(position).getRating());
            intent.putExtra("picked", fav_movie);
            view.getContext().startActivity(intent);
        }

    }//end inner




}//end class

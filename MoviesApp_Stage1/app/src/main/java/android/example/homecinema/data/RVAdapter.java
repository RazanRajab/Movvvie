package android.example.homecinema.data;

import android.content.Context;
import android.content.Intent;
import android.example.homecinema.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class RVAdapter  extends RecyclerView.Adapter<RVAdapter.RVAdapterViewHolder> {

    private List <Movie> movies_to_show = new ArrayList<>();

    public void setList(List<Movie> list) {
        movies_to_show = list;
        notifyDataSetChanged();
    }

    @Override
    public RVAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.moive_item, viewGroup, false);
        RVAdapterViewHolder viewHolder = new RVAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RVAdapterViewHolder holder, int position) {
        String full_poster_path = "http://image.tmdb.org/t/p/w342/" + movies_to_show.get(position).getPoster();
        Picasso.with(holder.movie_poster.getContext())
                .load(full_poster_path).placeholder(R.mipmap.ic_launcher).into(holder.movie_poster);
    }

    @Override
    public int getItemCount() {
        return movies_to_show.size();
    }

    //inner
    public class RVAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView movie_poster;

        public RVAdapterViewHolder(View itemView) {
            super(itemView);
            movie_poster = (ImageView) itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), DetailsForMovie.class);
            Movie picked_movie = movies_to_show.get(getAdapterPosition());
            intent.putExtra("picked", picked_movie);
            view.getContext().startActivity(intent);
        }

    }// end inner


}// end class
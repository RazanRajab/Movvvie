package android.example.homecinema.mainui;


import android.example.homecinema.database.Model;
import android.example.homecinema.network.FetchingTask;
import android.example.homecinema.R;
import android.example.homecinema.data.Movie;
import android.example.homecinema.data.RVAdapter;
import android.example.homecinema.database.CreateResponeDataBase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class TabsFragment extends Fragment  {

    private RecyclerView rv_movies;
    private RVAdapter mRVAdapter ;
    private int choice;

    public TabsFragment() {
        // Required empty public constructor
    }

    //recycle vire in fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tabs, container, false);
        rv_movies= view.findViewById(R.id.rv_movies);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity(), 2);
        rv_movies.setLayoutManager(layoutManager);
        mRVAdapter = new RVAdapter();
        rv_movies.setAdapter(mRVAdapter);
        FetchingTask mFetchingTask = new FetchingTask(mRVAdapter);

        //filling the rv based on sort method
        choice = getArguments().getInt("choice");
        Log.d("choice", choice+"");

        switch(choice) {
            case 1:
                mFetchingTask.execute("popular");
                break;

            case 2:
                mFetchingTask.execute("top_rated");
                break;
            case 3:
                CreateResponeDataBase.getInstance(this.getActivity())
                        .getData()
                        .getAllMovies()
                        .observe(this, new Observer<List<Movie>>() {
                            @Override
                            public void onChanged(List<Movie> models) {
                                FavoritesRVAdapter mFRVAdapter = new FavoritesRVAdapter();
                                mFRVAdapter.setListModels(models);
                                rv_movies.setAdapter(mFRVAdapter);
                            }
                        });
                break;
        }
        return view;

    }

}//end class

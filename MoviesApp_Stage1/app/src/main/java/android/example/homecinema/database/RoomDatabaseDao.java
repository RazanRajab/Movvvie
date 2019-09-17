package android.example.homecinema.database;

import android.example.homecinema.data.Movie;
import android.example.homecinema.data.Movies;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;


@Dao
public interface RoomDatabaseDao {
    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> getAllMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMovie(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM Movie where id = :id")
    LiveData<Movie> CheckMovies(int id);
}

package android.example.homecinema.database;

import android.content.Context;
import android.example.homecinema.data.Movie;
import android.util.Log;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Movie.class}, version = 4 , exportSchema = false)
public abstract class CreateResponeDataBase extends RoomDatabase {

    private static final String LOG_TAG = CreateResponeDataBase.class.getSimpleName();
    private static final String DATABASE_NAME = "MoviesDB";
    private static CreateResponeDataBase sInstance;

    public abstract RoomDatabaseDao getData();

    public static CreateResponeDataBase getInstance(Context context) {
        if (sInstance == null) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        CreateResponeDataBase.class, DATABASE_NAME)
                        .build();

        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }


}

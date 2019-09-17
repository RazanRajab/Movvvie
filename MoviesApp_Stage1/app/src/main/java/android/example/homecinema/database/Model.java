package android.example.homecinema.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;

@Entity
public class Model implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private  int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "poster")
    private String poster;

    @ColumnInfo(name = "plot")
    private String plot;

    @ColumnInfo(name = "rating")
    private String rating;

    @ColumnInfo(name = "date")
    private String date;

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlot() {
        return plot;
    }
    public void setPlot(String plot) {
        this.plot = plot;
    }


    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getRating () {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }




}//end class

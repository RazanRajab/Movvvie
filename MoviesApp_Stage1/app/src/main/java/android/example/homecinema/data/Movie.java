package android.example.homecinema.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Movie implements Parcelable {

    private  String title;
    private  String poster;
    private  String plot;
    private  String rating;
    private  String date;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Movie(String title, String poster, String plot, String rating, String date, int id) {
        this.title = title;
        this.poster = poster;
        this.plot = plot;
        this.rating = rating;
        this.date = date;
        this.id = id;
    }

    @Ignore
    public Movie(){}

    @Ignore
    public Movie(Parcel parcel) {
        title = parcel.readString();
        poster = parcel.readString();
        plot = parcel.readString();
        rating = parcel.readString();
        date = parcel.readString();
        id=parcel.readInt();
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getPlot() {
        return plot;
    }

    public String getRating() {
        return rating;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public void setPlot(String plot) {
        this.plot = plot;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(plot);
        parcel.writeString(rating);
        parcel.writeString(date);
        parcel.writeInt(id);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };


}//end class

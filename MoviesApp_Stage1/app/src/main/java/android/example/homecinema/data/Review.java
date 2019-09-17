package android.example.homecinema.data;

import com.google.gson.annotations.SerializedName;

public class Review {


    public Review() {
    }

    /*
        private String author;
        private String content;
        private String url;


        public String getAuthor() {
            return author;
        }
        public String getContent() {
        return content;
    }
        public String getUrl() {
        return url;
    }
        public void setAuthor(String author) {
            this.author = author;
        }
        public void setContent(String content) {
            this.content = content;
        }

        public void setUrl(String url) {
            this.url = url;
        }

*/

        //
        @SerializedName("author")
        String mAuthor;
        @SerializedName("content")
        String mContent;

    public String getmAuthor() {
        return mAuthor;
    }
    public String getmContent() {
        return mContent;
    }



}//end class

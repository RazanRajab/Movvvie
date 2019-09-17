package android.example.homecinema.data;

import com.google.gson.annotations.SerializedName;

public class Trailer {

    @SerializedName("key")
    String mkey ;

    @SerializedName("name")
    String mName_Trailaer;
    public String getmName_Trailaer() {
        return mName_Trailaer;
    }
    public String getMkey() {
        return mkey;
    }

/*
        private String id;
        private String key;
        private String name;
        private String site;
        private String type;

        public Trailer() {
        }

        public String getId() {
            return id;
        }
        public String getKey() {
        return key;
    }
        public String getName() {
        return name;
    }
        public String getSite() {
        return site;
    }
        public String getType() {
        return type;
    }

        public void setId(String id) {
            this.id = id;
        }
        public void setKey(String key) {
            this.key = key;
        }
        public void setName(String name) {
        this.name = name;
    }
        public void setSite(String site) {
        this.site = site;
    }
        public void setType(String type) {
        this.type = type;
    }  */
}

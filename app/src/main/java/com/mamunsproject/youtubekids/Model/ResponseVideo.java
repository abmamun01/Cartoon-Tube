package com.mamunsproject.youtubekids.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseVideo implements Serializable {

    public ArrayList<Video> items;

    public String nextPageToken;

    public String getPageToken() {
        return nextPageToken;
    }
}

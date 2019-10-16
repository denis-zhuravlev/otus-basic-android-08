package com.otus.otusbasicnetwork;

import com.otus.otusbasicnetwork.api.reponse.MovieJson;

public class MovieItem {
    public String title;
    public String imageUrl;

    public MovieItem(MovieJson movieJson) {
        this.title = movieJson.title;
        this.imageUrl = movieJson.img;
    }
}

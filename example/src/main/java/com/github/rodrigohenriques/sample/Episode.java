package com.github.rodrigohenriques.sample;

@SambaWith(R.layout.episode_item)
public class Episode {
    public String title;
    public String rating;

    public Episode(String title, String rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }
}
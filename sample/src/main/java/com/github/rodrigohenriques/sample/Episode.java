package com.github.rodrigohenriques.sample;

import com.github.rodrigohenriques.samba.TextView;
import com.github.rodrigohenriques.samba.ViewHolder;

@ViewHolder(R.layout.episode_item)
public class Episode {
    public String title;
    public String rating;

    public Episode(String title, String rating) {
        this.title = title;
        this.rating = rating;
    }

    @TextView(R.id.textview_episode_name)
    public String getTitle() {
        return title;
    }

    @TextView(R.id.textview_episode_rating)
    public String getRating() {
        return rating;
    }
}
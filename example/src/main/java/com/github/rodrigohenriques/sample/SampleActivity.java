package com.github.rodrigohenriques.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SampleActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        List<Episode> episodes = new ArrayList<>();

        episodes.add(new Episode("First", "10"));
        episodes.add(new Episode("Second", "8.5"));

        mRecyclerView.setAdapter(new EpisodeAdapter(episodes));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }
}

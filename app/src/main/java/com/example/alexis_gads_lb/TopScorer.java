package com.example.alexis_gads_lb;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class TopScorer {
    public String name;
    public  Integer score;
    public String country;
    public String badgeUrl;

    public TopScorer(String name, Integer score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    @BindingAdapter({"android:urlBadge"})
    public static void loadImage (ImageView view, String badgeUrl){
        Picasso.with(view.getContext())
                .load(badgeUrl)
                .into(view);
    }
}


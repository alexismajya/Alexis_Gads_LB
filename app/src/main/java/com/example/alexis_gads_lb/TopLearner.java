package com.example.alexis_gads_lb;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;

public class TopLearner {
    public String name;
    public  Integer hours;
    public String country;
    public String badgeUrl;

    public TopLearner(String name, Integer hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    @BindingAdapter({"android:badgeUrl"})
    public static void loadImage (ImageView view, String badgeUrl){
        Picasso.with(view.getContext())
                .load(badgeUrl)
                .into(view);
    }
}

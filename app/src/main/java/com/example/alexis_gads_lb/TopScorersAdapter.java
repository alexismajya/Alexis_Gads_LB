package com.example.alexis_gads_lb;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alexis_gads_lb.databinding.FragmentLearnersBinding;

import java.util.ArrayList;

public class TopScorersAdapter extends RecyclerView.Adapter<TopScorersAdapter.TopScorerViewHolder> {

    ArrayList<TopScorer> topScorers;
    public TopScorersAdapter(ArrayList<TopScorer> topScorers) {
        this.topScorers = topScorers;
    }

    @NonNull
    @Override
    public TopScorerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.fragment_scorers, parent, false);
        return new TopScorerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopScorerViewHolder holder, int position) {
        TopScorer topscorer = topScorers.get(position);
        holder.bind(topscorer);
    }

    @Override
    public int getItemCount() {
        return topScorers.size();
    }

    public class TopScorerViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDetails;
        ImageView imageView;
        public TopScorerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName= (TextView) itemView.findViewById(R.id.tvName);
            tvDetails= (TextView) itemView.findViewById(R.id.tvDetails);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        public void bind (TopScorer topScorer) {
            tvName.setText(topScorer.name);
            tvDetails.setText(topScorer.score+" skill IQ Score, "+ topScorer.country);
//            imageView.setImageURI(Uri.parse(topScorer.badgeUrl));
            loadImage(imageView);
        }
        public void loadImage (ImageView imageView){
            imageView.setImageResource(R.drawable.skill_iq_trimmed);
        }
    }
}



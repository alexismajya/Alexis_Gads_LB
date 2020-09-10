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


import java.util.ArrayList;

public class TopLearnersAdapter extends RecyclerView.Adapter<TopLearnersAdapter.TopLearnerViewHolder> {

    ArrayList<TopLearner> topLearners;
    public TopLearnersAdapter(ArrayList<TopLearner> topLearners) {
        this.topLearners = topLearners;
    }

    @NonNull
    @Override
    public TopLearnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.fragment_learners, parent, false);

        return new TopLearnerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopLearnerViewHolder holder, int position) {
        TopLearner topLearner = topLearners.get(position);
        holder.bind(topLearner);

    }


    @Override
    public int getItemCount() {
        return topLearners.size();
    }

    public class TopLearnerViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvDetails;
        ImageView imageView;
        public TopLearnerViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName= (TextView) itemView.findViewById(R.id.tvName);
            tvDetails= (TextView) itemView.findViewById(R.id.tvDetails);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        public void bind (TopLearner topLearner) {
            tvName.setText(topLearner.name);
            tvDetails.setText(topLearner.hours+" learning hours, "+ topLearner.country);
//            imageView.setImageURI(Uri.parse(topLearner.badgeUrl));
            loadImage(imageView);
        }

        public void loadImage (ImageView imageView){
            imageView.setImageResource(R.drawable.top_learner);
        }
    }
}


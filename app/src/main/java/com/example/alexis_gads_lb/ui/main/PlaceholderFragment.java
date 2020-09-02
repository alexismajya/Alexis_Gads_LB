package com.example.alexis_gads_lb.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.alexis_gads_lb.ApiUtil;
import com.example.alexis_gads_lb.MainActivity;
import com.example.alexis_gads_lb.R;
import android.os.Bundle;

import com.example.alexis_gads_lb.TopLearner;
import com.example.alexis_gads_lb.TopLearnersAdapter;
import com.example.alexis_gads_lb.TopScorer;
import com.example.alexis_gads_lb.TopScorersAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.alexis_gads_lb.ui.main.SectionsPagerAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    private ProgressBar mLoadingProgress;
    private RecyclerView rvTopLearners;
    private RecyclerView rvTopScorers;
    private TextView tvError;
    private String dataType="";

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mLoadingProgress= root.findViewById(R.id.pb_Loading);
        rvTopLearners = root.findViewById(R.id.rv_topLearners);
//        rvTopScorers = root.findViewById(R.id.rv_topScorers);
        tvError = root.findViewById(R.id.tvError);

        LinearLayoutManager topLearnerLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rvTopLearners.setLayoutManager(topLearnerLayoutManager);

        pageViewModel.getText().observe(this, new Observer<String>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onChanged(@Nullable String s) {
                dataType="";
                if(s.equals("tab 1")){
                    try {
                        URL hoursUrl = ApiUtil.buildUrl("hours");
                        dataType="hours";
                        new PlaceholderFragment.DataQueryTask().execute(hoursUrl);

                    }
                    catch (Exception e){
                        Log.d("Error", e.getMessage());
                    }
                }
                else{
                    try {
                        URL skillsUrl = ApiUtil.buildUrl("skilliq");
                        dataType="skills";
                        new PlaceholderFragment.DataQueryTask().execute(skillsUrl);
                    }
                    catch (Exception e){
                        Log.d("Error", e.getMessage());
                    }
                }
            }
        });

        return root;
    }

    public class DataQueryTask extends AsyncTask<URL, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0];
            String result = null;
            try{
                result = ApiUtil.getJson(searchURL);
            }
            catch (Exception e){
                Log.d("Error", e.getMessage());
                return null;
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            mLoadingProgress.setVisibility(View.INVISIBLE);

            if (result== null){
                rvTopLearners.setVisibility(View.INVISIBLE);
                tvError.setVisibility(View.VISIBLE);
            }
            else {
                rvTopLearners.setVisibility(View.VISIBLE);
                tvError.setVisibility(View.INVISIBLE);
            }

            if(dataType=="hours"){
                ArrayList<TopLearner> topLearners = ApiUtil.getTopLearnersFromJson(result);
                TopLearnersAdapter adapter = new TopLearnersAdapter(topLearners);
                rvTopLearners.setAdapter(adapter);
            }
            else if(dataType=="skills"){
                ArrayList<TopScorer> topScorers = ApiUtil.getTopScorersFromJson(result);
                TopScorersAdapter adapter = new TopScorersAdapter(topScorers);
                rvTopLearners.setAdapter(adapter);
            }
            else {
                rvTopLearners.setVisibility(View.INVISIBLE);
                tvError.setVisibility(View.VISIBLE);
            }

        }

    }

}
package com.example.alexis_gads_lb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alexis_gads_lb.submit_services.ServiceBuilder;
import com.example.alexis_gads_lb.submit_services.SubmitService;

import retrofit2.Callback;
import retrofit2.Response;

public class Submit extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final SubmitService sendService = ServiceBuilder.buildService(SubmitService.class);

        final EditText fName = (EditText) findViewById(R.id.fName);
        final EditText lName = (EditText) findViewById(R.id.lName);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText link = (EditText) findViewById(R.id.link);

        button = (Button) findViewById(R.id.submit_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext());
//                View mView= getLayoutInflater().inflate(R.layout.dialog, null);
                builder.setCancelable(true);
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // continue ...

                        retrofit2.Call retrofit2 = sendService.Submit(fName.getText().toString(),
                                lName.getText().toString(),
                                email.getText().toString(),
                                link.getText().toString());

                        retrofit2.enqueue(new Callback<String>(){
                            @Override
                            public void onResponse(retrofit2.Call call, Response response) {
                                Toast.makeText(view.getContext(), "Submission Successful", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(retrofit2.Call call, Throwable t) {
                                Toast.makeText(view.getContext(), "Submission not Successful", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

//                builder.setView(mView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        this.finish();

    }
}

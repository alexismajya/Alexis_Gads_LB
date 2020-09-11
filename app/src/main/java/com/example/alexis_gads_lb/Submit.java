package com.example.alexis_gads_lb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexis_gads_lb.submit_services.ServiceBuilder;
import com.example.alexis_gads_lb.submit_services.SubmitService;

import retrofit2.Callback;
import retrofit2.Response;

public class Submit extends AppCompatActivity {
    private Button button;
    private Button confirm_button;
    private ImageView cancel_button;

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

        //Toast
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        final TextView toast_text = (TextView) layout.findViewById(R.id.toast_text);
        final ImageView success = (ImageView) layout.findViewById(R.id.success);
        final ImageView not_success = (ImageView) layout.findViewById(R.id.not_success);


        final Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        button = (Button) findViewById(R.id.submit_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext());
                builder.setCancelable(true);
//                builder.setMessage("Are you sure?");

                //custom dialog
                View mView= getLayoutInflater().inflate(R.layout.dialog, null);
                cancel_button= (ImageView) mView.findViewById(R.id.close);
                confirm_button= (Button) mView.findViewById(R.id.confirm);

                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                confirm_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                    }
//                });
//
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {

                        retrofit2.Call retrofit2 = sendService.Submit(fName.getText().toString(),
                                lName.getText().toString(),
                                email.getText().toString(),
                                link.getText().toString());

                        retrofit2.enqueue(new Callback<String>(){
                            @Override
                            public void onResponse(retrofit2.Call call, Response response) {
                                toast_text.setText("Submission Successful");
                                success.setVisibility(View.VISIBLE);
                                not_success.setVisibility(View.INVISIBLE);
                                toast.show();
//                                Toast.makeText(view.getContext(), "Submission Successful", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(retrofit2.Call call, Throwable t) {
                                toast_text.setText("Submission not Successful");
                                success.setVisibility(View.INVISIBLE);
                                not_success.setVisibility(View.VISIBLE);
                                toast.show();
//                                Toast.makeText(view.getContext(), "Submission not Successful", Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialog.cancel();

                    };
                });


                //custom cancel buttom
                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

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

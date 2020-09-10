package com.faruking.practiceproject2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.faruking.practiceproject2.network.RetrofitClientInstanceSubmit;
import com.faruking.practiceproject2.service.PostSubmitData;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make status bar transparent
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_submission_page);

        //sets up button for the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText etFirstName =  findViewById(R.id.etFirstName);
        final EditText etLastName = findViewById(R.id.etLastName);
        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etLink = findViewById(R.id.etProjectLink);
        Button btnSubmit = findViewById(R.id.btnSubmitFinal);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String firstName = etFirstName.getText().toString().trim();
                final String lastName = etLastName.getText().toString().trim();
                final String eMail = etEmail.getText().toString().trim();
                final  String projectLink = etLink.getText().toString().trim();
                if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) &&
                        !TextUtils.isEmpty(eMail) && !TextUtils.isEmpty(projectLink)){

                    final AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionPage.this);
                    LayoutInflater inflater = getLayoutInflater();

                    View dialogView = inflater.inflate(R.layout.custom_dialog_confirm, null);
                    Button btnYes = dialogView.findViewById(R.id.btn_yes);
                    builder.setView(dialogView);
                    ImageButton imgClose = dialogView.findViewById(R.id.img_close);
                    final Dialog dialog = builder.create();

                    dialog.show();
                    btnYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sendPost(firstName,lastName,eMail,projectLink);
                            dialog.cancel();
                        }
                    });
                    imgClose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.cancel();
                        }
                    });
                }
                else{
                    Toast.makeText(SubmissionPage.this,"Please fill all required fields",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void sendPost(String firstName,String lastName,String eMail,String linkToProject){
        PostSubmitData service = RetrofitClientInstanceSubmit.getRetrofitInstance()
                .create(PostSubmitData.class);
        Call<Void> call = service.postSubmission(firstName,lastName,eMail,linkToProject);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("Successful","this request was successful");
                    SuccessfulDialog successfulDialog = new SuccessfulDialog();
                    successfulDialog.show(getSupportFragmentManager(),"Successful!");
                }
                else{
                    Log.d("Not Successful","this request was not successful");
                    NotSuccessfulDialog notSuccessfulDialog = new NotSuccessfulDialog();
                    notSuccessfulDialog.show(getSupportFragmentManager(),"Not Successful!!!");
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("Not Successful","this request was not successful");
                NotSuccessfulDialog notSuccessfulDialog = new NotSuccessfulDialog();
                notSuccessfulDialog.show(getSupportFragmentManager(),"Not Successful!!!");
            }
        });
    }
}
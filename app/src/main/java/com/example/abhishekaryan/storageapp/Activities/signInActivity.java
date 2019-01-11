package com.example.abhishekaryan.storageapp.Activities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.abhishekaryan.storageapp.R;

public class signInActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EMAIL_KEY ="EMAIL_KEY" ;
    public static final String FILE_NAME ="FILE_NAME" ;
    private EditText email;
    private EditText password;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email=(EditText)findViewById(R.id.activity_main_editText_email);
        password=(EditText)findViewById(R.id.activity_main_editText_password);
        signIn=(Button)findViewById(R.id.signIn_activity_btn_signIn);
        signIn.setOnClickListener(this);
        SharedPreferences preferences=getSharedPreferences(FILE_NAME,MODE_PRIVATE);
        email.setText(preferences.getString(EMAIL_KEY,""));

    }

    @Override
    public void onClick(View view) {


        int itemId=view.getId();

        switch (itemId){


            case R.id.signIn_activity_btn_signIn:
                email.setError(null);
                password.setError(null);
                String emailText=email.getText().toString();

                if(TextUtils.isEmpty(emailText)){

                    email.setError("Please enter email");
                }

                else {
                    SharedPreferences preferences=getSharedPreferences(FILE_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString(EMAIL_KEY,emailText);
                    editor.apply();
                    getIntent().putExtra(EMAIL_KEY,emailText);
                    setResult(RESULT_OK,getIntent());
                    finish();
                }
                break;
        }


    }
}

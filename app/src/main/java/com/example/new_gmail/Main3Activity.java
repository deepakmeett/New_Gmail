package com.example.new_gmail;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
public class Main3Activity extends AppCompatActivity {

    ImageView imageView;
    TextView textView3, textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main3 );
        imageView = findViewById( R.id.profile_sec );
        textView3 = findViewById( R.id.name_sec);
        textView4 = findViewById( R.id.email_sec );

        String s1, s2, s3;
        Intent intent = getIntent();
        s1 = intent.getStringExtra( "s1" );
        s2 = intent.getStringExtra( "s2" );
        s3 = intent.getStringExtra( "s3" );
        textView3.setText( s1 );
        textView4.setText( s2 );
        Glide.with( this ).load( s3 ).into( imageView );
        
    }
}

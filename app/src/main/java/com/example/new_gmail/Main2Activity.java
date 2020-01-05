package com.example.new_gmail;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
public class Main2Activity extends AppCompatActivity {

    TextView textView, textView2;
    ImageView imageView;
    CardView cardView;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        cardView = findViewById( R.id.cardView );
        textView = findViewById( R.id.name );
        textView2 = findViewById( R.id.email );
        imageView = findViewById( R.id.profile_pic );
        
        final GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount( this );
        cardView.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                googleSignInClient.signOut();
                Toast.makeText( Main2Activity.this, "Sign Out Successfully", Toast.LENGTH_SHORT ).show();
                finish();
                return false;
            }
        } );
        cardView.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Main2Activity.this, Main3Activity.class );
                assert acct != null;
                intent.putExtra( "s1", acct.getDisplayName() );
                intent.putExtra( "s2", acct.getEmail() );
                intent.putExtra( "s3", acct.getPhotoUrl() );
                
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View, String>( imageView, "img" );
                pairs[1] = new Pair<View, String>( textView, "img" );
                pairs[2] = new Pair<View, String>( textView2, "img" );
              
                ActivityOptions activityOptions = null;
                activityOptions = ActivityOptions.makeSceneTransitionAnimation( Main2Activity.this, pairs );
                startActivity( intent, activityOptions.toBundle() );
            }
        } );
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        googleSignInClient = GoogleSignIn.getClient( this, gso );
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();
            textView.setText( personName );
            textView2.setText( personEmail );
            Glide.with( this ).load( personPhoto ).into( imageView );
        }
    }
}

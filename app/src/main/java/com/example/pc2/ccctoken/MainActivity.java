package com.example.pc2.ccctoken;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private Button btnEnter,btnLogout;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    FirebaseDatabase database;
    DatabaseReference myRef;

    String dataTitle, dataMessage;
    EditText title, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        // Handle possible data accompanying notification message.
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                if (key.equals("title")) {
                    dataTitle=(String)getIntent().getExtras().get(key);
                }
                if (key.equals("message")) {
                    dataMessage = (String)getIntent().getExtras().get(key);;
                }
            }
            showAlertDialog();
        }

        title = (EditText) findViewById(R.id.edtInput);
        message = (EditText) findViewById(R.id.edtAccount);
        btnEnter = (Button) findViewById(R.id.btn_send);
        btnLogout = (Button) findViewById(R.id.btn_logout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("messages");

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Message");
        builder.setMessage("title: " + dataTitle + "\n" + "message: " + dataMessage);
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    public void sendMessage(View view) {
        myRef.push().setValue(new Message(title.getText().toString(), message.getText().toString()));
        Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
    }



}


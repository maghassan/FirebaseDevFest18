package enovatelab.firebasedevfest18;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button ourButton;
    private EditText ourText;

    private FirebaseFirestore ourDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ourButton = findViewById(R.id.ourButton);
        ourText = findViewById(R.id.ourText);

        ourDB = FirebaseFirestore.getInstance();



        ourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String TextwePushed = ourText.getText().toString();

                if (TextUtils.isEmpty(TextwePushed)) {

                    ourText.setError("Please enter a text");
                    ourText.requestFocus();

                } else {

                    Map<String, Object> ourMap = new HashMap<>();

                    ourMap.put("OurText", TextwePushed);

                    ourDB.collection("OurTextPost")
                            .add(ourMap)
                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {

                                    if (task.isComplete()) {

                                        Toast.makeText(MainActivity.this, "Pushed Successfully", Toast.LENGTH_LONG).show();
                                        ourText.setText("");
                                    }
                                }
                            });
                }
            }
        });
    }
}

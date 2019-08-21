package es.esy.practikality.healr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CollabActivity extends AppCompatActivity {
    Button send;
    EditText name, location, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collab);
        send = findViewById(R.id.send);
        name = findViewById(R.id.name);
        location = findViewById(R.id.location);
        msg = findViewById(R.id.msg);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_str = name.getText().toString();
                String location_str = location.getText().toString();
                String msg_str = msg.getText().toString();
                Intent sendemail = new Intent(Intent.ACTION_SEND);
                sendemail.putExtra(Intent.EXTRA_EMAIL, new String[]{"ribhavsharma2003@gmail.com"});
                sendemail.putExtra(Intent.EXTRA_SUBJECT, "Collab Invite From " + name_str + ", "+location_str);
                sendemail.putExtra(Intent.EXTRA_TEXT, msg_str);
                sendemail.setType("message/rfc822");
                startActivity(Intent.createChooser(sendemail, "Sent."));

                BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch(menuItem.getItemId()){
                            case R.id.action_pickup:
                                startActivity(new Intent(getApplicationContext(), MediActivity.class));
                                break;
                            case R.id.action_collab:
                                Toast.makeText(CollabActivity.this, "Already on Collab", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_profile:
                                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                                break;
                            case R.id.action_remind:
                                startActivity(new Intent(getApplicationContext(), RemindActivity.class));
                                break;

                        }

                        return true;
                    }
                });
            }
        });
    }
}




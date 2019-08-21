package es.esy.practikality.healr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {
    TextView usertv;
    ImageView userprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        userprofile = findViewById(R.id.userprofile);
        usertv = findViewById(R.id.usertv);
        SharedPreferences sharedPref = getSharedPreferences("Healr", Context.MODE_PRIVATE);
        int earnedTillNow = sharedPref.getInt("totalEarned", 0);
        String userName = sharedPref.getString("currentUserName", "");
        String userEmail = sharedPref.getString("currentUserEmail", "");;
        TextView tv3 = findViewById(R.id.earnedtillnow);
        tv3.setText(String.valueOf(earnedTillNow));
        TextView historytxt = (TextView) findViewById(R.id.historytext);
        TextView coststxt = (TextView) findViewById(R.id.paidtext);
        String orderHistory = sharedPref.getString("OrderHistory", "");
        String orderCosts = sharedPref.getString("OrderCosts", "");
        String[] orders = orderHistory.split(" ");
        String[] costs = orderCosts.split(" ");
        String toputinHistory = "", toputinCosts = "";
        int temp = orders.length - 1;
        if (orders.length >= 6) {
            toputinHistory = orders[temp] + "\n" + orders[temp - 1] + "\n" + orders[temp - 2] + "\n" + orders[temp - 3] + "\n" + orders[temp - 4];
            toputinCosts = costs[temp] + "\n" + costs[temp - 1] + "\n" + costs[temp - 2] + "\n" + costs[temp - 3] + "\n" + costs[temp - 4];
        } else if (orders.length >= 5) {
            toputinHistory = orders[temp] + "\n" + orders[temp - 1] + "\n" + orders[temp - 2] + "\n" + orders[temp - 3];
            toputinCosts = costs[temp] + "\n" + costs[temp - 1] + "\n" + costs[temp - 2] + "\n" + costs[temp - 3];
        } else if (orders.length >= 4) {
            toputinHistory = orders[temp] + "\n" + orders[temp - 1] + "\n" + orders[temp - 2];
            toputinCosts = costs[temp] + "\n" + costs[temp - 1] + "\n" + costs[temp - 2];
        } else if (orders.length >= 3) {
            toputinHistory = orders[temp] + "\n" + orders[temp - 1];
            toputinCosts = costs[temp] + "\n" + costs[temp - 1];
        } else if (orders.length >= 2) {
            toputinHistory = orders[temp];
            toputinCosts = costs[temp];
        } else {
            toputinHistory = "Nothing Found";
            toputinCosts = "--";
        }
        historytxt.setText(toputinHistory);
        coststxt.setText(toputinCosts);
        if (getIntent().hasExtra("username")) {
            usertv.setText(getIntent().getStringExtra("username"));
        } else {
            System.out.println("khaali hai");
        }

        // bottom navbar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.action_pickup:
                        startActivity(new Intent(getApplicationContext(), MediActivity.class));
                        break;
                    case R.id.action_collab:
                        startActivity(new Intent(MenuActivity.this, CollabActivity.class));
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        startActivity(i);
                        break;
                    case R.id.action_profile:
                        Toast.makeText(MenuActivity.this, "Already on Profile", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_remind:
                        startActivity(new Intent(getApplicationContext(), RemindActivity.class));
                        break;

                }

                return true;
            }
        });
    }

    private static final int GALLERY_REQUEST = 1;


    public void addImage(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            userprofile.setImageURI(imageUri);
        }
    }
}



package es.esy.practikality.healr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ImageView userprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences sharedPref = getSharedPreferences("Healr", Context.MODE_PRIVATE);
        if (sharedPref.getBoolean("firstTime", true)) {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("firstTime", false);
            editor.putBoolean("loggedIn", false);
            editor.putString("userPass", "first");
            editor.putString("userName", "first");
            editor.putString("userEmail", "first ");
            editor.putString("userEmail", "first ");
            editor.putInt("totalEarned", 0);
            editor.putString("OrderHistory", "");
            editor.putString("OrderCosts", "");
            editor.apply();
        }
//        if (sharedPref.getBoolean("loggedIn", false)) {
//            Intent intent1 = new Intent(getApplicationContext(), Menu.class);
//            startActivity(intent1);
//        }
    }

    public void gotoSignUp(View view) {
        Intent intent1 = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent1);
    }

    public void makeToast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();
    }

    public void goto_Login(View view) {
        Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent1);
    }
}
package es.esy.practikality.healr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*SharedPreferences sharedPref = getSharedPreferences("Healr", Context.MODE_PRIVATE);
        int earnedTillNow = sharedPref.getInt("totalEarned",0);
        String userName = sharedPref.getString("currentUserName","");
        String userEmail = sharedPref.getString("currentUserEmail","");
        TextView tv1 = (TextView) findViewById(R.id.usernamelogin);
        TextView tv2 = (TextView) findViewById(R.id.useremaillogin);
        TextView tv3 = (TextView) findViewById(R.id.earnedtillnow);
        tv1.setText(userName);
        tv2.setText(userEmail);
        tv3.setText("" + earnedTillNow);

        TextView historytxt = (TextView) findViewById(R.id.historytext);
        TextView coststxt = (TextView) findViewById(R.id.paidtext);
        String orderHistory = sharedPref.getString("OrderHistory","");
        String orderCosts = sharedPref.getString("OrderCosts","");
        String[] orders = orderHistory.split(" ");
        String[] costs = orderCosts.split(" ");
        for(int i=0;i<orders.length;i++){
            String texts = historytxt.getText().toString();
            historytxt.setText(texts + orders[i] + "\n");
            String costtexts = coststxt.getText().toString();
            coststxt.setText(costtexts + costs[i] + "\n");
        } */
    }
    public void signout(View view){
        SharedPreferences sharedPref = getSharedPreferences("Healr", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("currentUserName","");
        editor.putString("currentUserEmail","");
        editor.putBoolean("loggedIn",false);
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);
    }
}

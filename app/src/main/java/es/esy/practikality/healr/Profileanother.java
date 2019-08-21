package es.esy.practikality.healr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Profileanother extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileanother);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SharedPreferences sharedPref = getSharedPreferences("Healr", Context.MODE_PRIVATE);
        int earnedTillNow = sharedPref.getInt("totalEarned",0);
        String userName = sharedPref.getString("currentUserName","");
        String userEmail = sharedPref.getString("currentUserEmail","");
        TextView tv1 = (TextView) findViewById(R.id.usernamelogin);
        TextView tv2 = (TextView) findViewById(R.id.useremaillogin);
        TextView tv3 = (TextView) findViewById(R.id.earnedtillnow);
        tv1.setText(userName);
        tv2.setText(userEmail);
        tv3.setText(String.valueOf(earnedTillNow));
        TextView historytxt = (TextView) findViewById(R.id.historytext);
        TextView coststxt = (TextView) findViewById(R.id.paidtext);
        String orderHistory = sharedPref.getString("OrderHistory","");
        String orderCosts = sharedPref.getString("OrderCosts","");
        String[] orders = orderHistory.split(" ");
        String[] costs = orderCosts.split(" ");
        String toputinHistory = "", toputinCosts="";
        int temp = orders.length-1;
        if(orders.length>=6){
            toputinHistory = orders[temp] + "\n" + orders[temp-1] + "\n" + orders[temp-2] + "\n" + orders[temp-3] + "\n" + orders[temp-4];
            toputinCosts = costs[temp] + "\n" + costs[temp-1] + "\n" + costs[temp-2] + "\n" + costs[temp-3] + "\n" + costs[temp-4];
        }else if(orders.length>=5){
            toputinHistory = orders[temp] + "\n" + orders[temp-1] + "\n" + orders[temp-2] + "\n" + orders[temp-3];
            toputinCosts = costs[temp] + "\n" + costs[temp-1] + "\n" + costs[temp-2] + "\n" + costs[temp-3];
        }else if(orders.length>=4){
            toputinHistory = orders[temp] + "\n" + orders[temp-1] + "\n" + orders[temp-2];
            toputinCosts = costs[temp] + "\n" + costs[temp-1] + "\n" + costs[temp-2];
        }else if(orders.length>=3){
            toputinHistory = orders[temp] + "\n" + orders[temp-1];
            toputinCosts = costs[temp] + "\n" + costs[temp-1];
        }else if(orders.length>=2){
            toputinHistory = orders[temp];
            toputinCosts = costs[temp];
        }else{
            toputinHistory = "Nothing Found";
            toputinCosts = "--";
        }
        historytxt.setText(toputinHistory);
        coststxt.setText(toputinCosts);
    }
    public void signout(View view){
        SharedPreferences sharedPref = getSharedPreferences("Healr", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("currentUserName","");
        editor.putString("currentUserEmail","");
        editor.putBoolean("loggedIn",false);
        editor.apply();
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);
    }
}

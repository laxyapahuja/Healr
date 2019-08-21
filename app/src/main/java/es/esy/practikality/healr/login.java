package es.esy.practikality.healr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void login(View view){
        SharedPreferences sharedPref = getSharedPreferences("Healr", Context.MODE_PRIVATE);
        TextInputEditText useremailtv = (TextInputEditText) findViewById(R.id.useremailogin);
        TextInputEditText passwordtv = (TextInputEditText) findViewById(R.id.passwordlogin);
        String useremail = useremailtv.getText().toString().trim();
        String password = passwordtv.getText().toString().trim();
        System.out.println(useremail + password);
        String userEmail = sharedPref.getString("userEmail","nomail");
        if(userEmail.contains(useremail)){
            String[] emails = userEmail.split(" ");
            int k = 0;
            for(int i=0;i<emails.length;i++){
                if(emails[i].equals(useremail)){
                    k=i;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("currentUserEmail",emails[k]);
                    editor.apply();
                    break;
                }
            }
            String[] passwords = sharedPref.getString("userPass","").split("newpassfromhere");
            String[] usernames = sharedPref.getString("userName","").split("newuserfromhere");
            if(passwords[k].equals(password)){
                //add intent
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean("loggedIn",true);
                editor.putString("currentUserName",usernames[k]);
                editor.apply();
                Intent intent1 = new Intent(getApplicationContext(),Menu.class);
               intent1.putExtra("username", usernames[k]);
               startActivity(intent1);
            }else{
                makeToast("Invalid useremail and password combination. Try again.");
            }
        }
    }

    public void makeToast(String text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context,text,duration).show();
    }

}

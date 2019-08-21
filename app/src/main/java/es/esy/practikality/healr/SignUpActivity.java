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


public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void signUpNow(View view) {
        TextInputEditText et = (TextInputEditText) findViewById(R.id.username);
        TextInputEditText et1 = (TextInputEditText) findViewById(R.id.useremailogin);
        TextInputEditText et2 = (TextInputEditText) findViewById(R.id.userpassword);
        String username = et.getText().toString().trim();
        String useremail = et1.getText().toString().trim();
        String userpassword = et2.getText().toString().trim();
        System.out.println(username + useremail + userpassword);
        SharedPreferences sharedPref = getSharedPreferences("Healr",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String userEmail = sharedPref.getString("userEmail","errormail@gmail.com");
        String userPass = sharedPref.getString("userPass","errorpass");
        String userName = sharedPref.getString("userName","errorpass");
        if(useremail.length()!=0 && userpassword.length()!=0){
            if(userEmail.contains(useremail)){
                makeToast("We already have an account with this email id registered");
            }else{

                editor.putString("userEmail",userEmail + useremail + " ");
                editor.putString("userPass",userPass + "newpassfromhere" + userpassword);
                editor.putString("userName",userName + "newuserfromhere" + username);
                editor.apply();
                makeToast("Account Created. Welcome to Healr!");

                Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent1);
            }
        }else{
            makeToast("Enter all details to create an account");
        }
    }
    public void makeToast(String text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context,text,duration).show();
    }
    public void goto_Login(View view){
        Intent intentt =new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intentt);
    }



}

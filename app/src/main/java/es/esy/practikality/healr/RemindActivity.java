package es.esy.practikality.healr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class RemindActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String selectedpub = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerdrugremind);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.drugs_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String selected = parent.getItemAtPosition(pos).toString();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
        ((TextView) parent.getChildAt(0)).setTextSize(5);
        if(selected.equals("Select Item")){
            makeToast("Please Select a Drug to Add");
        }else{
            selectedpub = selected;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    public void makeToast(String text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context,text,duration).show();
    }
    public void goToAddress(View view){
        TextInputEditText et = (TextInputEditText) findViewById(R.id.expirationdate);
        String date = et.getText().toString();
        if(date.length()==4){
            SharedPreferences sharedPref = getSharedPreferences("Healr",Context.MODE_PRIVATE);
            String user = sharedPref.getString("currentUserName","");
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference();
            myRef.child("Reminders").child(user).setValue(selectedpub + " on " + date);
            makeToast("Reminder Saved. You'll be reminded with 1-2 business days of the date you've saved.");
            Intent intent1 = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(intent1);
        }else{
            makeToast("Wrong input format. Required format: mmyy");
        }
    }
    public void gotoPickup(View view) {
        Intent intent1 = new Intent(getApplicationContext(), MediActivity.class);
        startActivity(intent1);
    }


    public void goToCollab(View view) {
        String url = "http://arnabsagar.typeform.com/to/mJOJAV";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void goToRemind(View view) {
        Intent intent1 = new Intent(getApplicationContext(), RemindActivity.class);
        startActivity(intent1);
    }
    public void goToProfile(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}

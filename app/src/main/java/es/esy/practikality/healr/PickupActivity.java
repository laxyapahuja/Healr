package es.esy.practikality.healr;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class PickupActivity extends AppCompatActivity {
    String valuefinal = "";
    Button btn;
    Button btn_time;
    int year_x, month_x, day_x, hour_x, minute_x;
    static final int DIALOG_ID = 0;
    static final int DIALOG_ID1 = 1;
    private final int REQUEST_CODE_PLACEPICKER = 1;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);
        TextView buyitfor = (TextView) findViewById(R.id.buyitfor);
        MaterialButton confirm =  findViewById(R.id.confirmpickupbtn);
        buyitfor.setAlpha(0.0f);
        confirm.setAlpha(0.0f);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final Calendar cal = Calendar.getInstance();

        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_YEAR);
        dialog_OnButtonClick();
        showTimePickerDialog();
        Calendar dateTime = Calendar.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.action_pickup:
                        Toast.makeText(PickupActivity.this, "Already on Pickup", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_collab:
                        String url = "http://arnabsagar.typeform.com/to/mJOJAV";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
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

    public void dialog_OnButtonClick() {
        btn = (Button) findViewById(R.id.select_date);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        {;
                            showDialog(DIALOG_ID);
                        }
                    }
                }
        );
    }

    public void showTimePickerDialog() {
        btn_time = (Button) findViewById(R.id.select_time);
        btn_time.setOnClickListener(

                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setVisible();
                        showDialog(DIALOG_ID1);
                    }
                }
        );
    }

    @Override
    public Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, datePickerListener, year_x, month_x, day_x);
            }
            else if(id == DIALOG_ID1){
            return new TimePickerDialog(this, TimePickerListener, hour_x, minute_x, false);
        }
        return null;
    }

    public TimePickerDialog.OnTimeSetListener TimePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                hour_x = hourOfDay;
                    minute_x=minute;
                    //makeToast(String.valueOf(hour_x)+":"+String.valueOf(minute_x));
                    btn_time.setText(String.valueOf(hour_x)+":"+String.valueOf(minute_x));
                }
            };


    public DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x=year;
            month_x=month+1;
            day_x=dayOfMonth;
            makeToast(String.valueOf(day_x)+"/"+String.valueOf(month_x)+"/"+String.valueOf(year_x));
            btn.setText(String.valueOf(day_x)+"/"+String.valueOf(month_x)+"/"+String.valueOf(year_x));
            valuefinal += String.valueOf(day_x)+"/"+String.valueOf(month_x)+"/"+String.valueOf(year_x);
            SharedPreferences sharedPref = getSharedPreferences("Healr", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            String orderHistory = sharedPref.getString("OrderHistory","");
            editor.putString("OrderHistory",orderHistory + " " + String.valueOf(day_x)+"/"+String.valueOf(month_x)+"/"+String.valueOf(year_x));
            editor.apply();
        }

    };
    public void makeToast(String text){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context,text,duration).show();
    }
    public void goto_PlacePicker(View view){
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        // this would only work if you have your Google Places API working

        try {
            Intent intent = intentBuilder.build(this);
            startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PLACEPICKER && resultCode == RESULT_OK) {
            displaySelectedPlaceFromPlacePicker(data);
        }
    }

    private void displaySelectedPlaceFromPlacePicker(Intent data) {
        Place placeSelected = PlacePicker.getPlace(data, this);

        String name = placeSelected.getName().toString();
        String address = placeSelected.getAddress().toString();
        Button enterCurrentLocation = (Button) findViewById(R.id.current_loc);
        enterCurrentLocation.setText(name);
        valuefinal = name + " " + address;
    }




    public void setVisible() {
        SharedPreferences sharedPref = getSharedPreferences("Healr",Context.MODE_PRIVATE);
        TextView buyitfortv = (TextView) findViewById(R.id.buyitfor);
        MaterialButton confirmtv = (MaterialButton) findViewById(R.id.confirmpickupbtn);
        buyitfortv.setText(sharedPref.getString("costasofnow",""));
        SharedPreferences.Editor editor = sharedPref.edit();
        String orderCosts = sharedPref.getString("OrderCosts","");
        editor.putString("OrderCosts",orderCosts + " " + sharedPref.getString("costasofnow",""));
        editor.apply();
        buyitfortv.setAlpha(1.0f);
        confirmtv.setAlpha(1.0f);
    }
    public void confirm(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        SharedPreferences sharedPref = getSharedPreferences("Healr",Context.MODE_PRIVATE);
        String userName = sharedPref.getString("currentUserName","NoName");
        String toTake = sharedPref.getString("orderednow","noorders");
        Button btn = (Button) findViewById(R.id.select_time);
        valuefinal += " " + btn.getText().toString();
        valuefinal += " toTake: " + toTake;
        myRef.child("Scheduled").child(userName).setValue(valuefinal);
        valuefinal="";
        makeToast("Pickup Confirmed");
        Intent intent1 = new Intent(getApplicationContext(), MenuActivity.class);
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

    public void goToProfile(View view) {
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);
    }
}



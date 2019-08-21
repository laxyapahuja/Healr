package es.esy.practikality.healr;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MediActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    double cost = 0;
    String already = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medi);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerdrug);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.drugs_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }
    public void goToAddress(View view){
        SharedPreferences sharedPref = getSharedPreferences("Healr",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPref.edit();
        cost = Math.floor(cost*100)/100;
        editor.putString("costasofnow","" + cost);
        editor.putString("orderednow",already);
        int alreadyEarned = sharedPref.getInt("totalEarned",0);
        editor.putInt("totalEarned",alreadyEarned + (int) cost);
        editor.apply();
        cost=0;
        Intent intent1 = new Intent(getApplicationContext(),PickupActivity.class);
        startActivity(intent1);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String selected = parent.getItemAtPosition(pos).toString();
        TextView etmed = (TextView) findViewById(R.id.nametext);
        TextView etcost = (TextView) findViewById(R.id.pricetext);
        String medicinesalready = etmed.getText().toString();
        String costalready = etcost.getText().toString();
        if(selected.equals("Select Item")){
            makeToast("Please Select a Drug to Add");
        }else{
            if(selected.equals("Blister Pack")){
                if(!already.contains("1")){
                    if(cost==0){
                        etmed.setText("Blister Pack");
                        etcost.setText("2.4");
                        already+="1";
                    }else{
                        etmed.setText(medicinesalready + "\nBlister Pack");
                        etcost.setText(costalready + "\n2.4");
                    }
                    cost += 2.4;   
                }
            }else if(selected.equals("Medicine Bottle")){

                if(!(already.contains("2"))){if(cost==0){
                    etmed.setText("Medicine Bottle");
                    etcost.setText("4.4");
                    already+="2";
                }else{
                    etmed.setText(medicinesalready + "\nMedicine Bottle");
                    etcost.setText(costalready + "\n4.4");
                }}
                cost += 4.4;
            }else if(selected.equals("Paracetamol")){
                if(!(already.contains("3"))){
                if(cost==0){
                    etmed.setText("Paracetamol");
                    etcost.setText("0.6");
                    already+="3";
                }else{
                    etmed.setText(medicinesalready + "\nParacetamol");
                    etcost.setText(costalready + "\n0.6");
                }}
                cost += 0.6;
            }else if(selected.equals("Digene")){
                if(!(already.contains("4"))){
                if(cost==0){
                    etmed.setText("Digene");
                    etcost.setText("1");
                    already+="4";
                }else{
                    etmed.setText(medicinesalready + "\nDigene");
                    etcost.setText(costalready + "\n1");
                }}
                cost += 1;
            }else if(selected.equals("Vicks")){
                if(!(already.contains("5"))){
                if(cost==0){
                    etmed.setText("Vicks");
                    etcost.setText("4.5");
                    already+="5";
                }else {
                    etmed.setText(medicinesalready + "\nVicks");
                    etcost.setText(costalready + "\n4.5");
                }}
                cost += 4.5;
            }
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
        Intent intent1 = new Intent(getApplicationContext(), remind.class);
        startActivity(intent1);
    }
    public void goToProfile(View view){
        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent1);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
/*
    @Override
    protected void onActivtiyResult(int requestCode, int  resultCode, Intent intent){
        if(requestCode==0){
            if(requestCode== CommonStatusCodes.SUCCESS){
                if(data != 0){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    scan_result.setText("Barcode Value:  "+barcode.displayValue);
                }else{
                    scan_result.setText("No Barcode Found")
                }
            }
        }else{
            scan_result.setText("No Barcode Found);
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }
*/
    public void makeToast(String text){
    Context context = getApplicationContext();
    int duration = Toast.LENGTH_SHORT;
    Toast.makeText(context,text,duration).show();
}
}

package com.example.happyhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;
    boolean all = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBMBFullName);
        edaddress = findViewById(R.id.editTextBMBAddress);
        edcontact = findViewById(R.id.editTextBMBContact);
        edpincode = findViewById(R.id.editTextBMBPincode);
        btnBooking = findViewById(R.id.buttonBMBBooking);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = intent.getStringExtra("date");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username", "").toString();

                all = CheckAll();

                if(all) {
                    Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                    db.addOrder(username, edname.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(), Integer.parseInt(edpincode.getText().toString()), date.toString(), "", Float.parseFloat(price[1].toString()), "medicine");
                    db.removeCart(username, "medicine");
                    Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
                }
            }

            private boolean CheckAll()
            {
                if(edname.length()==0)
                {
                    edname.setError("Please enter your Name");
                    return false;
                }
                if(edaddress.length()==0)
                {
                    edaddress.setError("Address is required");
                    return false;
                }
                if(edpincode.length()==0)
                {
                    edpincode.setError("Pin Code is required");
                    return false;
                } else if (edpincode.length()!=6) {
                    edpincode.setError("Pin Code should be valid");
                    return false;
                }
                if(edcontact.length()==0)
                {
                    edcontact.setError("Phone number is required");
                    return false;
                } else if (edcontact.length()!=10) {
                    edcontact.setError("Invalid Phone number");
                    return false;
                }

                return true;
            }
        });


    }
}
package com.example.happyhealth;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name : Rahul Sharma", "Hospital Address : Jaipur", "Exp : 5yrs", "Mobile No.: 9898989898", "600"},
                    {"Doctor Name : Ritik Bhardwaj", "Hospital Address : Aligarh", "Exp : 15yrs", "Mobile No.: 7878787878", "900"},
                    {"Doctor Name : Rajat Shrivastav", "Hospital Address : Allahabad", "Exp : 8yrs", "Mobile No.: 8898989898", "300"},
                    {"Doctor Name : Varad Naik", "Hospital Address : Nanded", "Exp : 6yrs", "Mobile No.: 9010901091", "800"},
                    {"Doctor Name : Arnav Patil", "Hospital Address : Nashik", "Exp : 7yrs", "Mobile No.: 9696969696", "500"}
            };

    private String[][] doctor_details2 =
            {
                    {"Doctor Name : Imtiyaz Khan", "Hospital Address : Hatim", "Exp : 5yrs", "Mobile No.: 9898989898", "600"},
                    {"Doctor Name : Mangalam Raj", "Hospital Address : Patna", "Exp : 15yrs", "Mobile No.: 7878787878", "900"},
                    {"Doctor Name : Prabal Pratap Singh", "Hospital Address : UP", "Exp : 8yrs", "Mobile No.: 8898989898", "300"},
                    {"Doctor Name : Frey Vegda", "Hospital Address : Rajkot", "Exp : 6yrs", "Mobile No.: 9010901091", "800"},
                    {"Doctor Name : Kalpesh Salve", "Hospital Address : Nashik", "Exp : 7yrs", "Mobile No.: 9696969696", "500"}
            };

    private String[][] doctor_details3 =
            {
                    {"Doctor Name : Sumer Jhakkad", "Hospital Address : Jaipur", "Exp : 5yrs", "Mobile No.: 9898989898", "600"},
                    {"Doctor Name : Abhishek Singh", "Hospital Address : Varanasi", "Exp : 15yrs", "Mobile No.: 7878787878", "900"},
                    {"Doctor Name : Shivam", "Hospital Address : Bihar", "Exp : 8yrs", "Mobile No.: 8898989898", "300"},
                    {"Doctor Name : Kashyap", "Hospital Address : Bihar", "Exp : 6yrs", "Mobile No.: 9010901091", "800"},
                    {"Doctor Name : Aryan", "Hospital Address : Bihar", "Exp : 7yrs", "Mobile No.: 9696969696", "500"}
            };

    private String[][] doctor_details4 =
            {
                    {"Doctor Name : Siddh Jain", "Hospital Address : Panipat", "Exp : 5yrs", "Mobile No.: 9898989898", "1100"},
                    {"Doctor Name : Pranav Chandak", "Hospital Address : Jalgaon", "Exp : 15yrs", "Mobile No.: 7878787878", "900"},
                    {"Doctor Name : Prateek Singh", "Hospital Address : Delhi", "Exp : 8yrs", "Mobile No.: 8898989898", "300"},
                    {"Doctor Name : Abhiraj Singh Rajput", "Hospital Address : Jabalpur", "Exp : 6yrs", "Mobile No.: 9010901091", "800"},
                    {"Doctor Name : Neil", "Hospital Address : Pune", "Exp : 7yrs", "Mobile No.: 9696969696", "500"}
            };

    private String[][] doctor_details5 =
            {
                    {"Doctor Name : Rohan Udhwani", "Hospital Address : Thane", "Exp : 5yrs", "Mobile No.: 9898989898", "600"},
                    {"Doctor Name : Nishchint Kamath", "Hospital Address : Mumbai", "Exp : 15yrs", "Mobile No.: 7878787878", "900"},
                    {"Doctor Name : Rajat Shrivastav", "Hospital Address : Prayagraj", "Exp : 8yrs", "Mobile No.: 8898989898", "300"},
                    {"Doctor Name : Pranav Chandak", "Hospital Address : Jalgaon", "Exp : 6yrs", "Mobile No.: 9010901091", "800"},
                    {"Doctor Name : Arnav Patil", "Hospital Address : Nashik", "Exp : 7yrs", "Mobile No.: 9696969696", "500"}
            };


    TextView tv;
    Button btn;
    String[][] doctor_details;
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewHADTitle);
        btn = findViewById(R.id.buttonHADBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician") == 0) {
            doctor_details = doctor_details1;
        } else if (title.compareTo("Dietician") == 0) {
            doctor_details = doctor_details2;
        } else if (title.compareTo("Dentist") == 0) {
            doctor_details = doctor_details3;
        } else if (title.compareTo("Surgeon") == 0) {
            doctor_details = doctor_details4;
        } else {
            doctor_details = doctor_details5;
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        //iske aage hi koi gadbad hai
        list = new ArrayList();
        for(int i = 0; i < doctor_details.length; i++){
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons Fees : " +doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
                );
        ListView lst = findViewById(R.id.listViewHA);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}
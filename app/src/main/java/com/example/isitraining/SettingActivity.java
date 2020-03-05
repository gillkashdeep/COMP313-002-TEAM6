package com.example.isitraining;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    String txtChangeLocation;
    String txtChangeCountry;

    Switch switchNotification;
    String isNotiOn;

    EditText etChangeLocation;
    EditText etChangeCountry;
    Button btnSaveChanges,btnlangChange;
    RadioGroup radioGroup;
    RadioButton radioButton;

    Double tempValue;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // set toolbar title
        Toolbar tbSet = findViewById(R.id.tbSet);
        setSupportActionBar(tbSet);
        getSupportActionBar().setTitle("Setting");

        switchNotification = findViewById(R.id.switchNotification);
        radioGroup = findViewById(R.id.GrpCtoF);
        etChangeLocation = findViewById(R.id.etChangeLocation);
        etChangeCountry = findViewById(R.id.etChangeCountry);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);
        btnlangChange = (Button)findViewById(R.id.btnlangChanges);
        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchNotification.isChecked()){
                    isNotiOn = "t";
                }else {
                    isNotiOn = "f";
                    switchNotification.setChecked(false);
                }

                int selectedId = radioGroup.getCheckedRadioButtonId();

                radioButton = (RadioButton) findViewById(selectedId);
                radioButton.getText();
                System.out.println("+++++++"+radioButton.getText());

                Intent intent = getIntent();
                String str = intent.getStringExtra("temp_val");
                SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
                String value = sharedPreferences.getString("value","");
                double temp_val = Double.valueOf(value);

                if(radioButton.getText().equals("F"))
                {
                  tempValue = temp_val *9/5+32;
                }
                else if(radioButton.getText().equals("C"))
                {
                    tempValue =(temp_val-32)*9/5;
                }
                else {
                    tempValue = temp_val;
                }
                txtChangeLocation = etChangeLocation.getText().toString();
                txtChangeCountry = etChangeCountry.getText().toString();


                if(!txtChangeLocation.equals("") && !txtChangeCountry.equals("") && !radioButton.equals("")){
                    String result = isNotiOn + txtChangeLocation + "," + txtChangeCountry + ","+tempValue;

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("result", result);

                    setResult(RESULT_OK, resultIntent);
                    finish();
                }else {
                    Toast.makeText(SettingActivity.this, "Please fill all the blank", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnlangChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Fragment.class);
                v.getContext().startActivity(intent);
            }
        }
        );


    }



    public void goToAnActivity(View view) {
        Intent intent = new Intent(this, Fragment.class);
        startActivity(intent);
    }
}

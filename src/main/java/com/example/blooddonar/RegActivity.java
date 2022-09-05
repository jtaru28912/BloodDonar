package com.example.blooddonar;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.view.*;
import android.content.*;

public class RegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
  final EditText Name=(EditText) findViewById(R.id.editText3);
        final EditText Mobile=(EditText) findViewById(R.id.editText4);
        final Spinner BG=(Spinner) findViewById(R.id.spinner);
        final Spinner City=(Spinner) findViewById(R.id.spinner2);
        Button save =(Button) findViewById(R.id.button5);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData obj=new UserData();
                obj.Name=Name.getText().toString();
                obj.Mobile=Mobile.getText().toString();
                obj.BG=BG.getSelectedItem().toString();
                obj.City=City.getSelectedItem().toString();
                if(obj.Name.length()==0||obj.Mobile.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Value cannot be empty",Toast.LENGTH_SHORT).show();
                    return;
                }


                //write message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("/test/data/"+obj.Mobile);

                myRef.setValue(obj);
                Toast.makeText(getApplicationContext(),"Record saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    }


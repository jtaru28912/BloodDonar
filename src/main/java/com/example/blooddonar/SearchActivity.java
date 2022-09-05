package com.example.blooddonar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.*;
import java.util.ArrayList;
public class SearchActivity extends AppCompatActivity {
    ArrayList x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        final GridView gv=(GridView) findViewById(R.id.gridview1);
        Button b1=(Button)findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner s1=(Spinner) findViewById(R.id.spinner3);
                Spinner s2=(Spinner) findViewById(R.id.spinner4);
                final String bg=s1.getSelectedItem().toString();
                final String city=s2.getSelectedItem().toString();

                x=new ArrayList();
                x.add("Name");
                x.add("Mobile");


                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference("/test/data/");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        long count=dataSnapshot.getChildrenCount();
                        Toast.makeText(getApplicationContext(),"no of records:"+count,Toast.LENGTH_SHORT).show();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            UserData dd = child.getValue(UserData.class);
                            if(bg.equals(dd.BG)&&city.equals(dd.City)) {
                                x.add(dd.Name);
                                x.add(dd.Mobile);
                            }
                        }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, x);
                            gv.setAdapter(adapter);

                        }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }


        });
    }
}

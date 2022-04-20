package com.schoolmanagement;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.schoolmanagement.Parents.ParentsHomeActivity;
import com.schoolmanagement.Student.HomeActivity;
import com.schoolmanagement.Teachers.Activity.TeachersHomeActivity;
import com.schoolmanagement.Transport.HomeTransportActivity;


public class MainActivity extends AppCompatActivity {

    TextView login;
    EditText username,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(TextView)findViewById(R.id.login);
        username=(EditText)findViewById(R.id.username);
        mobile=(EditText)findViewById(R.id.mobile);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String name = username.getText().toString().trim();
                if(!name.isEmpty()){

                    if(name.equals("student")){
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(i);
                    } else if(name.equals("teachers")){
                        Intent i = new Intent(MainActivity.this, TeachersHomeActivity.class);
                        startActivity(i);
                    }else if(name.equals("parents")){
                        Intent i = new Intent(MainActivity.this, ParentsHomeActivity.class);
                        startActivity(i);
                    }else if(name.equals("transport")){

                        Intent i = new Intent(MainActivity.this, HomeTransportActivity.class);
                        startActivity(i);

                    }else {
                        Toast.makeText(MainActivity.this, "UserName Not Match", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Please Enter UserName And Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

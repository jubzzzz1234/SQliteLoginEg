package com.example.jubin.sqlitelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_username;
    EditText edt_password;
    Button btn_login;
    TextView txt_reg;
    DBHelper helper=new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_username=(EditText)findViewById(R.id.login_user);
        edt_password=(EditText)findViewById(R.id.login_password);
        btn_login=(Button)findViewById(R.id.login_btn);
        txt_reg=(TextView)findViewById(R.id.login_reg);

        txt_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Reg.class);
            startActivity(i);}
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password=helper.searchpass(edt_username.getText().toString());
                if(edt_password.getText().toString().equals(password))
                {
                    Intent j=new Intent(MainActivity.this,LoggedInPage.class);
                    startActivity(j);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Details not matching...", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}

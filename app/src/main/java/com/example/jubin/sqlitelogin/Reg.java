package com.example.jubin.sqlitelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Reg extends AppCompatActivity {
    EditText edt_regname;
    EditText edt_regpassword;
    EditText edt_regcpassword;
    Button btn_reg;
    DBHelper helper=new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        edt_regname=(EditText)findViewById(R.id.reg_name);
        edt_regpassword=(EditText)findViewById(R.id.reg_password);
        edt_regcpassword=(EditText)findViewById(R.id.reg_cpassword);
        btn_reg=(Button)findViewById(R.id.btn_reg);

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_regpassword.getText().toString().equals(edt_regcpassword.getText().toString())) {

                    Details d=new Details();
                    d.setName(edt_regname.getText().toString());
                    d.setPassword(edt_regpassword.getText().toString());

                    helper.insertDetails(d);
                    Toast.makeText(Reg.this, "Registered", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Reg.this, MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Reg.this, "Passwords does not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

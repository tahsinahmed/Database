package com.example.anik.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity implements View.OnClickListener {

    private DBhelper db;
    private EditText text1,text2;
    private TextView tv;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        db=new DBhelper(this);
        tv=(TextView)findViewById(R.id.textView2);
        text1=(EditText)findViewById(R.id.editText4);
        text2=(EditText)findViewById(R.id.editText5);

        btn=(Button)findViewById(R.id.button2);


        tv.setOnClickListener(this);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button2:
                register();
                break;
            case R.id.textView2:
                startActivity(new Intent(RegisterPage.this,LoginPage.class));
                finish();
                break;
            default:
        }
    }

    private void register(){
        String email=text1.getText().toString();
        String pass=text2.getText().toString();

        if(email.isEmpty() || pass.isEmpty())
        {
            displayToast("username/password field empty");
        }
        else{
            db.addUser(email,pass);

            displayToast("Registration Successful");
            finish();
        }

    }
    private void displayToast(String massage){
        Toast.makeText(getApplicationContext(),massage,Toast.LENGTH_SHORT).show();
    }
}

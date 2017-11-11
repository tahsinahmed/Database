package com.example.anik.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    private EditText text1,text2;
    private Button btn,btn2;
    private DBhelper db;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        session=new Session(this);
        db=new DBhelper(this);
        text1=(EditText)findViewById(R.id.editText);
        text2=(EditText)findViewById(R.id.editText2);
        btn=(Button)findViewById(R.id.button);
        btn2=(Button)findViewById(R.id.button4);

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);


        if(session.loggedIn()){
            startActivity(new Intent(LoginPage.this,Main3Activity.class));
            finish();
        }
        else {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                login();
                break;
            case R.id.button4:
                startActivity(new Intent(LoginPage.this,RegisterPage.class));
                break;
            default:
        }
    }
    private void login(){
        String email=text1.getText().toString();
        String pass=text2.getText().toString();

        if(email.isEmpty() || pass.isEmpty())
        {
            displayToast("username/password field empty");
        }
        else if(db.getUser(email,pass)){
            session.setLoggedIn(true);
            startActivity(new Intent(LoginPage.this,Main3Activity.class));
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Wrong email or password",Toast.LENGTH_SHORT).show();
        }

    }
    private void displayToast(String massage){
        Toast.makeText(getApplicationContext(),massage,Toast.LENGTH_SHORT).show();
    }
}

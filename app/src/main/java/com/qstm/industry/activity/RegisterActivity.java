package com.qstm.industry.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qstm.industry.Model.WebService;
import com.qstm.industry.R;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    private static Handler handler = new Handler();
    private String info;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mrPasswordView;
    private EditText mLoginView;
    private EditText mPhoneView;
    private ImageView mRegisterImageView;

    private String username;
    private String password;
    private String rpassword;
    private String email;
    private String telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        mEmailView = (EditText) findViewById(R.id.email);
        mrPasswordView = (EditText) findViewById(R.id.rpassword);
        mPasswordView = (EditText) findViewById(R.id.register_password);
        mLoginView = (EditText) findViewById(R.id.register_username);
        mPhoneView = (EditText) findViewById(R.id.telephone);
        mRegisterImageView = (ImageView)findViewById(R.id.register_imageView);

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour>=6&&hour<=18){
            mRegisterImageView.setImageResource(R.drawable.good_morning_img);
        } else {
            mRegisterImageView.setImageResource(R.drawable.good_night_img);
        }

        Button mEmailRegisterInButton = (Button) findViewById(R.id.bt_register_in);
        mEmailRegisterInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        Button mRegisterCancelButton = (Button) findViewById(R.id.bt_register_cancel);
        mRegisterCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void attemptRegister() {

        username = mLoginView.getText().toString();
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        rpassword = mrPasswordView.getText().toString();
        telephone = mPhoneView.getText().toString();
        if(checkInput()) {
            new Thread(new MyThread()).start();
        }
    }

    public class MyThread implements Runnable {
        @Override
        public void run() {
            info = WebService.registerHttpGet("RegLet", username, password, email, telephone);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(info.equals("注册失败")){
                        Toast.makeText(RegisterActivity.this,"账号已被注册",Toast.LENGTH_SHORT).show();
                    } else if(info.equals("注册成功")){
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("USERNAME",username);
                        intent.putExtra("PASSWORD",password);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                }
            });
        }
    }

    private Boolean checkInput(){
        if(!(rpassword.equals(password))){
            Toast.makeText(RegisterActivity.this,"两次密码输入不一致",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!isEmail(email)){
            Toast.makeText(RegisterActivity.this,"请输入正确的邮箱",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!isPhone(telephone)){
            Toast.makeText(RegisterActivity.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static boolean isPhone(String inputText) {
        Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher m = p.matcher(inputText);
        return m.matches();
    }

    //判断email格式是否正确
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
}
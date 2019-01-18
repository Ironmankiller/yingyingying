package com.qstm.industry.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qstm.industry.Model.WebService;
import com.qstm.industry.R;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    private String info;
    private static Handler handler = new Handler();

    private static final String TAG = "LoginActivity";
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private ImageView imageView;
    private TextView textView;
    private EditText mUsername;
    private EditText mPassword;

    private SharedPreferences login_sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);


        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        login_sp = getSharedPreferences("userInfo", 0);
        Boolean loginFlag = login_sp.getBoolean("LOG_STATE", false);
        if(loginFlag){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if(hour>=6&&hour<=18){
            imageView.setImageResource(R.drawable.good_morning_img);
            textView.setText("Morning");
        } else {
            imageView.setImageResource(R.drawable.good_night_img);
            textView.setText("Night");
        }

        Button registerInButton = (Button) findViewById(R.id.bt_register);
        if (registerInButton != null) {
            registerInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent regItn = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivityForResult(regItn,1);
                }
            });
        }


        Button mSignInButton = (Button) findViewById(R.id.bt_sign_in);
        if (mSignInButton != null) {
            mSignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checkNetwork()) {
                        attemptLogin();
                    } else {
                        Toast.makeText(LoginActivity.this,"网络未连接",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void attemptLogin() {


        new Thread(new MyThread()).start();
    }


    public class MyThread implements Runnable {
        @Override
        public void run() {
            info = WebService.loginHttpGet("LogLet",mUsername.getText().toString(), mPassword.getText().toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(info.equals("服务器连接超时...")){
                        Toast.makeText(LoginActivity.this,"服务器连接超时...",Toast.LENGTH_SHORT).show();
                    } else if(info.equals("登录成功")){
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = login_sp.edit();
                        editor.putBoolean("LOG_STATE",true);
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if(info.equals("登录失败")){
                        Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    mUsername.setText(data.getStringExtra("USERNAME"));
                    mPassword.setText(data.getStringExtra("PASSWORD"));
                }
        }
    }

    // 检测网络
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isConnected();
        }
        return false;
    }

}

package com.xcp.client;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xcp.neihan.IUserInfoAidlInterface;

public class MainActivity extends AppCompatActivity {
    private Button btnName;
    private Button btnPassword;
    private ServiceConnection conn;
    private IUserInfoAidlInterface iUserInfoAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnName = (Button) findViewById(R.id.button);
        btnPassword=(Button) findViewById(R.id.button2);
        //启动服务
        Intent intent=new Intent("com.xcp.neihan.MessageService");
        intent.setPackage("com.xcp.neihan");
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iUserInfoAidlInterface = IUserInfoAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(intent, conn,BIND_AUTO_CREATE);


        //设置监听

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   Toast.makeText(MainActivity.this, iUserInfoAidlInterface.getUserName(), Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(MainActivity.this, iUserInfoAidlInterface.getPassword(), Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

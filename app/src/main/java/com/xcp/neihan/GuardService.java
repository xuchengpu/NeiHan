package com.xcp.neihan;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * 此服务开在另外一个单独的进程中即守护进程，与我们的目标服务相互绑定，相互监听、重启。
 */
public class GuardService extends Service {


    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(GuardService.this, "GuardService与MessageService绑定上了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //4、断开就重新绑定
            bindService(new Intent(GuardService.this, MessageService.class), conn, BIND_AUTO_CREATE);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        //1、打印线程，用于判断服务是否还是活着的
        new Thread() {
            public void run() {
                while(true) {
                    Log.e("TAG", "---------》GuardService在运行");
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        bindService(new Intent(this,MessageService.class),conn,BIND_AUTO_CREATE);
        return  binder;
    }

    private ProcessGuard.Stub binder = new ProcessGuard.Stub() {
    };

}

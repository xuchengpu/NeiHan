package com.xcp.neihan;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MessageService extends Service {
    private final int MessageId=1;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MessageService.this, "MessageService绑定了GuardService", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //4、断开就重新绑定
            bindService(new Intent(MessageService.this, GuardService.class), conn, BIND_AUTO_CREATE);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //1、打印线程，用于判断服务是否还是活着的
        new Thread() {
            public void run() {
                while(true) {
                    Log.e("TAG", "MessageService在运行");
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        //2、一进来就绑定守护进程
        bindService(new Intent(this, GuardService.class), conn, BIND_AUTO_CREATE);
        //开启定时查询功能
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            startService(new Intent(this,JobGuardService.class));
        }
        //3、置为前台进程，提高优先级
        Notification notification = new NotificationCompat.Builder(this)
                .setSubText("subtext")
                .setWhen(System.currentTimeMillis())
                .setContentTitle("标题")
                .setContentText("内容")
//                .setContentText("新农村垃圾分类决定是否了解到上课了解放了时代峻峰开始的发了的设计费劳动力肯德基富兰克林")
                .setSmallIcon(R.drawable.home_cartoon)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.home_cartoon))
                .setAutoCancel(true)
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .setLights(Color.WHITE, 1000, 1000)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.home_cartoon)))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        startForeground(MessageId,new Notification());
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    //    private IUserInfoAidlInterface.Stub binder = new IUserInfoAidlInterface.Stub() {
//
//        @Override
//        public String getUserName() throws RemoteException {
//            return "微检";
//        }
//
//        @Override
//        public String getPassword() throws RemoteException {
//            return "123456";
//        }
//    };
    private ProcessGuard.Stub binder = new ProcessGuard.Stub() {
    };
}

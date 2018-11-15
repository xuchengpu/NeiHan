package com.xcp.neihan;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobGuardService extends JobService {
    private final  int jobId=2;
    private final int MessageId=3;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //1、开启一个轮训
        JobInfo jobInfo = new JobInfo.Builder(jobId, new ComponentName(this, JobGuardService.class))
                .setPeriodic(1000)
                .build();

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
        //2、置为前台进程，提高优先级
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
    public boolean onStartJob(JobParameters params) {
        //3、查询服务是否还在，如果不在就重新启动
        boolean serviceAlive = serviceAlive(MessageService.class.getName());
        Log.e("TAG", "JobGuardService查询结果为"+serviceAlive);
        if(!serviceAlive) {
            //4、断开就重新绑定
            Log.e("TAG", "JobGuardService重新开启了服务");
            startService(new Intent(JobGuardService.this, MessageService.class));
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    /**
     * 判断某个服务是否正在运行的方法
     * @param serviceName
     *            是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    private boolean serviceAlive(String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(100);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }
}

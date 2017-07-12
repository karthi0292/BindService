package service.bound.com.boundservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MyService extends Service {
    public MyService() {
    }

    private final IBinder iBinder=new MyLocalBinder();
    private final Random iGenarator=new Random();
    private MediaPlayer player=new MediaPlayer();


    @Override
    public void onCreate() {
        super.onCreate();

       /* Thread backGroundThread=new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        backGroundThread.start();*/
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return iBinder;
    }

    public class MyLocalBinder extends Binder {

        MyService getService(){
            return MyService.this;
        }
    }

    public int getRandom(){
        return iGenarator.nextInt(300);
    }

    public void startPlay(File file){
        try {
            player.setDataSource(file.getPath().toString());
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopPlay(){
        player.stop();
    }
}

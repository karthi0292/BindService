package service.bound.com.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button btnRandom;
    private MyService service;
    private boolean isBound=false;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRandom=(Button)findViewById(R.id.btn_random);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MyService.MyLocalBinder binder= (MyService.MyLocalBinder) iBinder;
                service=binder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };

        Intent intent=new Intent(this,MyService.class);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,service.getRandom()+"",Toast.LENGTH_LONG).show();

                File file= new File(Environment.getExternalStorageDirectory()+"/Sample");
                if(file.isDirectory()){
                    File[]files=file.listFiles();
                    service.startPlay(files[0]);
                }
            }
        });


    }
}

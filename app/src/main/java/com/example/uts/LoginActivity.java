package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextTextPersonName, getEditTextTextPassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        Button btnDaftar = (Button) findViewById(R.id.btnDaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,DaftarActivity.class);
                startActivity(intent);
            }
        });

        Button btnMasuk = (Button) findViewById(R.id.btnMasuk);
        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        getEditTextTextPassword2 = (EditText) findViewById(R.id.editTextTextPassword2);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextTextPersonName.getText().toString().trim().isEmpty()){
                    Toast.makeText(LoginActivity.this,"Username tidak boleh kosong",Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(LoginActivity.this,HomePageActivity.class);
                    startActivity(intent);
                    tampilNotifikasi();
                }
            }
        });
    }

    private void tampilNotifikasi(){
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID="";

        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CHANNEL_ID = "my_channel_01";
            CharSequence name = "my channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mchannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mchannel.setDescription(Description);
            mchannel.enableLights(true);
            mchannel.setLightColor(Color.RED);
            mchannel.enableVibration(true);
            mchannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mchannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mchannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"my_channel_01");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Berhasil Login");
        builder.setContentText("Selamat Datang Vinson");
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this, LoginActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(234,builder.build());
    }
}
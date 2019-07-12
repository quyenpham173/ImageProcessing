package com.example.builddewarp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quyenpham.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static com.example.quyenpham.R.id.activity_main;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    final CharSequence xin_chao = "Xin chào, vui lòng lựa chọn rồi nhấn vào nút Camera để chụp ảnh";
    String utteranceId = UUID.randomUUID().toString();
    Button btnCamera;
    private ArrayList<SpinnerItem> mLanguage;
    private ArrayList<SpinnerItem> mTime;
    private SpinnerAdapter mAdapter;
    Spinner spnLanguage;
    Spinner spnTime;
    TextView usage;
    private static int time = 0;
    private static String language = null;
    private static String clickItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout mh = (RelativeLayout) findViewById(activity_main);
        mh.setBackgroundResource(R.drawable.anh1);
        spnLanguage = findViewById(R.id.spn_language);
        spnTime = findViewById(R.id.spn_time);
        usage = findViewById(R.id.tv_usage);
        checkPermissions();
        initList();

        mAdapter = new SpinnerAdapter(this, mLanguage);
        spnLanguage.setAdapter(mAdapter);
        mAdapter = new SpinnerAdapter(this, mTime);
        spnTime.setAdapter(mAdapter);

        OnClick(spnLanguage);
        OnClick(spnTime);
        usage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UsageActivity.class);
                startActivity(intent);
            }
        });

        final Locale loc = new Locale("vi");
        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(loc);
                    textToSpeech.speak(xin_chao, TextToSpeech.QUEUE_FLUSH,null, utteranceId);
                }
            }
        });
        btnCamera = findViewById(R.id.btn_camera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.stop();
                Intent intent = new Intent(MainActivity.this, CaptureImage.class);
                startActivity(intent);
            }
        });
/*        RelativeLayout relativeLayout = (RelativeLayout) findViewById(activity_main);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textToSpeech.stop();
                Intent intent = new Intent(MainActivity.this, CaptureImage.class);
                startActivity(intent);

            }
        });*/
    }

    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };

    private void checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
        }
    }

    public void OnClick(Spinner spinner){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem langItem = (SpinnerItem) parent.getItemAtPosition(position);
                clickItem = langItem.getmLanguage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "Vui lòng chọn lại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String getLang(){
        if (clickItem.equals("Tiếng Việt"))
            language = "vie";
        else language = "eng";
        return language;
    }

    public static int getTimer(){
        switch (clickItem){
            case "5 giây":
                time = 5000;
                break;
            case "4 giây":
                time = 4000;
                break;
            case "3 giây":
                time = 3000;
                break;
            case "2 giây":
                time = 2000;
        }
        return time;
    }

    private void initList(){
        mTime = new ArrayList<>();
        mLanguage = new ArrayList<>();
        mLanguage.add(new SpinnerItem("Tiếng Việt"));
        mLanguage.add(new SpinnerItem("English"));
        mTime.add(new SpinnerItem("5 giây"));
        mTime.add(new SpinnerItem("4 giây"));
        mTime.add(new SpinnerItem("3 giây"));
        mTime.add(new SpinnerItem("2 giây"));
    }
}

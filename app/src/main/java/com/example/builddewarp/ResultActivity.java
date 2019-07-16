package com.example.builddewarp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quyenpham.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

import static com.example.quyenpham.R.id.activity_result;

public class ResultActivity extends AppCompatActivity {
    public static final String ROOT_FOLDER = "Reading Assistance";
    public static final String FILE_TXT = "Page_";
    private TextToSpeech textToSpeech;
    TextView tvResult;
    Button btnNext;
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        RelativeLayout mh = (RelativeLayout) findViewById(activity_result);
        tvResult = findViewById(R.id.tv_result);
        btnNext = findViewById(R.id.btn_Next);

        //mh.setBackgroundResource(R.drawable.anh2);

        Bundle bundle = getIntent().getExtras();

        assert bundle != null;
        final String fileImage = bundle.getString("Image");
        //Log.d("Quyen", fileImage);
        //assert content != null;
        //final CharSequence charSequence = new StringBuffer(content);
        File imgFile = new  File(fileImage);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tvResult.setText("");
                }
            }, 5000);
            new ImageToText(ResultActivity.this, tvResult).execute(myBitmap);
            content = tvResult.getText().toString();
        }
        tvResult.setMovementMethod(new ScrollingMovementMethod());

        //RelativeLayout relativeLayout = (RelativeLayout) findViewById(activity_result);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
                builder.setTitle("Bạn có muốn lưu lại nội dung sách");
                builder.setMessage("Chọn có để lưu và thoát, chọn không để thoát và chụp ảnh khác");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FileOutputStream fos = null;
                        File textFile = createTextFile();
                        try {
                            fos = new FileOutputStream(textFile);
                            fos.write(content.getBytes());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            if (fos!=null){
                                try {
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        Intent intent = new Intent(ResultActivity.this, CaptureImage.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ResultActivity.this, CaptureImage.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
    private File createTextFile() {
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
                File.separator + ROOT_FOLDER;
        File dir = new File(dirPath);
        if (!dir.exists())
            dir.mkdirs();
        String fileName = FILE_TXT + System.currentTimeMillis() + ".txt";
        return new File(dir, fileName);
    }


    @Override
    protected void onPause() {
        if (textToSpeech != null){
            textToSpeech.stop();
        }
        super.onPause();
    }
}


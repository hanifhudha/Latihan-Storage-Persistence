package com.hanifhudha.latihanstorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ExternalStorageActivity extends AppCompatActivity {

    private String keyExternal = "KEY_EXTERNAL_STORAGE";
    private String FILE_NAME = "nama_file.txt";
    public static final int REQUEST_CODE_STORAGE = 100;
    private TextView txtIsiFile;
    private  TextView inputTxtIsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        txtIsiFile = findViewById(R.id.txtTampilex);
        inputTxtIsi = findViewById(R.id.editTxtKalimatex);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String newTitle = extras.getString(keyExternal);
            setTitle(newTitle);
        }
    }

    private boolean checkPermissionStorage(){
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
                return false;
            }
        } else
            return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    actionBuatFileExt(null);
                    actionBacaFileExt(null);
                    actionUbahFileExt(null);
                    actionHapusFileExt(null);
                }
                break;
        }
    }

    public void actionBuatFileExt(View view) {
//        String isiFile = "Coba Isi Data File Text";
        String input = inputTxtIsi.getText().toString();
        String state = Environment.getExternalStorageState();
        if (!input.isEmpty()) {
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(input.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            txtIsiFile.setText("File Dibuat!");
        }} else {
            Toast.makeText(this, "Input text kosong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void actionBacaFileExt(View view) {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, FILE_NAME);

        if(file.exists()) {

            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();

                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
            txtIsiFile.setText(text.toString());
        } else {
            txtIsiFile.setText("File Belum diBuat!");
        }
    }

    public void actionUbahFileExt(View view) {
//        String ubah = "Update Isi Data File Text";
        String input = inputTxtIsi.getText().toString();
        String state = Environment.getExternalStorageState();
        if (!input.isEmpty()) {
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(input.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            txtIsiFile.setText("File Berhasil Diubah!");
        }} else {
            Toast.makeText(this, "Input text kosong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void actionHapusFileExt(View view) {
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);
        if (file.exists()) {
            file.delete();
            txtIsiFile.setText("File Dihapus!");
        }
    }
}
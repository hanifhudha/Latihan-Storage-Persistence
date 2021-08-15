package com.hanifhudha.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalStorageActivity extends AppCompatActivity {

    private String keyInternal = "KEY_INTERNAL_STORAGE";
    private String FILE_NAME = "nama_file.txt";
    private TextView txtIsiFile;
    private EditText edtText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        txtIsiFile = findViewById(R.id.txtTampil);
        edtText = findViewById(R.id.editTxtKalimat);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String newTitle = extras.getString(keyInternal);
            setTitle(newTitle);
        }

    }

    public void actionBuatFile(View view) {
//        String isiFile = "Membuat file baru";
        String input = edtText.getText().toString();
        if (!input.isEmpty()) {
        File file = new File(getFilesDir(), FILE_NAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(input.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        txtIsiFile.setText("File Dibuat!");
        } else {
            Toast.makeText(this, "Input text kosong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void actonUbahFile(View view) {
//        String ubah = "Update Isi Data File Text";
        String ubah = edtText.getText().toString();

        if (!ubah.isEmpty()) {
            File file = new File(getFilesDir(), FILE_NAME);

            FileOutputStream outputStream = null;
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file, false);
                outputStream.write(ubah.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            txtIsiFile.setText("File Diubah!");
        } else {
            Toast.makeText(this, "Input text kosong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void actionBacaFile(View view) {
        File sdcard = getFilesDir();
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

    public void actionHapusFile(View view) {
        File file = new File(getFilesDir(), FILE_NAME);
        if (file.exists()) {
            file.delete();
            txtIsiFile.setText("File Dihapus!");
        }
    }
}
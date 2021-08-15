package com.hanifhudha.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private String keyInternal = "KEY_INTERNAL_STORAGE";
    private String keyExternal = "KEY_EXTERNAL_STORAGE";
//    private Button btnIntStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void actionInternalStorage(View view) {
        Intent internal = new Intent(this, InternalStorageActivity.class);
        internal.putExtra(keyInternal, "Internal Storage");
        startActivity(internal);
    }

    public void actionExternalStorage(View view) {
        Intent external = new Intent(this, ExternalStorageActivity.class);
        external.putExtra(keyExternal, "External Storage");
        startActivity(external);
    }
}
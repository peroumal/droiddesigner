package io.github.rayanperoumal.droiddesigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import java.io.File;

import io.github.rayanperoumal.droiddesigner.git.Clone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Clone("https://github.com/rayanperoumal/droiddesigner.git"){
            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Toast.makeText(MainActivity.this,getMessage(integer),Toast.LENGTH_LONG).show();
            }
        }.execute(this);
    }
}

package io.github.rayanperoumal.droiddesigner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class ProjectActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_files:
                    return true;
                case R.id.navigation_code:
                    return true;
                case R.id.navigation_graphics:
                    return true;
                case R.id.navigation_manage:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(getIntent().hasExtra("path")){
            String path = getIntent().getStringExtra("path");
            Log.i("Project:path:",path);
            File file = new File(path);
            displayFiles(file);
        }
    }

    public void displayFiles(File file){
        if(file.exists()){
            File[] list= file.listFiles();
            for(int i=0;i<list.length;i++){
                try {
                    Log.i("Project:found:",list[i].getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

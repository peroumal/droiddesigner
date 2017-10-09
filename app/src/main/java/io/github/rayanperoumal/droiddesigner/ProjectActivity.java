package io.github.rayanperoumal.droiddesigner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import io.github.rayanperoumal.droiddesigner.file.FileListView;

public class ProjectActivity extends AppCompatActivity {
    public static File parent;
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
    public static void setFragment(FragmentManager fm, Fragment fragment) {
        fm.beginTransaction()
                .replace(R.id.content, fragment).commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(getIntent().hasExtra("path")){
            String path = getIntent().getStringExtra("path");
            Log.i("Project:path:",path);
            parent = new File(path);
            setFragment(getSupportFragmentManager(),new FileListFragment());
        }

    }


    public static class FileListFragment extends Fragment{
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return new FileListView(getActivity(),parent);
        }
    }


}

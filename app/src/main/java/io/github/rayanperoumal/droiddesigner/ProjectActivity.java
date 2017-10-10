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

import java.io.File;

import io.github.rayanperoumal.droiddesigner.file.FileRecyclerView;

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


    public static class FileListFragment extends Fragment {
        FileRecyclerView view;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            view =  new FileRecyclerView(getActivity(),parent);
            return view;
        }

        @Override
        public void onResume() {
            super.onResume();
            view.setOnFileSelected(file ->{
                if(file.isDirectory()) listFiles(file);
                else Log.i("File:selected","No action for this file");
            });

        }

        /**
         * List in RecyclerView the files present in an parent file
         * @param parent File used for display his own sub files, need {@link File#isDirectory()} is true for it works })
         */
        public void listFiles(File parent){
            if(parent.isDirectory()){
                view.syncFiles(parent);
            }
        }
    }


}

package io.github.rayanperoumal.droiddesigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

import io.github.rayanperoumal.droiddesigner.git.Clone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File f = new File(getFilesDir(),"/repo49/");
        new Clone(f).execute("https://github.com/rayanperoumal/droiddesigner.git");
    }
}

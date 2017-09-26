package io.github.rayanperoumal.droiddesigner.git;

import android.os.AsyncTask;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

/**
 * Created by r.peroumal on 26/09/2017.
 */

public class Clone extends AsyncTask<String,Integer,String>{
    File dir;
    public Clone(File directory){
        this.dir = directory;
    }
    @Override
    protected String doInBackground(String... strings) {
        dir.mkdirs();
        try {
            Git git = Git.cloneRepository()
                    .setURI( "https://github.com/rayanperoumal/droiddesigner.git" )
                    .setDirectory(dir)
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
        return strings[0];
    }
}

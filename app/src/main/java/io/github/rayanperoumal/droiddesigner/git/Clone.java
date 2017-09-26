package io.github.rayanperoumal.droiddesigner.git;

import android.os.AsyncTask;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

/**
 * Created by r.peroumal on 26/09/2017.
 */

public class Clone extends AsyncTask<File,Integer,Boolean>{
    public final String repository;
    public Clone(String repository){
        this.repository = repository;
    }

    public String getRepoName(){
        int start=0, end=0;
        for(int i=0; i<repository.length();i++){
            switch(repository.charAt(i)){
                case '/': start=i;break;
                case '.': end=i;break;
            }
        }
        return repository.substring(start+1,end);
    }

    @Override
    protected Boolean doInBackground(File... files) {
        files[0].mkdirs();
        File dir = new File(files[0],getRepoName());
        if(dir.exists()) return false;
        try {
            Git git = Git.cloneRepository()
                    .setURI( repository )
                    .setDirectory(dir)
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

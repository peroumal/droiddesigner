package io.github.rayanperoumal.droiddesigner.git;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.File;

/**
 * Created by r.peroumal on 26/09/2017.
 */

public class Clone extends AsyncTask<Context,Integer,Integer>{
    public final String repository;
    public static final int NO_CODE =-1;
    public static final int UNKNOW_ERROR =0;
    public static final int REPO_EXIST =1;
    public static final int REPO_CLONED =2;
    public File dir;

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
    protected Integer doInBackground(Context... contexts) {
        File root = new File(contexts[0].getFilesDir(),"/repositories/");
        root.mkdirs();
        dir = new File(root,getRepoName());
        if(dir.exists()){
            return REPO_EXIST;
        }
        try {
            Git git = Git.cloneRepository()
                    .setURI( repository )
                    .setDirectory(dir)
                    .call();
        } catch (GitAPIException e) {
            e.printStackTrace();
            return UNKNOW_ERROR;
        }
        return REPO_CLONED;
    }

    public String getMessage(int code){
        switch (code){

            case REPO_EXIST: return "Le repository "+getRepoName()+" est déja présent sur votre appareil";
            case REPO_CLONED: return "Le repository "+getRepoName()+" a été cloné avec succès !";
            case UNKNOW_ERROR:
                default: return "Impossible de cloner le repository "+getRepoName()+", Erreur Reseau ?";
        }
    }




}

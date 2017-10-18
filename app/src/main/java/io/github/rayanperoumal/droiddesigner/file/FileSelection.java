package io.github.rayanperoumal.droiddesigner.file;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import java.io.File;

/**
 * Created by r.peroumal on 17/10/2017.
 */

public class FileSelection {
    private File[] files;

    public FileSelection(File[] files){
        this.files = files;
    }

    public File[] getFiles() {
        return files;
    }

    public static FileSelection[] toArraySelection(File parent) {
        File[] files = parent.listFiles();
        FileSelection[] selections =  new FileSelection[files.length];
        for(int i=0;i<selections.length;i++){
            FileSelection selection = new FileSelection(new File[]{files[i]});
            selection.setName(files[i].getName());
        }
        return selections;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public String getName() {
        if(name!=null)return name;
        else if (files.length==1) return files[0].getName();
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;
}

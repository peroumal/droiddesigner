package io.github.rayanperoumal.droiddesigner.file;


import android.os.Bundle;

import java.io.File;

/**
 * @author rayan peroumal
 */

public class FileSelection {
    private String[] paths;
    public String name;
    private FileSelectionOpener opener;
    private FileSelectionListener listener;

    public FileSelection(){
        this.paths = new String[]{};
        opener = null;
    }

    public FileSelection(String[] paths){
        this();
        if(paths!=null) this.paths = paths;
    }

    public FileSelectionOpener open(FileSelection selection, int layout){
        if(listener!=null)listener.onSelected(selection);
        if(opener !=null) return opener;
        return null;
    }

    public void setOnFilectionSelectionListener(FileSelectionListener listener){
        this.listener = listener;
    }

    public void setOpener(FileSelectionOpener editor){
        this.opener = editor;
        Bundle b = new Bundle();
        b.putStringArray("paths",paths);
        b.putString("name",name);
        opener.setArguments(b);
    }

    public FileSelection (File parent) {
        this(parent.list());
    }

    public void addSelection(FileSelection selection){

        String[] paths = new String[this.paths.length+selection.paths.length];
        System.arraycopy(this.paths,0,paths,0,this.paths.length);
        System.arraycopy(selection.paths,0,paths,this.paths.length,selection.paths.length);
        this.paths =paths;

    }

    public FileSelection getSelection(int position){

        return new FileSelection(getPaths(position));
    }

    public String[] getPaths() {
        return paths;
    }

    private String[] getPaths(int position) {
        return new String[]{paths[position]};
    }

    public String getName() {
        if(name!=null)return name;
        else if (paths!=null && paths.length==1) return paths[0];
        return null;
    }

    int getCount(){
        return paths.length;
    }

    public void setName(String name) {
        this.name = name;
    }


}

package io.github.rayanperoumal.droiddesigner.file;


import java.io.File;

/**
 * @author rayan peroumal
 */

public class FileSelection {
    private File[] files;
    public String name;

    public FileSelection(){
        this.files = new File[]{};
    }

    public FileSelection(File[] files){
        this();
        if(files!=null) this.files = files;
    }

    public FileSelection (File parent) {
        this(parent.listFiles());
    }

    public void addSelection(FileSelection selection){

        File[] files = new File[this.files.length+selection.files.length];
        System.arraycopy(this.files,0,files,0,this.files.length);
        System.arraycopy(selection.files,0,files,this.files.length,selection.files.length);
        this.files =files;

    }

    FileSelection getSelection(int position){

        return new FileSelection(getFiles(position));
    }

    public File[] getFiles() {
        return files;
    }

    private File[] getFiles(int position) {
        return new File[]{files[position]};
    }

    public String getName() {
        if(name!=null)return name;
        else if (files!=null && files.length==1) return files[0].getName();
        return null;
    }

    int getCount(){
        return files.length;
    }

    public void setName(String name) {
        this.name = name;
    }


}

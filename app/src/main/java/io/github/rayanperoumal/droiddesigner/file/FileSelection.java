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

        // Add Files
        if(this.files!=null && this.files.length>0){
            System.arraycopy(selection.files,0,this.files,0,selection.files.length);
        }else this.files=selection.files;

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

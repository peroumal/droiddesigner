package io.github.rayanperoumal.droiddesigner.file;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;

import io.github.rayanperoumal.droiddesigner.ColorOpener;
import io.github.rayanperoumal.droiddesigner.R;

/**
 * @author rayan peroumal
 * @version 2
 */

public class FileSelection implements java.io.Serializable{
    private String[] paths;
    public String name;
    private FragmentTransaction opener;
    private ArrayList<FileSelection> childs;
    private FileSelectionListener listener;
    private FragmentTransaction transaction;
    private @IdRes int layout;
    private FileSelectionOpener fragment;
    private FragmentManager manager;

    public FileSelection(){
        this.childs = new ArrayList<>();
        this.paths = new String[]{};
        this.opener = null;
        this.name = null;
    }

    public FileSelection(String name, String[] paths){
        this();
        if(paths!=null) this.paths = paths;
        this.name = name;
    }

    public int open(){
        if(listener!=null)listener.onSelected(this);
        if(opener !=null){
            return opener.commit();
        }
        return -1;
    }

    public void setOnFilectionSelectionListener(FileSelectionListener listener){
        this.listener = listener;
    }

    public void setOpener(FragmentTransaction opener){
        this.opener = opener;
    }

    public void addSelection(FileSelection selection){
        childs.add(selection);
    }

    public FileSelection getSelection(int position){
        return childs.get(position);
    }

    public FileSelection getChild(int position) {
        return childs.get(position);
    }

    public ArrayList<FileSelection> getChilds() {
        return childs;
    }

    public String getPath(int position) {
        return paths[position];
    }

    public String[] getPaths() {
        return paths;
    }

    public String getName() {
        if(name!=null)return name;
        else if (paths!=null && paths.length==1) return paths[0];
        return null;
    }

    public int getCount(){
        return childs.size();
    }

    public void setName(String name) {
        this.name = name;
    }

}

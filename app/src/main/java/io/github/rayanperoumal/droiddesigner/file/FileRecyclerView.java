package io.github.rayanperoumal.droiddesigner.file;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.io.File;

import io.github.rayanperoumal.droiddesigner.R;

/**
 * @author rayan peroumal
 */

public class FileRecyclerView extends RecyclerView{
    public FileSelectionListener listener;
    FileSelection[] fileSelections;
    protected int item = R.layout.view_file;

    public FileRecyclerView(Context context, FileSelection[] fileSelections) {
        super(context);
        this.fileSelections = fileSelections;
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(new FileListViewAdapter(context));
    }

    public FileRecyclerView(Context context, AttributeSet attrs){
        super(context,attrs);
        setLayoutManager(new LinearLayoutManager(context));
    }

    public void sync(FileSelection[] selections){
        this.fileSelections = selections;
        Adapter adapter = getAdapter();
        if(adapter!=null)adapter.notifyDataSetChanged();
        else setAdapter(new FileListViewAdapter(getContext()));
    }

    public void setOnFileSelected(FileSelectionListener listener){
        this.listener = listener;
    }

    private class FileListViewAdapter extends RecyclerView.Adapter<FileListViewAdapter.ViewHolder>{
        Context context;
        ViewHolder viewHolder;
        FileListViewAdapter(Context context){
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            viewHolder = new ViewHolder(new FileView(context));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.display(position);
        }

        @Override
        public int getItemCount() {
            return fileSelections.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            FileView view;
            ViewHolder(FileView view) {
                super(view);
                this.view = view;
                if(listener!=null)view.setOnFileSelected(listener);
            }

            void display(int position){
                view.setFileSelection(fileSelections[position]);
                view.setTitle(fileSelections[position].getName());
            }
        }
    }
}

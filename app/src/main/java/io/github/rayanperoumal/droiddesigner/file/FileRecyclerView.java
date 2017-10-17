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
    private File [] files;
    public FileSelectionListener listener;
    private File parent;
    protected int item = R.layout.view_file;

    public FileRecyclerView(Context context, File parent) {
        super(context);
        this.parent = parent;
        this.files = catchFiles(parent);
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(new FileListViewAdapter(context));
    }

    public FileRecyclerView(Context context, File[] files) {
        super(context);
        this.parent = parent;
        this.files = files;
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(new FileListViewAdapter(context));
    }

    public FileRecyclerView(Context context, AttributeSet attrs){
        super(context,attrs);
        setLayoutManager(new LinearLayoutManager(context));
    }

    public void syncFiles(File file){
        parent = file;
        files = catchFiles(file);
        Adapter adapter = getAdapter();
        if(adapter!=null)adapter.notifyDataSetChanged();
        else setAdapter(new FileListViewAdapter(getContext()));
    }

    public void setOnFileSelected(FileSelectionListener listener){
        this.listener = listener;
    }

    public File[] catchFiles(File file){
        if(file.exists())
            return file.listFiles();
        return new File[]{file};
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
            return files.length;

        }

        class ViewHolder extends RecyclerView.ViewHolder{
            FileView view;
            ViewHolder(FileView view) {
                super(view);
                this.view = view;
                if(listener!=null)view.setOnFileSelected(listener);
            }

            void display(int position){
                view.setFile(files[position]);
                view.setTitle(null);
            }
        }
    }
}

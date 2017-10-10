package io.github.rayanperoumal.droiddesigner;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import io.github.rayanperoumal.droiddesigner.R;

/**
 * Created by rayanperoumal on 05/10/2017.
 */

public class RepositoryRecyclerView extends RecyclerView{
    private static File [] files;
    private final File parent;

    public RepositoryRecyclerView(Context context, File parent) {
        super(context);
        this.parent = parent;
        files = catchFiles(parent);
        setLayoutManager(new LinearLayoutManager(context));
        setAdapter(new FileListViewAdapter(context));
    }

    public File[] catchFiles(File file){
        if(file.exists()){
            File[] list= file.listFiles();
            for(int i=0;i<list.length;i++){
                try {
                    Log.i("Repository:",list[i].getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return list;
        }
        return new File[]{file};
    }

    public class FileListViewAdapter extends RecyclerView.Adapter<FileListViewAdapter.ViewHolder>{
        public Context context;
        ViewHolder viewHolder;
        public FileListViewAdapter(Context context){
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository,parent,false);
            viewHolder = new ViewHolder(view);
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

        public class ViewHolder extends RecyclerView.ViewHolder{
            public TextView text;
            public View view;
            public ViewHolder(View itemView) {
                super(itemView);
                view = itemView;
                view.setClickable(true);
                text  = (TextView) itemView.findViewById(R.id.file_name);
            }

            public void display(int position){
                File file = files[position];
                String name = file.getName();
                text.setText(name);
                Log.i("Recycler:display","repository:"+name);

            }
        }
    }

}

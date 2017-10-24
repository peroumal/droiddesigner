package io.github.rayanperoumal.droiddesigner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import java.io.File;
import io.github.rayanperoumal.droiddesigner.file.FileRecyclerView;
import io.github.rayanperoumal.droiddesigner.file.FileSelection;

/**
 * Created by rayanperoumal on 05/10/2017.
 */

public class RepositoryRecyclerView extends FileRecyclerView {

    public RepositoryRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnFileSelected(selection ->{
            File file = new File(selection.getPath(0));
            for (File n:file.listFiles()){
                Log.i("fuckit","name:"+n.getName());
                selection.addSelection(new FileSelection(n.getName(),new String[]{n.getAbsolutePath()}));
            }
            Intent intent = new Intent(context,ProjectActivity.class);
            intent.putExtra("selection",selection);
            context.startActivity(intent);
        });

        File file = new File(context.getFilesDir(), "/repositories/");
        FileSelection selection = new FileSelection(file.getName(),new String[]{file.getAbsolutePath()});


        for (File n:file.listFiles()){
            selection.addSelection(new FileSelection(n.getName(),new String[]{n.getAbsolutePath()}));


        }
        sync(selection);
    }

}

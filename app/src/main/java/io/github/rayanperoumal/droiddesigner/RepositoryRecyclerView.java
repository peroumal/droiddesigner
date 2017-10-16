package io.github.rayanperoumal.droiddesigner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.io.File;
import io.github.rayanperoumal.droiddesigner.file.FileRecyclerView;

/**
 * Created by rayanperoumal on 05/10/2017.
 */

public class RepositoryRecyclerView extends FileRecyclerView {

    public RepositoryRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnFileSelected(file ->{
            Intent intent = new Intent(context,ProjectActivity.class);
            intent.putExtra("path",file.getAbsolutePath());
            context.startActivity(intent);
        });
        syncFiles(new File(context.getFilesDir(),"/repositories/"));
    }

}

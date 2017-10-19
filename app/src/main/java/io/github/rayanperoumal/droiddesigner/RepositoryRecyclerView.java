package io.github.rayanperoumal.droiddesigner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

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
            Intent intent = new Intent(context,ProjectActivity.class);
            intent.putExtra("path",selection.getFiles()[0].getAbsolutePath());
            context.startActivity(intent);
        });

        sync(new FileSelection(
                new File(context.getFilesDir(), "/repositories/").listFiles())
        );
    }

}

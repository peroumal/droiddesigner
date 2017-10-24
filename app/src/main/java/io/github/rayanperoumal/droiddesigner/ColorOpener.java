package io.github.rayanperoumal.droiddesigner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.rayanperoumal.droiddesigner.file.FileSelectionOpener;

/**
 * Created by r.peroumal on 24/10/2017.
 */

public class ColorOpener extends FileSelectionOpener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.editor_color,null,false);
        return v;
    }
}

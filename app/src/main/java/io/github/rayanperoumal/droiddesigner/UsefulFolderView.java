package io.github.rayanperoumal.droiddesigner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by rayanperoumal on 12/10/2017.
 */

public class UsefulFolderView extends FrameLayout {
    LinearLayout content;
    TextView title;
    public UsefulFolderView(Context context) {
        super(context);
        initViews(context);
    }

    public UsefulFolderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public void initViews(Context context){
        View mainView = LayoutInflater.from(context).inflate(R.layout.item_useful_folder,null,false);
        content = (LinearLayout)mainView.findViewById(R.id.content);
        title = (TextView)mainView.findViewById(R.id.title);
    }

}

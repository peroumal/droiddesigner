package io.github.rayanperoumal.droiddesigner.file;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.rayanperoumal.droiddesigner.R;

/**
 * @author rayan peroumal
 */

public class FileView extends FrameLayout {
    TextView title;
    private FileSelection fileSelection;
    LinearLayout mainView;
    private FileSelectionListener listener;

    public FileView(Context context) {
        super(context);
        initViews(context);
    }

    public FileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FileView,0,0);
        String s ="1";
        try{
            s=array.getString(R.styleable.FileView_title);
            Log.i("UseFulFile","exist and equals: "+s);
        }finally {
            array.recycle();
        }
        setTitle(s);
    }

    public void setTitle(String text){
        if(text!=null)title.setText(text);
        else if(fileSelection !=null) title.setText(fileSelection.getName());
    }

    public void initViews(Context context){
        setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        mainView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_file,null,false);
        removeAllViews();
        addView(mainView);
        mainView.setClickable(true);
        title = mainView.findViewById(R.id.title);
    }

    public void setFileSelection(FileSelection selection){
        this.fileSelection = selection;
        mainView.setOnClickListener(view1 ->{
            Log.i("filerecyclerview","fileSelection is clicked");
            if(listener!=null) listener.onSelected(fileSelection);
        });
    }

    public void setOnFileSelected(FileSelectionListener listener){
        this.listener = listener;
    }


}

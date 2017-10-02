package io.github.rayanperoumal.droiddesigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;

import io.github.rayanperoumal.droiddesigner.git.Clone;

public class MainActivity extends AppCompatActivity {
    CardView clonerView;
    EditText cloneText;
    TextView cloneTitle,alert;
    public static final int CLONE_TERMINATED=2;
    public static final int CLONE_IN_PROGRESS=1;
    public static final int CLONE_AVAILABLE=0;
    public static final int CLONE_UNAVAILABLE=-1;
    int cloneStatus =-1;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clonerView = (CardView) findViewById(R.id.clone);
        cloneText = (EditText) findViewById(R.id.cloneText);
        cloneTitle = (TextView) findViewById(R.id.cloneTitle);
        alert = (TextView) findViewById(R.id.alert);
        submit = (Button)findViewById(R.id.submit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        clonerView.setOnClickListener(view -> {
                cloneText.setEnabled(true);
                cloneText.setHint("domaine/repertoire/repository.git");
                cloneTitle.setVisibility(View.VISIBLE);
                cloneTitle.setText("Specifier le nom du repository");
                Log.i("activity:event","clone:called");
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cloneStatus == CLONE_TERMINATED){

                }else{
                    new Clone(cloneText.getText().toString()){
                        @Override
                        protected void onPostExecute(Integer integer) {
                            super.onPostExecute(integer);
                            Toast.makeText(MainActivity.this,getMessage(integer),Toast.LENGTH_LONG).show();
                            switch (integer){
                                case REPO_CLONED:
                                    cloneStatus=CLONE_TERMINATED;
                                    cloneTitle.setText("Le repository sera dans le repertoire");
                                    try {
                                        cloneText.setText(dir.getCanonicalPath());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    submit.setText("terminer");
                                    break;
                                default:break;
                            }
                        }
                    }.execute(MainActivity.this);
                }
            }
        });

        cloneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (cloneStatus != CLONE_TERMINATED) {
                    if (charSequence.toString().endsWith(".git")) {
                        if (checkServer(charSequence.toString())) {
                            alert.setVisibility(View.VISIBLE);
                            alert.setText("Le repo est pret à etre cloné");
                            submit.setVisibility(View.VISIBLE);
                        } else {
                            submit.setVisibility(View.GONE);
                            alert.setVisibility(View.VISIBLE);
                            alert.setText("Ce repository ne contient pas de domaine valide");
                        }
                    } else {
                        submit.setVisibility(View.GONE);
                        alert.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    public boolean checkServer(String name){

        // Check domain name
        for(int i=1; i<(name.length()-5);i++)
            if(name.charAt(i)=='/' && name.charAt(i-1)!='/' && name.charAt(i+1)!='/') return true;

        return false;
    }
}

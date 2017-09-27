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

import io.github.rayanperoumal.droiddesigner.git.Clone;

public class MainActivity extends AppCompatActivity {
    CardView clonerView;
    EditText cloneText;
    TextView cloneTitle,alert;
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
        new Clone("https://github.com/rayanperoumal/droiddesigner.git"){
            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                Toast.makeText(MainActivity.this,getMessage(integer),Toast.LENGTH_LONG).show();
            }
        }.execute(this);
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

        cloneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().endsWith(".git")){
                    if(checkDomain(charSequence.toString())){
                        alert.setVisibility(View.VISIBLE);
                        alert.setText("Le repo est pret à etre cloné");
                        submit.setVisibility(View.VISIBLE);
                    }
                    else{
                        submit.setVisibility(View.GONE);
                        alert.setVisibility(View.VISIBLE);
                        alert.setText("Ce repository ne contient pas de domaine valide");
                    }
                } else {
                    submit.setVisibility(View.GONE);
                    alert.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
    public boolean checkDomain(String name){
        boolean domain=false;
        int slash=-1, point=-1;
        for(int i=0; i<(name.length()-5) && slash<0;i++)
            switch(name.charAt(i)){
                case '/': slash=i;break;
                case '.': point=i;break;
            }
        if((point+1)<slash) domain = true;
        return domain;
    }
}

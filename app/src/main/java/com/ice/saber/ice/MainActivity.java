package com.ice.saber.ice;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button b1,b2,b3,b4;
    private static ArrayList<question> q=new ArrayList<question>();
    private ArrayList<String> result=new ArrayList<>();
    private FragmentManager  fmanger;
   private  Add f1;
    private  ans f3;
    private  submit f4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
       fmanger=getFragmentManager();
        b1=(Button)findViewById(R.id.radioButton1); // add
        b2=(Button)findViewById(R.id.radioButton2); //load
        b3=(Button)findViewById(R.id.radioButton3); //ans
        b4=(Button)findViewById(R.id.radioButton4); //submit
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
    }
    private void hideAllFragment(FragmentTransaction ft){
        if(f1 != null)ft.hide(f1);
        if(f3 != null)ft.hide(f3);
        if(f4 != null)ft.hide(f4);
    }
    @Override
    public void onClick(View v) {
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        hideAllFragment(ft);

        switch (v.getId())
        {
            case R.id.radioButton1:
                if(f1 == null){
                    f1 = new Add();
                    ft.add(R.id.f_main,f1,"f1");
                }else ft.show(f1);
                break;
            case R.id.radioButton2:
                break;
            case R.id.radioButton3:
                if(f3 == null){
                    f3 = new ans();
                    ft.add(R.id.f_main,f3,"f3");
                }else ft.show(f3);
                break;
            case R.id.radioButton4:
                if(f4 == null){
                    f4 = new submit();
                    ft.add(R.id.f_main,f4,"f4");
                }else ft.show(f4);
                break;
        }
        ft.commit();
    }
}

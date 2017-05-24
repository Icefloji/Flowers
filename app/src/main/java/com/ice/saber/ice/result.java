package com.ice.saber.ice;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class result extends Activity {
private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        lv=(ListView)findViewById(R.id.listwiev1);

        int length=getIntent().getIntExtra("length",0);
        boolean []is=getIntent().getBooleanArrayExtra("is");
        boolean [] re= getIntent().getBooleanArrayExtra("re");
        boolean [] ir= getIntent().getBooleanArrayExtra("ir");
        String []r=new String[length+1];
        for(int i=0;i<length;i++)
           if(is[i]==false) r[i]=Integer.toString(i+1)+".not selected";
           else r[i]=Integer.toString(i+1)+".you selected "+Boolean.toString(re[i])+",and you are "+Boolean.toString(ir[i]);
        int result=0;
        for(int i=0;i<length;i++)
            if(ir[i]==true) result++;
        r[length]="you get "+Integer.toString(result*5);
       lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,r));

    }

}

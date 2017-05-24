package com.ice.saber.ice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class add_que extends Activity {
    private Button b3;
    private EditText et;
    private RadioButton rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_que);
        b3=(Button)findViewById(R.id.button8);
        et=(EditText)findViewById(R.id.editText);
        rb=(RadioButton)findViewById(R.id.radioButton4);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et.getText().toString().trim()=="")  { Toast.makeText(add_que.this,R.string.blank,Toast.LENGTH_SHORT);return;}
                Intent it=new Intent(add_que.this,ansq_page.class);
                Bundle b=new Bundle();
                b.putSerializable("qu",new question(et.getText().toString(),rb.isChecked()));
                it.putExtras(b);
                startActivity(it);
            }
        });
    }
}

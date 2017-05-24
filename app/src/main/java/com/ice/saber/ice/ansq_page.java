package com.ice.saber.ice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class ansq_page extends Activity {
    private Button b1,b2,b3,b4,b5,b6,b7,b10,tr;
    private TextView t1,t2,t3;
    private static int c_index=0, c_total=1;
    private static question q[]=new question[10];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ansq_page);
        //
        q[0]=new question(getString(R.string.ques_1),true);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b6=(Button)findViewById(R.id.button6);
        b7=(Button)findViewById(R.id.button9);
        b10=(Button)findViewById(R.id.button10);
        t1=(TextView)findViewById(R.id.textView1);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);
        t1.setText(q[0].getMyquestion());
        t2.setText(Integer.toString(c_total)+"题，第"+Integer.toString(c_index+1)+"题");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(true);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(false);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_index++;
                if(c_index==c_total) c_index=0;
                t2.setText(Integer.toString(c_total)+"题，第"+Integer.toString(c_index+1)+"题");
                t1.setText(q[c_index].getMyquestion());
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c_index--;
                if(c_index==-1) c_index=c_total-1;
                t2.setText(Integer.toString(c_total)+"题，第"+Integer.toString(c_index+1)+"题");
                t1.setText(q[c_index].getMyquestion());
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int test=0,te=0,not=0;
                for(int i=0;i<c_total;i++)
                    if(!q[i].isselect()) {not=i;test=1;break;}
                else {te+=q[i].getScore();}
                if(test==0)  t3.setText("you get"+Integer.toString(te));
                else t3.setText("第"+Integer.toString(not+1)+"未选择");
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                if(!q[c_index].isselect())  a=R.string.select;
                else {
                    if (q[c_index].isOldanswer()) a=R.string.s_ture;
                    else a=R.string.s_false;
                }
                Toast.makeText(ansq_page.this,a,Toast.LENGTH_SHORT).show();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[]is=new boolean[c_total];
                boolean[]re=new boolean[c_total];
                boolean[]ir=new boolean[c_total];
                for(int i=0;i<c_total;i++)
                    if(q[i].isselect()==true) {
                       is[i]=true;
                        re[i]=q[i].isOldanswer();
                        ir[i]=(q[0].isOldanswer()==q[0].isMyanswer());
                    }
                else is[i] = false;
                Intent t = new Intent(ansq_page.this, result.class);
                t.putExtra("is",is);
                t.putExtra("re", re);
                t.putExtra("ir", ir);
                t.putExtra("length", c_total);
                startActivity(t);
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue mQueue = Volley.newRequestQueue(ansq_page.this);
                JSONObject jb=new JSONObject();
                JsonArrayRequest sr=new JsonArrayRequest("http://10.0.2.2/load.php",
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray arr) {
                                try {
                                    int size=arr.getJSONObject(0).getInt("size");
                                    for(int i=1;i<=size;i++){
                                      boolean b;if(arr.getJSONObject(i).getInt("an")==1) b=true;else b=false;
                                      q[c_total+i-1]=new question(arr.getJSONObject(i).getString("qu"),b);}
                                    c_total+=size;
                                }catch (Exception e){e.printStackTrace();}
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                            }
                        });
                mQueue.add(sr);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(getIntent().getExtras()==null) return;
        question qu=(question)getIntent().getExtras().getSerializable("qu");
        q[c_total]=new question();
        q[c_total].setMyquestion(qu.getMyquestion());
        q[c_total].setMyanswer(qu.isMyanswer());
        c_total++;
    }

    private void check(boolean s_answer) {
        int answer_id;
        q[c_index].setIsselect(true);
        q[c_index].setOldanswer(s_answer);
        if(s_answer==q[c_index].isMyanswer())
        {answer_id=R.string.true_toast;q[c_index].setScore(5);}
        else
        {answer_id=R.string.false_toast;q[c_index].setScore(0);}
        Toast.makeText(ansq_page.this,answer_id,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.ansq_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case  R.id.clear:clear();break;
            case R.id.nothing:;
        }
        return super.onOptionsItemSelected(item);
    }
    private void clear()
    {
        for(int i=1;i<c_total;i++)
        {
         q[i].setMyquestion(null);
            q[i].setIsselect(false);
            q[i].setScore(0);
            q[i].setMyanswer(false);
        }
        q[0].setIsselect(false);
        q[0].setScore(0);
    }

}

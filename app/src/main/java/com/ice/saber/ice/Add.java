package com.ice.saber.ice;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;


public class Add extends Fragment {
     private  View view;
    private ListView listview;
    private Button b1,b2,b3;
    private TextView text1;
    private RadioButton rb;
    private  String volleystr;
    private static ArrayList<question> ques_list=new ArrayList<question>();
    private  static ArrayList<String> ques_str=new ArrayList<String>();
    private  static   int index=-1;

    public Add() {
        // Required empty public constructor
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ArrayAdapter<String> adapt=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,ques_str);
        listview.setAdapter(adapt);
        RequestQueue re=newRequestQueue(getActivity());
       //add to listviwe

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text1.toString().trim()!="")
                { ques_list.add(new question(text1.getText().toString(),rb.isChecked()));
                    ques_str.add(text1.getText().toString()+","+Boolean.toString(rb.isChecked()));
                    adapt.notifyDataSetChanged();}
                else Toast.makeText(getActivity(),R.string.blank,Toast.LENGTH_SHORT);;
            }
        });
        //submit to mysql
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              volleystr="[";
                for(int i=0;i<ques_list.size();i++)
                    volleystr+="{\"ques\":\""+ques_list.get(i).getMyquestion()+"\",\"ans\":\""+Boolean.toString(ques_list.get(i).isMyanswer())+"\"}";
               volleystr+="]";
                RequestQueue re=newRequestQueue(getActivity());
                StringRequest sr=new StringRequest(Request.Method.POST,"http://10.0.2.2/load.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                  text1.setText(volleystr);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       error.printStackTrace();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("question", volleystr);
                        return map;                      }
                };
                re.add(sr);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index!=-1){
                ques_list.remove(index);
                ques_str.remove(index);
                adapt.notifyDataSetChanged();}
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               index=position;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_add, container, false);
        listview=(ListView)view.findViewById(R.id.add_list);
        b1=(Button)view.findViewById(R.id.add_b1);
        b2=(Button)view.findViewById(R.id.add_b2);
        b3=(Button)view.findViewById(R.id.add_b3);
        text1=(TextView)view.findViewById(R.id.add_text);
        rb=(RadioButton)view.findViewById(R.id.add_r1);
        return  view;
    }
}

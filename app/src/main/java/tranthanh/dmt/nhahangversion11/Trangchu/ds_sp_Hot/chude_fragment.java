package tranthanh.dmt.nhahangversion11.Trangchu.ds_sp_Hot;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import tranthanh.dmt.nhahangversion11.MainActivity;
import tranthanh.dmt.nhahangversion11.R;
import tranthanh.dmt.nhahangversion11.SQl_helper;
import tranthanh.dmt.nhahangversion11.Trangchu.chude.chude_trangchu_adap;
import tranthanh.dmt.nhahangversion11.chude.dong_hoahoa_ofchude;
import tranthanh.dmt.nhahangversion11.chude.hanghoa_ofchude_fragment;
import tranthanh.dmt.nhahangversion11.chuyendulieu;
import tranthanh.dmt.nhahangversion11.giohang.dong_sp_giohang;
import tranthanh.dmt.nhahangversion11.interface_Sukien;
import tranthanh.dmt.nhahangversion11.trunggian;

public class chude_fragment extends Fragment implements interface_Sukien,chuyendulieu {
    ArrayList<dong_hoahoa_ofchude> list;
    chude_trangchu_adap adapter;
    ds_trangchu_adap adapter1;
    ArrayList<dong_sp_giohang> list1;
    RecyclerView recyclerView,recyclerView1;
    ArrayList<String> listten;
    Spinner spinner;

    SQl_helper sQl_helper;

    @Override
    public void onStart() {
        super.onStart();
        sQl_helper.openDB();
    }

    @Override
    public void onStop() {
        super.onStop();
        sQl_helper.closeDB();
    }

    public static chude_fragment newInstance() {

        Bundle args = new Bundle();

        chude_fragment fragment = new chude_fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View view=inflater.inflate(R.layout.trangchu_view,container,false);
       sQl_helper=new SQl_helper(getActivity());
        spinner=view.findViewById( R.id.title_trangchu);
        recyclerView=(RecyclerView) view.findViewById(R.id.rychude_trangchu);
       recyclerView1= view.findViewById(R.id.reCyDS_trangchu);
        spinner=view.findViewById(R.id.title_trangchu);
        khoidongSpinner();
        hamchude(view);
        hamchude1(view);
        MainActivity.edtAuto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter1.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }
    void khoidongSpinner(){
        ArrayList<String> lsst=new ArrayList<>();
        lsst.add("Sap xep theo luot Mua tang dan");
        lsst.add("Sap xep theo gia tien tang dan");
        lsst.add("Sap xep theo gia tien giam dan");
        ArrayAdapter<String> adapter12=new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_spinner_item,
                lsst
        );
        adapter12.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter12);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //((TextView) adapterView.getChildAt(0)).setTextColor(Color.RED);
                try {
                    switch (i) {
                        case 0:
                            Collections.sort(list1);
                            adapter1.notifyDataSetChanged();
                            break;
                        case 1:
                            Collections.sort(list1, new Comparator<dong_sp_giohang>() {
                                @Override
                                public int compare(dong_sp_giohang dong_sp_giohang, dong_sp_giohang t1) {
                                    int a = Integer.parseInt(dong_sp_giohang.getTien());
                                    int b = Integer.parseInt(t1.getTien());
                                    if (a == b)
                                        return 0;
                                    else if (a < b)
                                        return -1;
                                    else
                                        return 1;
                                }
                            });
                            adapter1.notifyDataSetChanged();

                            break;
                        case 2:
                            Collections.sort(list1, new Comparator<dong_sp_giohang>() {
                                @Override
                                public int compare(dong_sp_giohang dong_sp_giohang, dong_sp_giohang t1) {
                                    int a = Integer.parseInt(dong_sp_giohang.getTien());
                                    int b = Integer.parseInt(t1.getTien());
                                    if (a == b)
                                        return 0;
                                    else if (a > b)
                                        return -1;
                                    else
                                        return 1;
                                }
                            });
                            adapter1.notifyDataSetChanged();

                            break;
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void hamchude(View view){
        list=new ArrayList<>();
        readJson(list,view, trunggian.linkchude,this);
    }
    void hamchude1(View view){
        list1= new ArrayList<>();
        readJson(list1,view, trunggian.linkHangHoa,this);

    }


    @Override
    public void TenChude(String s) {
        trunggian.luu("tenchude",s,getContext());
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("listchude").replace(R.id.content_frame, hanghoa_ofchude_fragment.newInstance()).commit();
    }
    void readJson(final ArrayList<dong_hoahoa_ofchude> lst, final View view, String url, final interface_Sukien ion){
        RequestQueue requestQueue= Volley.newRequestQueue(view.getContext());
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject object=response.getJSONObject(i);
                        String anh =object.getString("Img");
                        String ten=object.getString("Name");
                        if(!object.isNull("NCC")) {
                            String Ncc = object.getString("NCC");
                            String Tien =String.valueOf(object.getInt("Gia"));
                            lst.add(new dong_hoahoa_ofchude(anh,ten,Ncc,Tien));
                        }else {
                            lst.add(new dong_hoahoa_ofchude(anh, ten));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter =new chude_trangchu_adap(lst,view.getContext(), ion);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(view.getContext(), "loi!"+error.toString(), Toast.LENGTH_SHORT).show();
                Log.d("abc", "onErrorResponse: "+error.toString());
            }
        });
        requestQueue.add(request);
    }
    public void readJson(final ArrayList<dong_sp_giohang> lst, final View view, String url,final  chuyendulieu ion){
        RequestQueue requestQueue= Volley.newRequestQueue(view.getContext());
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<dong_awc> listDong;
                listDong=new ArrayList<>();
                listDong=laylist(listDong);
                Log.d("abc", "onResponse: "+listDong.size());

                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject object=response.getJSONObject(i);
                        String anh =object.getString("Img");
                        String ten=object.getString("Name");
                        String Ncc = object.getString("NCC");
                        String Tien = String.valueOf(object.getInt("Gia"));
                        lst.add(new dong_sp_giohang(anh, ten, Ncc, Tien));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                for (int i=0;i<lst.size();i++){
                    for(int j=0;j<listDong.size();j++){
                        if(lst.get(i).getName().equals(listDong.get(j).getName())){
                            lst.get(i).setLuotxem(listDong.get(j).getSl());
                        }
                    }
                }
                Collections.sort(lst);

                adapter1 =new ds_trangchu_adap(view.getContext(),lst,ion);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL,false);
                recyclerView1.setLayoutManager(layoutManager);
                recyclerView1.setAdapter(adapter1);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(view.getContext(), "loi!"+error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(request);
    }

    @Override
    public void sendata(String s) {
        MainActivity.nameMon.add(s);
        MainActivity.i++;
        MainActivity.tangsl(MainActivity.i+"");
    }
    ArrayList<dong_awc> laylist(ArrayList<dong_awc> lisst) {
        try {

            Cursor cursor = sQl_helper.getAllRecord();
            if (cursor != null) {
                cursor.moveToFirst();
                lisst.clear();
                String b;
                int c;
                while (!cursor.isAfterLast()) {
                    b = cursor.getString(cursor.getColumnIndex(SQl_helper.getNAME()));
                    c = Integer.parseInt(cursor.getString(cursor.getColumnIndex("sl")));
                   lisst.add(new dong_awc(b, c));
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){

        }
        return lisst;
    }
    void layID(){
        Cursor cursor= sQl_helper.getAllRecord1();
        if(cursor !=null) {
            cursor.moveToFirst();
            int c=0;
            while (!cursor.isAfterLast()) {
                c = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQl_helper.getID())));
                cursor.moveToNext();
            }
        }
    }
}

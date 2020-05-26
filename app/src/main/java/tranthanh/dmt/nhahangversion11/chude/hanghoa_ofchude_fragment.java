package tranthanh.dmt.nhahangversion11.chude;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import tranthanh.dmt.nhahangversion11.MainActivity;
import tranthanh.dmt.nhahangversion11.R;
import tranthanh.dmt.nhahangversion11.chuyendulieu;
import tranthanh.dmt.nhahangversion11.trunggian;

public class hanghoa_ofchude_fragment extends Fragment implements chuyendulieu {
    ArrayList<dong_hoahoa_ofchude> list;
    hanghoa_ofchude_adapter adapter;
    RecyclerView recyclerView;
    String b="";



    public static hanghoa_ofchude_fragment newInstance() {

        Bundle args = new Bundle();

        hanghoa_ofchude_fragment fragment = new hanghoa_ofchude_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.ds_hanghoaofchude_view,container,false);
        recyclerView=(RecyclerView) view.findViewById(R.id.reCyDs_hanghoaOfchude);
        b= trunggian.laydulieu("tenchude",getContext());
        hamchude(view);
        return view;
    }
    void hamchude(View view){
        list =new ArrayList<>();
        readJson(list,view,trunggian.linkHangHoa,this);



    }
    public void readJson(final ArrayList<dong_hoahoa_ofchude> list, final View view, String url, final chuyendulieu ion){
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
                            if(object.getString("Loai").contains(b)) {
                                String Ncc = object.getString("NCC");
                                String Tien = String.valueOf(object.getInt("Gia"));
                                list.add(new dong_hoahoa_ofchude(anh, ten, Ncc, Tien));
                            }else continue;
                        }else {
                            list.add(new dong_hoahoa_ofchude(anh, ten));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter =new hanghoa_ofchude_adapter(view.getContext(),list,ion);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL,false);
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


    @Override
    public void sendata(String s) {
        MainActivity.nameMon.add(s);
        MainActivity.i++;
        MainActivity.tangsl(MainActivity.i+"");
    }
}

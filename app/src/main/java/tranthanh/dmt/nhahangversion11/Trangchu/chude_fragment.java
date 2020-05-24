package tranthanh.dmt.nhahangversion11.Trangchu;

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

import tranthanh.dmt.nhahangversion11.R;
import tranthanh.dmt.nhahangversion11.chude.dong_hoahoa_ofchude;
import tranthanh.dmt.nhahangversion11.chude.hanghoa_ofchude_fragment;
import tranthanh.dmt.nhahangversion11.interface_Sukien;
import tranthanh.dmt.nhahangversion11.trunggian;

public class chude_fragment extends Fragment implements interface_Sukien {
    ArrayList<dong_hoahoa_ofchude> list;
    chude_trangchu_adap adapter;
    RecyclerView recyclerView;
    Bundle bundle=new Bundle();
    int i=0;


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
       recyclerView=(RecyclerView) view.findViewById(R.id.rychude_trangchu);
        i++;
        hamchude(view);
        return view;
    }
    void hamchude(View view){
        list=new ArrayList<>();
        readJson(list,view, trunggian.linkchude,list,this);


    }


    @Override
    public void TenChude(String s) {
        trunggian.luu("tenchude",s,getContext());
        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("listchude").replace(R.id.content_frame, hanghoa_ofchude_fragment.newInstance()).commit();
    }
    void readJson(final ArrayList<dong_hoahoa_ofchude> list, final View view, String url, final ArrayList<dong_hoahoa_ofchude> lst, final interface_Sukien ion){
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


}

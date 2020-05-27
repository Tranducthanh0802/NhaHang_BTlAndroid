package tranthanh.dmt.nhahangversion11.giohang;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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

import tranthanh.dmt.nhahangversion11.MainActivity;
import tranthanh.dmt.nhahangversion11.R;
import tranthanh.dmt.nhahangversion11.thanhtoan.thanhtoan_fragment;
import tranthanh.dmt.nhahangversion11.trunggian;

public class giohang_fragment extends Fragment implements sl_ion {
    RecyclerView recyclerView;
    TextView txtSotien;
    Button btnThanhtoan;
    ds_giohang_adap adapter;
    ArrayList<dong_sp_giohang> list;
    ArrayList<String> listten;
    int vitri=0;
    int tong=0;
    public static giohang_fragment newInstance() {

        Bundle args = new Bundle();

        giohang_fragment fragment = new giohang_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.giohang_view,container,false);
        recyclerView=view.findViewById(R.id.RecyslSp_giohang);
        txtSotien=view.findViewById(R.id.sotien_giohang);
        btnThanhtoan=view.findViewById(R.id.btnThanhtoan_giohang);
        btnThanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trunggian.luuMangGioHang("ListThanhToan",list,getActivity());
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("thanhtoan").replace(R.id.content_frame, thanhtoan_fragment.newInstance()).commit();
                MainActivity.check(getView());
            }
        });
        registerForContextMenu(recyclerView);
        hamchude(view);

        return view;
    }
    void hamchude(View view){
        list =new ArrayList<>();
        readJson(list,view,trunggian.linkHangHoa,this);

    }
    public void readJson(final ArrayList<dong_sp_giohang> list, final View view, String url, final sl_ion ion){
        RequestQueue requestQueue= Volley.newRequestQueue(view.getContext());
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listten = trunggian.laydulieuMang("MangMonAn", view.getContext());
                if (listten != null) {
                    tong = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject object = response.getJSONObject(i);
                            String anh = object.getString("Img");
                            String ten = object.getString("Name");
                            if (!object.isNull("NCC")) {

                                String Ncc = object.getString("NCC");
                                String Tien = String.valueOf(object.getInt("Gia"));
                                for (int j = 0; j < listten.size(); j++) {
                                    if (ten.equals(listten.get(j))) {
                                        list.add(new dong_sp_giohang(anh, ten, Ncc, Tien));
                                        tong += Integer.parseInt(Tien);
                                    }
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    adapter = new ds_giohang_adap(list, view.getContext(), ion);
                    adapter.setClick(new ds_giohang_adap.onLongclick() {
                        @Override
                        public void ItemLongClicked(View v, int position) {
                            vitri=position;
                            v.showContextMenu();
                        }
                    });
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    txtSotien.setText(tong + "");

                }
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
    public void sltang(String name) {
        tong+=Integer.parseInt(name);

        txtSotien.setText(tong+"");
    }

    @Override
    public void slgiam(String name) {
        tong-=Integer.parseInt(name);
        txtSotien.setText(tong+"");

    }

    @Override
    public void xoa(dong_sp_giohang sp) {
        list.remove(sp);
        adapter.notifyDataSetChanged();
        tong-=Integer.parseInt(sp.getTien());
        txtSotien.setText(tong+"");
        xoagiohang(sp.getName());
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.xoa_context_giohang:
                xoagiohang(list.get(vitri).getName());
                tong-=Integer.parseInt(list.get(vitri).getTien());
                txtSotien.setText(tong+"");
                list.remove(vitri);
                adapter.notifyDataSetChanged();

                break;
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.context_giohang,menu);
    }
   public void xoagiohang(String name){
        MainActivity.nameMon.remove(name);
        trunggian.luuMang("MangMonAn", MainActivity.nameMon, getContext());
        MainActivity.i--;
        MainActivity.tangsl(MainActivity.i+"");

    }
}

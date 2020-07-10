package tranthanh.dmt.nhahangversion11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

import tranthanh.dmt.nhahangversion11.Trangchu.ds_sp_Hot.chude_fragment;
import tranthanh.dmt.nhahangversion11.giohang.dong_sp_giohang;
import tranthanh.dmt.nhahangversion11.giohang.giohang_fragment;

public class MainActivity extends AppCompatActivity implements  chuyendulieu{
    private DrawerLayout mDrawerLayout;
    static TextView txtSl;
    ImageView img;
    ArrayList<dong_sp_giohang> list1;
    arrayAdapter_autoText adapter1;
    public static EditText edtAuto;
    Spinner spinner;
    public static ArrayList<String> nameMon;
    public static int  i=0;
    private static final int REQUEST_CALL=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        nameMon=new ArrayList<>();
        hamchude1();
        edtAuto= findViewById(R.id.AutoCom);
        img = findViewById(R.id.imgShop_Main);
        txtSl = findViewById(R.id.slshop_Main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom);
        navigation.setOnNavigationItemSelectedListener(mNavigationItemReselectedListener);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.deleteDraw:
                        if(i==0){
                            Toast.makeText(getApplicationContext(),"Không sản phẩm nào !",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"Xóa thành công !",Toast.LENGTH_SHORT).show();
                        }
                        i=0;
                        txtSl.setText(i+"");
                        nameMon.removeAll(nameMon);
                        trunggian.luuMang("MangMonAn",nameMon, getBaseContext());
                        break;
                    case R.id.lienHeCuocGoi:
                        makePhoneCall();
                        break;
                }
                menuItem.setChecked(true);
                return true;
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameMon!=null) {
                    trunggian.luuMang("MangMonAn", nameMon, getBaseContext());
                }else{
                    nameMon=new ArrayList<>();
                    trunggian.luuMang("MangMonAn",nameMon, getBaseContext());

                }
                check(getCurrentFocus());
                getSupportFragmentManager().beginTransaction().addToBackStack("giohang").replace(R.id.content_frame, giohang_fragment.newInstance()).commit();
            }
        });
        getSupportFragmentManager().beginTransaction().addToBackStack("chude").replace(R.id.content_frame, chude_fragment.newInstance()).commit();

    }


    public static void tangsl(String sl) {
        txtSl.setText(sl + "");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
    void hamchude1(){
        list1= new ArrayList<>();
        readJson(list1, trunggian.linkHangHoa,this);

    }
    public void readJson(final ArrayList<dong_sp_giohang> lst,  String url, final  chuyendulieu ion){
        RequestQueue requestQueue= Volley.newRequestQueue(getBaseContext());
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
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
                adapter1 =new arrayAdapter_autoText(getBaseContext(),lst,ion);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "loi!"+error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(request);
    }

    @Override
    public void sendata(String s) {
        nameMon.add(s);
        i++;
        tangsl(MainActivity.i+"");
    }
    private void makePhoneCall()
    {
        int number=18001900;
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + (number + "");
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }

    }

    BottomNavigationView.OnNavigationItemSelectedListener mNavigationItemReselectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.trangchu:
                    getSupportFragmentManager().beginTransaction().addToBackStack("chude").replace(R.id.content_frame, chude_fragment.newInstance()).commit();
                    check(getCurrentFocus());
                    return true;
                case R.id.giohang_bottom_main:
                    if(nameMon!=null) {
                        trunggian.luuMang("MangMonAn", nameMon, getBaseContext());
                    }else{
                        nameMon=new ArrayList<>();
                        trunggian.luuMang("MangMonAn",nameMon, getBaseContext());
                    }
                    getSupportFragmentManager().beginTransaction().addToBackStack("giohang").replace(R.id.content_frame, giohang_fragment.newInstance()).commit();
                    return true;

            }
            return false;
            }

    };
    public static void check(View view) {
        boolean ret = ConnectionReceiver.isConnected();
        String msg="";
        if (ret != true) {
            msg = "Thiết bị chưa kết nối internet";
            Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
        }

    }
}

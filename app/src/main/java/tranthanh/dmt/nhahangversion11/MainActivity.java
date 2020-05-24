package tranthanh.dmt.nhahangversion11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;

import tranthanh.dmt.nhahangversion11.Trangchu.chude_fragment;
import tranthanh.dmt.nhahangversion11.chude.hanghoa_ofchude_fragment;
import tranthanh.dmt.nhahangversion11.giohang.giohang_fragment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
     static TextView txtSl;
     ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        img=findViewById(R.id.imgShop_Main);
        txtSl=findViewById(R.id.slshop_Main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_black_24dp);
        mDrawerLayout=findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()){
                   case R.id.amNhac:
                       Log.d("abc", "onNavigationItemSelected: am nhac");
                       break;
                   case R.id.lienHeCuocGoi:
                       Log.d("abc", "onNavigationItemSelected: lien he cuoc goi");
                       break;
               }
                menuItem.setChecked(true);
                return true;
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trunggian.luuMang("MangMonAn", hanghoa_ofchude_fragment.nameMon,getBaseContext());
                getSupportFragmentManager().beginTransaction().addToBackStack("giohang").replace(R.id.content_frame, giohang_fragment.newInstance()).commit();


            }
        });
         getSupportFragmentManager().beginTransaction().addToBackStack("chude").replace(R.id.content_frame, chude_fragment.newInstance()).commit();

    }
    public static void tangsl(String sl){
        txtSl.setText(sl+"");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timkiem,menu);
        android.widget.SearchView searchView= (android.widget.SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
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
}

package tranthanh.dmt.nhahangversion11;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tranthanh.dmt.nhahangversion11.Trangchu.dong_chude_trangchu;

public class trunggian {


    public static String linkchude="http://192.168.1.5:8080/chude.php";
    public static  String linkHangHoa="http://192.168.1.5:8080/NhaHang/dulieu.php";
    public static void luu(String ten,String dulieu, Context context){
        SharedPreferences pre=context.getSharedPreferences("my_data",context.MODE_PRIVATE);
        SharedPreferences.Editor edit=pre.edit();
        edit.remove(ten).commit();
        edit.putString(ten, dulieu);
        edit.commit();
    }
    public static String laydulieu(String ten,Context context) {
        SharedPreferences pre = context.getSharedPreferences("my_data", context.MODE_PRIVATE);
        return  pre.getString(ten,"");
    }
    public static void luuMang(String ten,ArrayList<String> dulieu, Context context){
        SharedPreferences pre=context.getSharedPreferences("my_data",context.MODE_PRIVATE);
        SharedPreferences.Editor edit=pre.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dulieu);
        edit.remove(ten).commit();
        edit.putString(ten, json);
        edit.commit();
    }
    public static ArrayList<String> laydulieuMang(String ten, Context context) {
        SharedPreferences pre = context.getSharedPreferences("my_data", context.MODE_PRIVATE);
        Gson gson=new Gson();
        String response=pre.getString(ten, "");
        ArrayList<String> lstArrayList = gson.fromJson(response,
                new TypeToken<List<String>>(){}.getType());
        return  lstArrayList;
    }
}

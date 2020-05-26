package tranthanh.dmt.nhahangversion11;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import tranthanh.dmt.nhahangversion11.giohang.dong_sp_giohang;

public class trunggian {


    public static String linkchude="http://192.168.1.14:8080/chude.php";
    public static  String linkHangHoa="http://192.168.1.14:8080/NhaHang/dulieu.php";
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
    public static void luuMangGioHang(String ten,ArrayList<dong_sp_giohang> dulieu, Context context){
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
    public static ArrayList<dong_sp_giohang> laydulieuMangGioHang(String ten, Context context) {
        SharedPreferences pre = context.getSharedPreferences("my_data", context.MODE_PRIVATE);
        Gson gson=new Gson();
        String response=pre.getString(ten, "");
        ArrayList<dong_sp_giohang> lstArrayList = gson.fromJson(response,
                new TypeToken<List<dong_sp_giohang>>(){}.getType());
        return  lstArrayList;
    }
}

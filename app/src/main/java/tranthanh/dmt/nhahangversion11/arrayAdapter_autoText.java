package tranthanh.dmt.nhahangversion11;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tranthanh.dmt.nhahangversion11.chude.dong_hoahoa_ofchude;
import tranthanh.dmt.nhahangversion11.giohang.dong_sp_giohang;

public class arrayAdapter_autoText extends ArrayAdapter<dong_sp_giohang> {
    Context mContext;
    int layoutResourceId;
    ImageView img;
    TextView txtName, txtNCC, txtMoney;
    Button btnmua;
    chuyendulieu ion;
    ArrayList<dong_sp_giohang> list =new ArrayList<>();
    ArrayList<dong_sp_giohang> food = new ArrayList<>();



    public arrayAdapter_autoText(@NonNull Context context, @NonNull List<dong_sp_giohang> foodlist, chuyendulieu ion) {
        super(context, 0, foodlist);
        this.ion = ion;
        this.list = new ArrayList<>( foodlist);
        this.food = new ArrayList<>( foodlist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
            // inflate the layout

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dong_hanghoaofchude, parent, false);
        }
        img = convertView.findViewById(R.id.anh_ds_ofchude_adap);
        txtMoney = convertView.findViewById(R.id.txtTien_ds_ofchude_adap);
        txtName = convertView.findViewById(R.id.txtTen_ds_ofchude_adap);
        txtNCC = convertView.findViewById(R.id.txtNCC_ds_ofchude_adap);
        btnmua = convertView.findViewById(R.id.btnMua);

        dong_sp_giohang ds = list.get(position);
        Picasso.with(mContext).load(ds.getAnh().toString()).into(img);
        txtName.setText(ds.getName().toString() + "");
        txtNCC.setText(ds.getNcc().toString() + "");
        txtMoney.setText(ds.getTien().toString() + "");
        btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ion.sendata(txtName.getText().toString());
            }
        });
        return convertView;

    }

    @NonNull
    @Override
    public Filter getFilter() {

        return foodFilter;
    }

    private Filter foodFilter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults result=new FilterResults();
            List<dong_sp_giohang> suggestion = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                suggestion.addAll(list);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getName().toLowerCase().contains(filterPattern)){
                        suggestion.add(list.get(i));
                        Log.d("abcd", filterPattern+" "+list.get(i).getName()+i);

                    }
                }
            }
            result.values=suggestion;
            result.count=suggestion.size();
            return result;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {

            return ((dong_sp_giohang) resultValue).getName();   }
    };
}
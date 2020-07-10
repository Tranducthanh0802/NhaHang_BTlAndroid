package tranthanh.dmt.nhahangversion11.Trangchu.ds_sp_Hot;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import tranthanh.dmt.nhahangversion11.R;
import tranthanh.dmt.nhahangversion11.chuyendulieu;
import tranthanh.dmt.nhahangversion11.giohang.dong_sp_giohang;

public class ds_trangchu_adap extends RecyclerView.Adapter<ds_trangchu_adap.ViewHolder> implements Filterable, Serializable {
    Context context;
    ArrayList<dong_sp_giohang> list;
    chuyendulieu ion;
    Customfilter filter;
    ArrayList<dong_sp_giohang> filterArrayList;

    public ds_trangchu_adap(Context context, ArrayList<dong_sp_giohang> list, chuyendulieu ion) {
        this.context = context;
        this.list = list;
        this.ion = ion;
        filterArrayList=list;
    }

    public ds_trangchu_adap(Context context, ArrayList<dong_sp_giohang> list) {
        this.context = context;
        this.list = list;
        filterArrayList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.dong_hanghoaofchude,parent,false);
        ds_trangchu_adap.ViewHolder viewHolder=new ds_trangchu_adap.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        dong_sp_giohang ds=list.get(position);
        Picasso.with(context).load(ds.getAnh().toString()).into(holder.img);
        holder.txtName.setText(ds.getName().toString()+"");
        holder.txtNCC.setText(ds.getNcc().toString()+"");
        holder.txtMoney.setText(""+ds.getTien().toString()+" VND");
        holder.txtLuotxem.setText("Số lần mua :"+ds.getLuotxem() +"");
        holder.btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ion.sendata(holder.txtName.getText().toString());
                Toast.makeText(context,"Đã thêm sản phẩm vào giỏ hàng",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtName,txtNCC,txtMoney,txtLuotxem;
        Button btnmua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.anh_ds_ofchude_adap);
            txtMoney=itemView.findViewById(R.id.txtTien_ds_ofchude_adap);
            txtName=itemView.findViewById(R.id.txtTen_ds_ofchude_adap);
            txtNCC=itemView.findViewById(R.id.txtNCC_ds_ofchude_adap);
            btnmua=itemView.findViewById(R.id.btnMua);
            txtLuotxem=itemView.findViewById(R.id.sluongNguoiMua);
        }
    }


    @Override
    public Filter getFilter() {
        if (filter ==null){
            filter = new Customfilter();
        }
        return filter;
    }

    private class Customfilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            if(charSequence !=null && charSequence.length()>0){
                int a=Integer.parseInt(charSequence.toString().trim());
                ArrayList<dong_sp_giohang> filters=new ArrayList<>();
                for(int i=0;i<filterArrayList.size();i++){
                    Log.d("abcde", "performFiltering: "+filterArrayList.get(i).getTien().trim()+a);
                    if(Integer.parseInt(filterArrayList.get(i).getTien())==a){
                        dong_sp_giohang p = new dong_sp_giohang(filterArrayList.get(i).getAnh(),filterArrayList.get(i).getName(),filterArrayList.get(i).getNcc(),filterArrayList.get(i).getTien());
                        filters.add(p);
                    }
                }
                results.count=filters.size();
                results.values=filters;

            }
            else{
                results.count=filterArrayList.size();
                results.values=filterArrayList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list= (ArrayList<dong_sp_giohang>) filterResults.values;
            notifyDataSetChanged();
        }

    }
}

package tranthanh.dmt.nhahangversion11.giohang;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tranthanh.dmt.nhahangversion11.R;

public class ds_giohang_adap extends RecyclerView.Adapter<ds_giohang_adap.ViewHolder> {
    List<dong_sp_giohang> listsp;
    Context context;
    sl_ion ion;

    public ds_giohang_adap(List<dong_sp_giohang> listsp, Context context) {
        this.listsp = listsp;
        this.context = context;
    }

    public ds_giohang_adap(List<dong_sp_giohang> listsp, Context context, sl_ion ion) {
        this.listsp = listsp;
        this.context = context;
        this.ion = ion;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ds_giohang_adap,parent,false);
        ds_giohang_adap.ViewHolder viewHolder=new ds_giohang_adap.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        dong_sp_giohang sp=listsp.get(position);
        Picasso.with(context).load(sp.getAnh().toString()).into(holder.img);
        holder.txtTen.setText(sp.getName());
        holder.txtNCC.setText(sp.getNcc());
        holder.txtTien.setText(sp.getTien());
        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ion.sltang(holder.txtTien.getText().toString()+"");
                int a= Integer.parseInt(holder.txtSl.getText().toString())+1;
                holder.txtSl.setText(a+"");
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(holder.txtSl.getText().toString()) > 1) {
                    ion.slgiam((holder.txtTien.getText().toString())+"");
                    int a= Integer.parseInt(holder.txtSl.getText().toString())-1;
                    holder.txtSl.setText(a+"");
                }
            }
        });
        holder.imgXOa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ion.xoa(listsp.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listsp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img,imgXOa;
        TextView txtNCC,txtTien,txtSl,txtTen;
        Button btnTru,btnCong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.anh_ds_giohang_adap);
            txtNCC=itemView.findViewById(R.id.txtNCC_ds_giohang_adap);
            txtTien=itemView.findViewById(R.id.txtTien_ds_giohang_adap);
            txtTen=itemView.findViewById(R.id.txtTen_ds_giohanh_adap);
            txtSl=itemView.findViewById(R.id.txtSl_ds_giohang_adap);
            btnTru=itemView.findViewById(R.id.btnTru_ds_giohang_adap);
            btnCong=itemView.findViewById(R.id.btnCong_gioHang_adap);
            imgXOa=itemView.findViewById(R.id.imgXoa_giohang_adap);
        }
    }
}

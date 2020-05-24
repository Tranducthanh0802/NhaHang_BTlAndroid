package tranthanh.dmt.nhahangversion11.Trangchu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tranthanh.dmt.nhahangversion11.R;

public class ds_trangchu_adap extends RecyclerView.Adapter<ds_trangchu_adap.ViewHolder> {
    List<dong_sp_trangchu> listSp;
    Context context;

    public ds_trangchu_adap(List<dong_sp_trangchu> listSp, Context context) {
        this.listSp = listSp;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ds_trangchu_adap,parent,false);
        ds_trangchu_adap.ViewHolder viewHolder=new ds_trangchu_adap.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        dong_sp_trangchu sp=listSp.get(position);
        Picasso.with(context).load(sp.getAnh().toString()).into(holder.img);
        holder.name.setText(sp.getTen());
        holder.tien.setText(sp.getGiatien());
    }

    @Override
    public int getItemCount() {
        return listSp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name,tien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.anh_ds_trangchu_adap);
            name=itemView.findViewById(R.id.tensp_ds_trangchu_adap);
            tien=itemView.findViewById(R.id.giatien_ds_trangchu_adap);
        }
    }
}

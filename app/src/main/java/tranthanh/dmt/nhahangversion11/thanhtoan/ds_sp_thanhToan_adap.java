package tranthanh.dmt.nhahangversion11.thanhtoan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tranthanh.dmt.nhahangversion11.R;

public class ds_sp_thanhToan_adap extends RecyclerView.Adapter<ds_sp_thanhToan_adap.ViewHolder> {
    List<dong_sp_thanhtoan> listsp;
    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ds_thanhtoan_adap,parent,false);
        ds_sp_thanhToan_adap.ViewHolder viewHolder=new ds_sp_thanhToan_adap.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listsp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtten,txtNCC,txtSL,txtTongTien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.anh_ds_thanhtoan_adap);
            txtten=itemView.findViewById(R.id.txtTen_ds_thanhtoan_adap);
            txtNCC= itemView.findViewById(R.id.txtNCC_ds_thanhtoan_adap);
            txtSL=itemView.findViewById(R.id.txtSL_thanhtoan_adap);
            txtTongTien=itemView.findViewById(R.id.txtTien_ds_thanhtoan_adap);
        }
    }
}

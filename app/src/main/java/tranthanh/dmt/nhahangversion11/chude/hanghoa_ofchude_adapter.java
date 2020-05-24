package tranthanh.dmt.nhahangversion11.chude;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tranthanh.dmt.nhahangversion11.R;
import tranthanh.dmt.nhahangversion11.chuyendulieu;

public class hanghoa_ofchude_adapter extends RecyclerView.Adapter<hanghoa_ofchude_adapter.ViewHolder> {
    Context context;
    ArrayList<dong_hoahoa_ofchude> list;
    chuyendulieu ion;

    public hanghoa_ofchude_adapter(Context context, ArrayList<dong_hoahoa_ofchude> list) {
        this.context = context;
        this.list = list;
    }

    public hanghoa_ofchude_adapter(Context context, ArrayList<dong_hoahoa_ofchude> list, chuyendulieu ion) {
        this.context = context;
        this.list = list;
        this.ion = ion;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.dong_hanghoaofchude,parent,false);
        hanghoa_ofchude_adapter.ViewHolder viewHolder=new hanghoa_ofchude_adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        dong_hoahoa_ofchude ds=list.get(position);
        Picasso.with(context).load(ds.getAnh().toString()).into(holder.img);
        holder.txtName.setText(ds.getName().toString()+"");
        holder.txtNCC.setText(ds.getNCc().toString()+"");
        holder.txtMoney.setText(ds.getTien().toString()+"");
        holder.btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ion.sendata(holder.txtName.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtName,txtNCC,txtMoney;
        Button btnmua;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.anh_ds_ofchude_adap);
            txtMoney=itemView.findViewById(R.id.txtTien_ds_ofchude_adap);
            txtName=itemView.findViewById(R.id.txtTen_ds_ofchude_adap);
            txtNCC=itemView.findViewById(R.id.txtNCC_ds_ofchude_adap);
            btnmua=itemView.findViewById(R.id.btnMua);
        }
    }
}

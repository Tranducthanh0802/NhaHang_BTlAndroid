package tranthanh.dmt.nhahangversion11.Trangchu.chude;

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

import tranthanh.dmt.nhahangversion11.MainActivity;
import tranthanh.dmt.nhahangversion11.R;
import tranthanh.dmt.nhahangversion11.chude.dong_hoahoa_ofchude;
import tranthanh.dmt.nhahangversion11.interface_Sukien;

public class chude_trangchu_adap extends RecyclerView.Adapter<chude_trangchu_adap.ViewHolder> {
    List<dong_hoahoa_ofchude> listChude;
    Context context;
    interface_Sukien ion;



    public chude_trangchu_adap(List<dong_hoahoa_ofchude> listChude, Context context) {
        this.listChude = listChude;
        this.context = context;
    }

    public chude_trangchu_adap(List<dong_hoahoa_ofchude> listChude, Context context, interface_Sukien ion) {
        this.listChude = listChude;
        this.context = context;
        this.ion = ion;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chude_adapter,parent,false);
        chude_trangchu_adap.ViewHolder viewHolder=new chude_trangchu_adap.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        dong_hoahoa_ofchude ds=listChude.get(position);
        Picasso.with(context).load(ds.getAnh().toString()).into(holder.img);
        holder.txt.setText(ds.getName().toString()+"");
    }

    @Override
    public int getItemCount() {
        return listChude.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.anh_chude_trangchu_adap);
            txt=itemView.findViewById(R.id.txtTen_ds_trangchu_adap);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ion.TenChude(txt.getText().toString()+"");
                    MainActivity.check(itemView);
                }
            });
        }
    }
}

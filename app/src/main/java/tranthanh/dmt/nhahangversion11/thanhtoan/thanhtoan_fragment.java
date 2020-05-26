package tranthanh.dmt.nhahangversion11.thanhtoan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tranthanh.dmt.nhahangversion11.MainActivity;
import tranthanh.dmt.nhahangversion11.R;
import tranthanh.dmt.nhahangversion11.SQl_helper;
import tranthanh.dmt.nhahangversion11.Trangchu.chude_fragment;
import tranthanh.dmt.nhahangversion11.giohang.dong_sp_giohang;
import tranthanh.dmt.nhahangversion11.trunggian;

public class thanhtoan_fragment extends Fragment {
    ArrayList<dong_sp_giohang> list;
    RecyclerView recyclerView;
    RadioButton rdtienmat, rdthe;
    ds_sp_thanhToan_adap adapter;
    Button btnThanhTOan;
    TextView txtTongtien;
    SQl_helper sQl_helper;

    @Override
    public void onStart() {
        super.onStart();
        sQl_helper.openDB();
    }

    @Override
    public void onStop() {
        super.onStop();
        sQl_helper.closeDB();
    }

    public static thanhtoan_fragment newInstance() {

        Bundle args = new Bundle();

        thanhtoan_fragment fragment = new thanhtoan_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.thanhtoan_view, container, false);
        recyclerView = view.findViewById(R.id.slSp_thanhtoan);
        sQl_helper = new SQl_helper(getContext());
        rdtienmat = view.findViewById(R.id.rdTienMat);
        rdthe = view.findViewById(R.id.rdThe);
        txtTongtien = view.findViewById(R.id.giatien_thanhtoan);
        btnThanhTOan = view.findViewById(R.id.btnThanhtoan_thanhtoan);
        list = trunggian.laydulieuMangGioHang("ListThanhToan", getContext());
        adapter = new ds_sp_thanhToan_adap(list, getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        tinhtongtien();
        btnThanhTOan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rdthe.isChecked() || rdtienmat.isChecked()) {
                    long resultADD;
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    for (int i = 0; i < list.size(); i++) {
                        String s = simpleDateFormat.format(date);
                        resultADD = sQl_helper.Insert(1,list.get(i).getName(), s);
                        if (resultADD == -1) {
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                        }
                    }
                    int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                    while (count > 1) {
                        getActivity().getSupportFragmentManager().popBackStack();
                        count--;
                    }
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, chude_fragment.newInstance()).commit();
                } else {
                    Toast.makeText(getActivity(), "Hay chon hinh thuc thanh toan", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return view;
    }

    private void tinhtongtien() {
        int tongtien = 0;
        for (int i = 0; i < list.size(); i++) {
            tongtien += list.get(i).getSl() * Integer.parseInt(list.get(i).getTien());
        }
        txtTongtien.setText(tongtien + "");
    }
}

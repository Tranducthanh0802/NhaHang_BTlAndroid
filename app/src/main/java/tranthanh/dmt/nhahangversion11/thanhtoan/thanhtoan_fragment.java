package tranthanh.dmt.nhahangversion11.thanhtoan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import tranthanh.dmt.nhahangversion11.R;

public class thanhtoan_fragment extends Fragment {
    public static thanhtoan_fragment newInstance() {

        Bundle args = new Bundle();

        thanhtoan_fragment fragment = new thanhtoan_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.thanhtoan_view,container,false);

        return view;
    }
}

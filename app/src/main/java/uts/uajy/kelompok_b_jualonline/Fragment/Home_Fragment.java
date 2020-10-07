package uts.uajy.kelompok_b_jualonline.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import uts.uajy.kelompok_b_jualonline.BarangRecyclerViewAdapter;
import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.modelBarang.Barang;
import uts.uajy.kelompok_b_jualonline.modelBarang.DataBarang;

import static android.content.ContentValues.TAG;

public class Home_Fragment extends Fragment {
    ArrayList<Barang> listBarang, listCart;
    RecyclerView recyclerView;
    BarangRecyclerViewAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        //get the dummy data
        listBarang = new DataBarang().listBarang;

        adapter = new BarangRecyclerViewAdapter(getContext(),listBarang);

        recyclerView = view.findViewById(R.id.recyclerview_Barang);

        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }
}
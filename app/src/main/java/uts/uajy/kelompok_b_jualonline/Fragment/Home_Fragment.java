package uts.uajy.kelompok_b_jualonline.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import uts.uajy.kelompok_b_jualonline.adapter.BarangRecyclerViewAdapter;
import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.database.DatabaseClient;
import uts.uajy.kelompok_b_jualonline.modelBarang.Barang;
import uts.uajy.kelompok_b_jualonline.modelBarang.DataBarang;

public class Home_Fragment extends Fragment {
    List<Barang> listBarang, listCart;
    BarangRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ExtendedFloatingActionButton addtocart;
    Boolean checkTheme;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_, container, false);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("filename", Context.MODE_PRIVATE);
        checkTheme = sharedPreferences.getBoolean("NightMode",false);
        if(checkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            getContext().setTheme(R.style.darktheme);

        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            getContext().setTheme(R.style.AppTheme);
        }

        //get the dummy data
        listBarang = new DataBarang().listBarang;
        listCart = new ArrayList<Barang>();
        adapter = new BarangRecyclerViewAdapter(getContext(),listBarang);
        recyclerView = view.findViewById(R.id.recyclerview_Barang);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        addtocart = view.findViewById(R.id.extended_fab);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.returnCart().isEmpty()) {
                    Toast.makeText(view.getContext(),"Empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(view.getContext(),"Not Empty",Toast.LENGTH_SHORT).show();
                    listCart = adapter.returnCart();
                    addUser(listCart);
                    Toast.makeText(view.getContext(),"List Acquired",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public List returnCartList() {
        return listCart;
    }

    private void addUser(final List<Barang> listCart){
        class AddUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids)  {
                for (int i=0;i<listCart.size();i++) {
                    Barang barang = listCart.get(i);
                    DatabaseClient.getInstance(getContext()).getDatabase()
                            .barangDAO()
                            .insert(barang);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        }
        AddUser add = new AddUser();
        add.execute();
    }

    private void delete(final Barang barang){
        class DeleteUser extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getContext()).getDatabase()
                        .barangDAO()
                        .delete(barang);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getContext(), "Barang deleted", Toast.LENGTH_SHORT).show();
            }
        }

        DeleteUser delete = new DeleteUser();
        delete.execute();
    }
}
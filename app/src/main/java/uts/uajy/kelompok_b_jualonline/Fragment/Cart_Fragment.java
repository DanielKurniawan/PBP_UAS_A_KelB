package uts.uajy.kelompok_b_jualonline.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.adapter.CartRecyclerViewAdapter;
import uts.uajy.kelompok_b_jualonline.modelBarang.Barang;


public class Cart_Fragment extends Fragment {
    //tema
    Boolean checkTheme;

    //adapter swiperefresh
    List<Barang> listCart;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    ExtendedFloatingActionButton addtocart;
    CartRecyclerViewAdapter adapter;
    SwipeRefreshLayout refreshLayout;
    MaterialButton checkout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cart_, container, false);
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

        refreshLayout = view.findViewById(R.id.swipe_refresh);
        recyclerView = view.findViewById(R.id.cart_rv);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Inflate the layout for this fragment
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                getUsers(view);
                refreshLayout.setRefreshing(false);
            }
        });

        checkout = view.findViewById(R.id.btn_checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(getContext())
                        .setTitle("Pay")
                        .setMessage("Are you sure want to pay ?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                update(view);
                                Toast.makeText(getContext(), "Sudah terbeli, silahkan refresh", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
//        getUsers(view);
        return view;
    }

//    public void getUsers(final View view){
//        class GetUsers extends AsyncTask<Void, Void, List<Barang>> {
//            public CartRecyclerViewAdapter adapter;
//            MaterialTextView outputSubTotal, totalHarga, ongkirtxt;
//            @Override
//            protected List<Barang> doInBackground(Void... voids) {
//                List<Barang> cartList = DatabaseClient
//                        .getInstance(view.getContext())
//                        .getDatabase()
//                        .barangDAO()
//                        .getAll("belum");
//                return cartList;
//            }
//
//            @SuppressLint("SetTextI18n")
//            @Override
//            protected void onPostExecute(List<Barang> cart) {
//                super.onPostExecute(cart);
//                adapter = new CartRecyclerViewAdapter(view.getContext(), cart);
//                recyclerView.setAdapter(adapter);
//                int subtotal=0, ongkir=20000;
//                outputSubTotal = view.findViewById(R.id.txt_harga);
//                totalHarga = view.findViewById(R.id.totalharga);
//                ongkirtxt = view.findViewById(R.id.ongkir);
//                for(int i=0;i<cart.size();i++)
//                {
//                    subtotal=subtotal+cart.get(i).getHarga();
//                }
//                if (cart.isEmpty()){
//                    outputSubTotal.setText("Rp 0");
//                    ongkirtxt.setText("Rp 0");
//                    totalHarga.setText("Rp 0");
//                    Toast.makeText(view.getContext(), "Empty List", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    outputSubTotal.setText("Rp "+String.valueOf(subtotal));
//                    ongkirtxt.setText("Rp 10000");
//                    totalHarga.setText("Rp" + String.valueOf(subtotal+10000));
//                }
//            }
//        }
//        GetUsers get = new GetUsers();
//        get.execute();
//    }

//    private void delete(final Barang barang){
//        class DeleteUser extends AsyncTask<Void, Void, Void> {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                DatabaseClient.getInstance(getContext()).getDatabase()
//                        .barangDAO()
//                        .delete(barang);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                Toast.makeText(getContext(), "Barang deleted", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        DeleteUser delete = new DeleteUser();
//        delete.execute();
//    }


//    private void update(final View view){
//        class UpdateUser extends AsyncTask<Void, Void, Void> {
//
//            @Override
//            protected Void doInBackground(Void... voids) {
//
//                List<Barang> cartList = DatabaseClient
//                        .getInstance(view.getContext())
//                        .getDatabase()
//                        .barangDAO()
//                        .getAll("belum");
//                for(int i=0;i<cartList.size();i++) {
//                    DatabaseClient.getInstance(view.getContext()).getDatabase()
//                            .barangDAO()
//                            .update("sudah",cartList.get(i).getId());
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                Toast.makeText(getActivity().getApplicationContext(), "Status updated, please refresh to continue", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        UpdateUser update = new UpdateUser();
//        update.execute();
//    }
}
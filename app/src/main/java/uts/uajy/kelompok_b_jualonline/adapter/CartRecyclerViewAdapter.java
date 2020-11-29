package uts.uajy.kelompok_b_jualonline.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.databinding.AdapterRecyclerViewCartBinding;
import uts.uajy.kelompok_b_jualonline.model.Barang;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.MyViewHolder> implements Filterable {
    private Context context;

    //dataset Barang
    private List<Barang> cart,cartOriginal;

    private AdapterRecyclerViewCartBinding binding;

    public CartRecyclerViewAdapter() {}

    public CartRecyclerViewAdapter(Context context, List<Barang> cart) {
        this.context = context;
        this.cart = cart;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = AdapterRecyclerViewCartBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        final MyViewHolder holder = new MyViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartRecyclerViewAdapter.MyViewHolder holder, int position) {
        final Barang b = cart.get(position);
        binding.setBarang(b);
        final int pos = position;
        //gk ono holder soal e gk iso di check
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new MaterialAlertDialogBuilder(view.getContext())
                        .setTitle("Are you sure want to delete this item ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                delete(b,view);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
    }

//    private void delete(final Barang barang, final View view){
//        class DeleteUser extends AsyncTask<Void, Void, Void> {
//            MaterialTextView outputSubTotal;
//            @Override
//            protected Void doInBackground(Void... voids) {
//
//                //delete dulu
//                DatabaseClient.getInstance(view.getContext().getApplicationContext()).getDatabase()
//                        .barangDAO()
//                        .delete(barang);
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                super.onPostExecute(aVoid);
//                //summon the toasty bread hehe
//                Toast.makeText(view.getContext().getApplicationContext(), "Barang deleted, swipe to refresh", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        DeleteUser delete = new DeleteUser();
//        delete.execute();
//    }



    @Override
    public int getItemCount() {
        return cart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private MaterialCardView cardView;
        private MaterialTextView nama,deskripsi,harga;
        private ImageView imageView;
        private final AdapterRecyclerViewCartBinding binding;
        public MyViewHolder(@NonNull AdapterRecyclerViewCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            nama = itemView.findViewById(R.id.namaBarangText);
            deskripsi = itemView.findViewById(R.id.deskripsiText);
            harga = itemView.findViewById(R.id.hargaText);
            imageView = itemView.findViewById(R.id.imageBarang);
            cardView = itemView.findViewById(R.id.cartCardView);
        }

        public void bind(Barang b){
            binding.setBarang(b);
            binding.executePendingBindings();
        }

        public void onClick(View view) {
            Toast.makeText(context, "Touched", Toast.LENGTH_SHORT).show();
        }
    }

    // FILTER SWIPE REFRESH HAHAHA
    @Override
    public Filter getFilter() {
        return null;
    }

    private Filter userFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence searchText) { //executed on the background, jadi algoritma gk ngganggu
            List<Barang> filteredList = new ArrayList<>();
            if(searchText == null || searchText.length()==0) {
                filteredList.addAll(cartOriginal);
            }
            else {
                String filtered = searchText.toString().toLowerCase().trim();

                for(Barang barang : cartOriginal) {
                    if(barang.getNamaBarang().contains(filtered)) {
                        filteredList.add(barang);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) { //results automatically published
            cart.clear();
            cart.addAll((List) filterResults.values);
            notifyDataSetChanged();
            //userList hasilnya hasil search
        }
    };
}

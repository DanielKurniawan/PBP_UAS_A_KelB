package uts.uajy.kelompok_b_jualonline;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import uts.uajy.kelompok_b_jualonline.databinding.AdapterRecyclerViewBarangBinding;
import uts.uajy.kelompok_b_jualonline.modelBarang.Barang;

public class BarangRecyclerViewAdapter extends RecyclerView.Adapter<BarangRecyclerViewAdapter.MyViewHolder> {
    private Context context;

    //dataset Barang
    private ArrayList<Barang> listBarang;
    private ArrayList<Barang> cart;

    public AdapterRecyclerViewBarangBinding binding;
    public BarangRecyclerViewAdapter(){}

    public BarangRecyclerViewAdapter(Context context, ArrayList<Barang> result){
        this.context = context;
        this.listBarang = result;
        cart = new ArrayList<Barang>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = AdapterRecyclerViewBarangBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        final MyViewHolder holder = new MyViewHolder(binding);
        return holder;
    }

    @Override
    //replace the contents of the view
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Barang b = listBarang.get(position);
        binding.setBarang(b);
        final int pos = position;
//        Toast.makeText(context,b.getNamaBarang(),Toast.LENGTH_SHORT).show();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cardView.setChecked(!holder.cardView.isChecked());
                addorremoveTitle(view, holder, b);
                Toast.makeText(view.getContext(),"Added to cart",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    //return the size of dataset
    public int getItemCount() {
        return listBarang.size();
    }

    public ArrayList<Barang> returnCart() {
        return cart;
    }

    public void addorremoveTitle(View v, MyViewHolder holder, Barang b)
    {
        if(holder.cardView.isChecked()) //checked
        {
            //add ke list cart hehehhe
            cart.add(b);
        }
        else if(!holder.cardView.isChecked()) //unchecked
        {
            //hapus
            cart.remove(b);
        }
//        Toast.makeText(v.getContext(),b.getNamaBarang(),Toast.LENGTH_SHORT).show();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        private final AdapterRecyclerViewBarangBinding binding;
        private MaterialCardView cardView;
        private MaterialTextView nama,deskripsi,harga;
        private ImageView imageView;
        private final AdapterRecyclerViewBarangBinding binding;
        public MyViewHolder(@NonNull AdapterRecyclerViewBarangBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            nama = itemView.findViewById(R.id.namaBarangText);
            deskripsi = itemView.findViewById(R.id.deskripsiText);
            harga = itemView.findViewById(R.id.hargaText);
            imageView = itemView.findViewById(R.id.imageBarang);
            cardView = itemView.findViewById(R.id.CardView);
        }

        public void bind(Barang b){
            binding.setBarang(b);
            binding.executePendingBindings();
        }

        public void onClick(View view) {
            Toast.makeText(context, "Touched", Toast.LENGTH_SHORT).show();
        }
    }
}

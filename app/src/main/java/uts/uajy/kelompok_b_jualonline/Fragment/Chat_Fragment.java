package uts.uajy.kelompok_b_jualonline.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.persistencedata.sharedpref;

public class Chat_Fragment extends Fragment {

    sharedpref sharedpref;
    Boolean checkTheme;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat_, container, false);
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
//        getBarang(view);
        return view;
    }
//    public void getBarang(final View view){
//        class GetUsers extends AsyncTask<Void, Void, List<Barang>> {
//            public HistoryRecyclerViewAdapter adapter;
//            @Override
//            protected List<Barang> doInBackground(Void... voids) {
//                List<Barang> historyList = DatabaseClient
//                        .getInstance(view.getContext())
//                        .getDatabase()
//                        .barangDAO()
//                        .getAll("sudah");
//                return historyList;
//            }
//
//            @SuppressLint("SetTextI18n")
//            @Override
//            protected void onPostExecute(List<Barang> history) {
//                super.onPostExecute(history);
//
//                recyclerView = view.findViewById(R.id.history_rv);
//                mLayoutManager = new LinearLayoutManager(view.getContext());
//                recyclerView.setLayoutManager(mLayoutManager);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                adapter = new HistoryRecyclerViewAdapter(view.getContext(), history);
//                recyclerView.setAdapter(adapter);
//            }
//        }
//        GetUsers get = new GetUsers();
//        get.execute();
//    }
}
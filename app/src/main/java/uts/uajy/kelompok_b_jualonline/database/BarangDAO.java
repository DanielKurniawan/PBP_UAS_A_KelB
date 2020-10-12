package uts.uajy.kelompok_b_jualonline.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import uts.uajy.kelompok_b_jualonline.modelBarang.Barang;

@Dao
public interface BarangDAO {

    @Query("SELECT * FROM barang WHERE status =:status")
    List<Barang> getAll(String status);

    @Insert
    void insert(Barang barang);

    @Query("UPDATE barang SET status= :status where id= :id")
    void update(String status, int id);

    @Delete
    void delete(Barang barang);
}

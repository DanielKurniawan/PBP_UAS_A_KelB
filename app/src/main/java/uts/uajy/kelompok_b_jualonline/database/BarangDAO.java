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

    @Query("SELECT * FROM barang")
    List<Barang> getAll();

    @Insert
    void insert(Barang barang);

    @Update
    void update(Barang barang);

    @Delete
    void delete(Barang barang);
}

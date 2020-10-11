package uts.uajy.kelompok_b_jualonline.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import uts.uajy.kelompok_b_jualonline.modelBarang.Barang;

//@Database yang mena mendefinisikan bahwa ini adalah database yang akan dibuat, dengan tabel Barang dan versionnya 1.
@Database(entities = {Barang.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BarangDAO barangDAO();
}

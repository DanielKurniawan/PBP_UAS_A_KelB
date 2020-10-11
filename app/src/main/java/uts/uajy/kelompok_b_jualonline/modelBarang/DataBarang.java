package uts.uajy.kelompok_b_jualonline.modelBarang;

import java.util.ArrayList;
import java.util.List;

public class DataBarang {
    public List<Barang> listBarang;
    public DataBarang() {
        listBarang = new ArrayList();
        listBarang.add(b1);
        listBarang.add(b2);
        listBarang.add(b3);
        listBarang.add(b4);
        listBarang.add(b5);
        listBarang.add(b6);
    }

    public static final Barang b1 = new Barang("Dettol Sabun Mandi 20ml","Dettol sabun mandi adalah sabun yang digunakan" +
            "untuk mandi agar kuman-kuman yang ada bisa mati dan tidak menyebabkan bau badan yang menyengat",20000,"https://www.static-sr" +
            "c.com/wcsstore/Indraprastha/images/catalog/full//757/dettol_dettol-cair-antiseptik--750-ml-_full02.jpg?output-format=webp");
    public static final Barang b2 = new Barang("Dettol Sabun Cuci Tangan 40ml","Dettol sabun cuci tangan adalah sabun yang digunakan" +
            "untuk mencuci agar kuman-kuman yang ada bisa mati dan tidak menyebabkan bau tangan yang menyengat",20000,"https://d27zlipt1pllog.cloudfront." +
            "net/pub/media/catalog/product/d/e/det0166.jpg");
    public static final Barang b3 = new Barang("Dettol Sabun Cuci Mobil 100ml","Dettol sabun cuci mobil adalah sabun yang digunakan" +
            "untuk mencuci agar kuman-kuman yang ada bisa mati dan tidak menyebabkan bau mobil yang menyengat",20000,"https://cdn.fcglcdn.com/brainbee" +
            "s/images/products/438x531/2013930a.jpg");
    public static final Barang b4 = new Barang("Dettol Sabun Cuci Mobil 100ml","Dettol sabun cuci mobil adalah sabun yang digunakan" +
            "untuk mencuci agar kuman-kuman yang ada bisa mati dan tidak menyebabkan bau mobil yang menyengat",20000,"https://cdn.fcglcdn.com/brainbee" +
            "s/images/products/438x531/2013930a.jpg");
    public static final Barang b5 = new Barang("Dettol Sabun Cuci Mobil 100ml","Dettol sabun cuci mobil adalah sabun yang digunakan" +
            "untuk mencuci agar kuman-kuman yang ada bisa mati dan tidak menyebabkan bau mobil yang menyengat",20000,"https://cdn.fcglcdn.com/brainbee" +
            "s/images/products/438x531/2013930a.jpg");
    public static final Barang b6 = new Barang("Dettol Sabun Cuci Mobil 100ml","Dettol sabun cuci mobil adalah sabun yang digunakan" +
            "untuk mencuci agar kuman-kuman yang ada bisa mati dan tidak menyebabkan bau mobil yang menyengat",20000,"https://cdn.fcglcdn.com/brainbee" +
            "s/images/products/438x531/2013930a.jpg");

}
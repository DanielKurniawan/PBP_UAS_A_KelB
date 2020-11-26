package uts.uajy.kelompok_b_jualonline.api;

public class BarangAPI {
//    Route::post('barang','Api\BarangController@store'); // create
//    Route::get('barang','Api\BarangController@index'); //read all
//    Route::get('barang/{id}','Api\BarangController@show'); //read selected by id
//    Route::post('barang/update/{id}','Api\BarangController@update'); //update
//    Route::post('barang/delete/{id}','Api\BarangController@destroy'); //delete

    public static final String ROOT_URL   = "https://180709738.000webhostapp.com/";
    public static final String ROOT_API   = ROOT_URL+ "api/";
    public static final String URL_IMAGE  = ROOT_URL+"images/";

    //    method POST create
    public static final String URL_REGISTER = ROOT_API + "barang";

    //    method GET by ID
    public static final String URL_GET_ID = ROOT_API + "barang/";

    //    method GET all
    public static final String URL_GET = ROOT_API + "barang/";

    //    method POST
    public static final String URL_UPDATE = ROOT_API + "barang/update/";

    //    method POST by ID
    public static final String URL_DELETE = ROOT_API + "barang/delete/";
}

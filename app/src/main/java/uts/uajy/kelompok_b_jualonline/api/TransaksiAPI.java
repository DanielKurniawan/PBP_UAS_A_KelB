package uts.uajy.kelompok_b_jualonline.api;

public class TransaksiAPI {
//    Route::post('transaction','Api\TransaksiController@store'); // create
//    Route::get('transaction','Api\TransaksiController@index'); //read all
//    Route::get('transaction/{id}','Api\TransaksiController@show'); //read selected by id
//    Route::post('transaction/update/{id}','Api\TransaksiController@update'); //update
//    Route::post('transaction/delete/{id}','Api\TransaksiController@destroy'); //delete

    public static final String ROOT_URL   = "https://180709738.000webhostapp.com/";
    public static final String ROOT_API   = ROOT_URL+ "api/";
    public static final String URL_IMAGE  = ROOT_URL+"images/";

    //    method POST create
    public static final String URL_REGISTER = ROOT_API + "transaction";

    //    method GET by ID
    public static final String URL_GET_ID = ROOT_API + "transaction/";

    //    method GET all
    public static final String URL_GET = ROOT_API + "transaction/";

    //    method POST
    public static final String URL_UPDATE = ROOT_API + "transaction/update/";

    //    method POST by ID
    public static final String URL_DELETE = ROOT_API + "transaction/delete/";
}

package uts.uajy.kelompok_b_jualonline.ReviewTest;

import android.app.Activity;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import uts.uajy.kelompok_b_jualonline.R;
import uts.uajy.kelompok_b_jualonline.api.ReviewAPI;
import www.sanju.motiontoast.MotionToast;

import static com.android.volley.Request.Method.POST;

public class ReviewService {

    public void addReview(ReviewView view, String id_user, String id_barang_parameter, String review, ReviewCallback callback){
        RequestQueue queue = Volley.newRequestQueue(view.getContext());

        //Memulai membuat permintaan request menghapus data ke jaringan
        StringRequest stringRequest = new StringRequest(POST, ReviewAPI.URL_CREATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Disini bagian jika response jaringan berhasil tidak terdapat ganguan/error
                try {
                    JSONObject obj = new JSONObject(response);
                    Toast.makeText(view.getContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    callback.onSuccess(true);
                    Toast.makeText(view.getContext(), "Berhasil add", Toast.LENGTH_SHORT).show();
                    MotionToast.Companion.createColorToast(view.getEditActivity(),"Review Added","You've successfully published your product review",
                            MotionToast.TOAST_SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(view.getContext(), R.font.helvetica_regular));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                view.showAddError("Add Review Failed");
                //Disini bagian jika response jaringan terdapat ganguan/error
                Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                /*
                    Disini adalah proses memasukan/mengirimkan parameter key dengan data value,
                    dan nama key nya harus sesuai dengan parameter key yang diminta oleh jaringan
                    API.
                */
                Map<String, String>  params = new HashMap<String, String>();
                params.put("id_user", id_user);
                params.put("id_barang", id_barang_parameter);
                params.put("review", review);
                return params;
            }
        };
        //Disini proses penambahan request yang sudah kita buat ke reuest queue yang sudah dideklarasi
        queue.add(stringRequest);
    }
    public void editReview(ReviewView view,String id_review, String review, ReviewCallback callback){
        //Pendeklarasian queue
        RequestQueue queue = Volley.newRequestQueue(view.getContext());

        //Memulai membuat permintaan request menghapus data ke jaringan
        StringRequest  stringRequest = new StringRequest(POST, ReviewAPI.URL_UPDATE + id_review, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Disini bagian jika response jaringan berhasil tidak terdapat ganguan/error

                try {
                    //Mengubah response string menjadi object
                    JSONObject obj = new JSONObject(response);
                    //obj.getString("message") digunakan untuk mengambil pesan message dari response
//                    Toast.makeText(getApplicationContext(), "Review : "+review, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), ReviewAPI.URL_UPDATE + id_review, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                    MotionToast.Companion.createColorToast(view.getEditActivity(),"Review Edited !",obj.getString("message"),
                            MotionToast.TOAST_SUCCESS,
                            MotionToast.GRAVITY_BOTTOM,
                            MotionToast.LONG_DURATION,
                            ResourcesCompat.getFont(view.getContext(),R.font.helvetica_regular));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Disini bagian jika response jaringan terdapat ganguan/error
                Toast.makeText(view.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                /*
                    Disini adalah proses memasukan/mengirimkan parameter key dengan data value,
                    dan nama key nya harus sesuai dengan parameter key yang diminta oleh jaringan
                    API.
                */
                Map<String, String>  params = new HashMap<String, String>();
                params.put("review", review);
                return params;
            }
        };

        //Disini proses penambahan request yang sudah kita buat ke reuest queue yang sudah dideklarasi
        queue.add(stringRequest);
    }
    public Boolean getValid(final ReviewView view, String id_user_parameter, String id_barang_parameter, String review_parameter){
        // kenapa pake array kalo cuma dipake index pertama aja, knp gk variabel bool biasa
        final Boolean[] bool = new Boolean[1];
        addReview(view, id_user_parameter, id_barang_parameter, review_parameter , new ReviewCallback() {
            @Override
            public void onSuccess(boolean value) {
                bool[0] = true;
            }
            @Override
            public void onError() {
                bool[0] = false;
            }
        });
        return bool[0];
    }
}

package uts.uajy.kelompok_b_jualonline.ReviewTest;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class ReviewPresenter {
    private ReviewView view;
    private ReviewService service;
    private ReviewCallback callback;
    private String status;

    public ReviewPresenter(ReviewView view, ReviewService service) {
        this.view = view;
        this.service = service;
    }

    public void onAddReviewClicked(){
        loadReviewStatus();
        Toast.makeText(view.getContext(), status, Toast.LENGTH_SHORT).show();
        System.out.println(">>>>>>>>>>>> Masuk presenter");
        if(view.getReview().isEmpty()) {
            saveAddedReview("");
            view.showReviewError("Review tidak boleh kosong");
        }
        else {
            if(view.getStatus().equalsIgnoreCase("add")){
                service.addReview(view, view.getIdUser(), view.getIdBarang(), view.getReview(), new ReviewCallback() {
                    @Override
                    public void onSuccess(boolean value) {
//                        saveAddedReview("success");
                        view.startMainActivity();
                        view.getEditActivity().onBackPressed();
//                        view.getContext().getApplicationContext();
                    }

                    @Override
                    public void onError() {
                        saveAddedReview("");
                    }
                });
            }
            else{
                service.editReview(view, (view.getIdReview()), view.getReview(), new ReviewCallback() {
                    @Override
                    public void onSuccess(boolean value) {
//                        saveAddedReview("success");
                        view.startMainActivity();
                        view.getEditActivity().onBackPressed();
                    }

                    @Override
                    public void onError() {
                        saveAddedReview("");
                    }
                });
            }
                return;
//            if(status.equalsIgnoreCase("add"))
//            {
//                addReview(id_user_from_sp, id_barang, review.getText().toString());
//                onBackPressed();
//            }
//            else {
//                editReview(Integer.parseInt(id_review), review.getText().toString());
//                onBackPressed();
//            }
        }
    }
    public void saveAddedReview(String status) {
        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("status", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("review_add",status);
        editor.commit();
    }

    public void loadReviewStatus(){
        SharedPreferences sharedPreferences = view.getContext().getSharedPreferences("id_user", Context.MODE_PRIVATE);
        status = sharedPreferences.getString("id_user","");
    }
}

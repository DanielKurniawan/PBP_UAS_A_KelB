package uts.uajy.kelompok_b_jualonline.ReviewTest;

import android.app.Activity;
import android.content.Context;

public interface ReviewView {
    String getReview();
    String getIdUser();
    String getIdBarang();
    void showReviewError(String message);
    void startMainActivity();
    void showAddError(String message);
    void showErrorResponse(String message);
    Context getContext();
    Activity getEditActivity();
}

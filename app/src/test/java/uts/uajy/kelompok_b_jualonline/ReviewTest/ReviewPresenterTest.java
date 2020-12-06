package uts.uajy.kelompok_b_jualonline.ReviewTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ReviewPresenterTest {

    @Mock
    private ReviewView view;

    @Mock
    private ReviewService service;
    private ReviewPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ReviewPresenter(view, service);
    }

    @Test
    public void inputReviewKosong() throws Exception {
        when(view.getReview()).thenReturn("");
        System.out.println("Review          : "+view.getReview());

        presenter.onAddReviewClicked();

        verify(view).showReviewError("Review tidak boleh kosong");
    }

    @Test
    public void validAddReview() throws Exception {
        when(view.getReview()).thenReturn("Barang ini sangat bagus karena barang ini sangat baik sekali");
        System.out.println("Review          : "+view.getReview());

        when(service.getValid(view, view.getIdUser(), view.getIdBarang(), view.getReview())).thenReturn(true);

        System.out.println("Hasil           : " + service.getValid(view, view.getIdUser(), view.getIdBarang(), view.getReview()));;

        presenter.onAddReviewClicked();
    }
}
package class1.ex;

import class1.ex.MovieReview;

public class MovieReviewMain {
    public static void main(String[] args) {
        MovieReview inception = new MovieReview("인셉션", "인생은 무한 루프");
        MovieReview aboutTime = new MovieReview("어바웃 타임", "인생 시간 영화!");

        MovieReview[] movieReviews = {inception, aboutTime};

        for (MovieReview movieReview : movieReviews) {
            System.out.println("영화 제목: \"" + movieReview.title + "\", 리뷰: \""  + movieReview.review + "\"");
        }
    }
}

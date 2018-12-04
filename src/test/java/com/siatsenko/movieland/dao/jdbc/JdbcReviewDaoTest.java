package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.Review;
import com.siatsenko.movieland.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "/spring/test-context.xml")
public class JdbcReviewDaoTest {

    private JdbcReviewDao jdbcReviewDao;

    @Test
    public void getByMovieId() {

        List<Review> reviews = jdbcReviewDao.getByMovieId(2);

        assertEquals(reviews.size(), 1);

        Review review = reviews.get(0);
        assertEquals(review.getId(), 4);
        assertEquals(review.getText(), "Перестал удивляться тому, что этот фильм занимает сплошь первые места во всевозможных кино рейтингах. Особенно я люблю когда при экранизации литературного произведение из него в силу специфики кинематографа исчезает ирония и появляется некий сверхреализм, обязанный удерживать зрителя у экрана каждую отдельно взятую секунду. ");

    }

    @Autowired
    public void setJdbcReviewDao(JdbcReviewDao jdbcReviewDao) {
        this.jdbcReviewDao = jdbcReviewDao;
    }
}
package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.entity.common.Review;
import com.siatsenko.movieland.entity.common.Role;
import com.siatsenko.movieland.entity.common.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcReviewDaoTest {

    private JdbcReviewDao jdbcReviewDao;

    @Test
    public void getByMovieId() {
        List<Review> reviews = jdbcReviewDao.getByMovieId(2);

        assertEquals(1, reviews.size());

        Review review = reviews.get(0);
        assertEquals(4, review.getId());
        assertEquals("Перестал удивляться тому, что этот фильм занимает сплошь первые места во всевозможных кино рейтингах. Особенно я люблю когда при экранизации литературного произведение из него в силу специфики кинематографа исчезает ирония и появляется некий сверхреализм, обязанный удерживать зрителя у экрана каждую отдельно взятую секунду. ", review.getText());
    }

    @Test
    @DirtiesContext
    public void add() {
        List<Review> reviewsFirst = jdbcReviewDao.getByMovieId(1);
        assertEquals(2, reviewsFirst.size());

        User user = new User(1,"Рональд Рейнольдс","ronald.reynolds66@example.com", Role.ADMIN);
        Review review = new Review();
        review.setUser(user);
        review.setText("Классный фильм");

        jdbcReviewDao.add(1,review);
        List<Review> reviewsSecond = jdbcReviewDao.getByMovieId(1);
        assertEquals(3, reviewsSecond.size());
    }

    @Autowired
    public void setJdbcReviewDao(JdbcReviewDao jdbcReviewDao) {
        this.jdbcReviewDao = jdbcReviewDao;
    }

}
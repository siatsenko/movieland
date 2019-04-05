package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.report.ReportMovieDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ReportMovieDetailRowMapper implements RowMapper<ReportMovieDetail> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

        @Override
        public ReportMovieDetail mapRow(ResultSet resultSet, int rowId) throws SQLException {
            ReportMovieDetail reportMovieDetail = new ReportMovieDetail();
            reportMovieDetail.setMovieId(resultSet.getInt("movie_id"));
            reportMovieDetail.setTitle(resultSet.getString("title"));
            reportMovieDetail.setDescription(resultSet.getString("description"));
            reportMovieDetail.setGenre(resultSet.getString("genre"));
            reportMovieDetail.setPrice(resultSet.getDouble("price"));
            reportMovieDetail.setAddDate(resultSet.getDate("add_date").toLocalDate());
            reportMovieDetail.setModifiedDate(resultSet.getDate("modified_date").toLocalDate());
            reportMovieDetail.setRating(resultSet.getDouble("rating"));
            reportMovieDetail.setReviewsCount(resultSet.getInt("reviews_count"));

            logger.trace("mapRow finished and return reportMovieDetail: {}", reportMovieDetail);
            return reportMovieDetail;
        }
}

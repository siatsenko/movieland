package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.report.ReportUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ReportUserDetailRowMapper implements RowMapper<ReportUserDetail> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public ReportUserDetail mapRow(ResultSet resultSet, int rowId) throws SQLException {
        ReportUserDetail reportUserDetail = new ReportUserDetail();
        reportUserDetail.setUserId(resultSet.getInt("user_id"));
        reportUserDetail.setEmail(resultSet.getString("email"));
        reportUserDetail.setReviewsCount(resultSet.getInt("reviewsCount"));
        reportUserDetail.setAverageRate(resultSet.getDouble("averageRate"));

        logger.trace("mapRow finished and return reportUserDetail: {}", reportUserDetail);
        return reportUserDetail;
    }
}

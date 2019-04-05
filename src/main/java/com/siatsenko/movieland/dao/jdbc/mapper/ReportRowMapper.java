package com.siatsenko.movieland.dao.jdbc.mapper;

import com.siatsenko.movieland.entity.common.User;
import com.siatsenko.movieland.entity.report.Report;
import com.siatsenko.movieland.entity.report.ReportOutputType;
import com.siatsenko.movieland.entity.report.ReportStatus;
import com.siatsenko.movieland.entity.report.ReportType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Repository
public class ReportRowMapper implements RowMapper<Report> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Report mapRow(ResultSet resultSet, int i) throws SQLException {
        Report report = new Report();
        report.setId(resultSet.getInt("id"));
        report.setDeleted((resultSet.getInt("deleted") == 1) ? true : false);
        report.setStatus(ReportStatus.valueOf(resultSet.getString("status")));

        report.setFile(resultSet.getString("file"));
        report.setLink(resultSet.getString("link"));
        report.setDateFrom(resultSet.getDate("date_from").toLocalDate());
        report.setDateTo(resultSet.getDate("date_to").toLocalDate());

        report.setType(ReportType.valueOf(resultSet.getString("type")));
        report.setOutputType(ReportOutputType.valueOf(resultSet.getString("output_type")));
        int userId = resultSet.getInt("user_id");
        User user = new User(userId);
        report.setUser(user);

        logger.trace("mapRow finished and return report: {}", report);
        return report;
    }
}

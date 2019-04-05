package com.siatsenko.movieland.dao.jdbc;

import com.siatsenko.movieland.dao.ReportDao;
import com.siatsenko.movieland.dao.jdbc.mapper.ReportRowMapper;
import com.siatsenko.movieland.entity.report.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JbdcReportDao implements ReportDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private ReportRowMapper reportRowMapper;

    @Value("${queries.reports.activeReportsSql}")
    private String activeReportsSql;
    @Value("${queries.reports.addReportSql}")
    private String addReportSql;
    @Value("${queries.reports.editReportSql}")
    private String editReportSql;
    @Value("${queries.reports.cleanReportSql}")
    private String cleanReportSql;

    @Override
    public List<Report> getActiveReports() {
        List<Report> reports = jdbcTemplate.query(activeReportsSql, reportRowMapper);
        logger.trace("getActiveReports finished and return reports: {}", reports);
        return reports;
    }

    @Override
    public Report upsert(Report report) {
        String querySql;
        boolean withId = report.getId() != null;

        if (withId) {
            querySql = editReportSql;
        } else {
            querySql = addReportSql;
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("deleted", (report.isDeleted()) ? 1 : 0)
                .addValue("status", report.getStatus().toString())
                .addValue("file", report.getFile())
                .addValue("link", report.getLink())
                .addValue("type", report.getType().toString())
                .addValue("output_type", report.getOutputType().toString())
                .addValue("user_id", report.getUser().getId())
                .addValue("date_from", report.getDateFrom())
                .addValue("date_to", report.getDateTo());

        if (withId) {
            mapSqlParameterSource.addValue("id", report.getId());
        }

        String[] returningArray = new String[]{"id"};

        namedParameterJdbcTemplate.update(querySql, mapSqlParameterSource, keyHolder, returningArray);
        if (!withId) {
            int reportId = keyHolder.getKey().intValue();
            report.setId(reportId);
        }

        logger.trace("upsert() finished and return report: {}", report);
        return report;
    }

    @Override
    public void cleanDone() {
        jdbcTemplate.update(cleanReportSql);
        logger.trace("cleanDone() finished");
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    public void setReportRowMapper(ReportRowMapper reportRowMapper) {
        this.reportRowMapper = reportRowMapper;
    }

}

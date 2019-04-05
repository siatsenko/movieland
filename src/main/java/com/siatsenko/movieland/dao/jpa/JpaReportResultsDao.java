package com.siatsenko.movieland.dao.jpa;

import com.siatsenko.movieland.dao.ReportResultsDao;
import com.siatsenko.movieland.entity.report.ReportMovieDetail;
import com.siatsenko.movieland.entity.report.ReportUserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.stream.Stream;

@Repository
public class JpaReportResultsDao implements ReportResultsDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${queries.reports.allReportMovieDetailSql}")
    private String allReportMovieDetailSql;
    @Value("${queries.reports.periodReportMovieDetailSql}")
    private String periodReportMovieDetailSql;
    @Value("${queries.reports.top5UserDetailSql}")
    private String top5UserDetailSql;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Stream<ReportMovieDetail> getAllReportMovieDetail() {
        Stream<ReportMovieDetail> stream = entityManager.createNativeQuery(allReportMovieDetailSql, ReportMovieDetail.class).getResultStream();
        logger.trace("getAllReportMovieDetail() finished");
        return stream;
    }

    @Override
    public Stream<ReportMovieDetail> getPeriodReportMovieDetail(LocalDate dateFrom, LocalDate dateTo) {
        Query query = entityManager.createNativeQuery(periodReportMovieDetailSql, ReportMovieDetail.class);
        query.setParameter("date_from", dateFrom);
        query.setParameter("date_to", dateTo);
        Stream<ReportMovieDetail> stream = query.getResultStream();
        logger.trace("getPeriodReportMovieDetail({},{}) finished",dateFrom, dateTo);
        return stream;
    }

    @Override
    public Stream<ReportUserDetail> getTop5UserDetail() {
        Stream<ReportUserDetail> stream = entityManager.createNativeQuery(top5UserDetailSql, ReportUserDetail.class).getResultStream();
        logger.trace("getTop5UserDetail() finished");
        return stream;
    }

}

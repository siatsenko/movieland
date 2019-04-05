package com.siatsenko.movieland.entity.report;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReportUserDetail extends ReportDetail {
    @Id
    private int userId;
    private String email;
    private int reviewsCount;
    private double averageRate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(double averageRate) {
        this.averageRate = averageRate;
    }

    @Override
    public String toString() {
        return "ReportUserDetail{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", reviewsCount=" + reviewsCount +
                ", averageRate=" + averageRate +
                '}';
    }
}

package ui_DayCare.model;

import java.util.Objects;

public class AnnualEnrollmentStat implements Comparable<AnnualEnrollmentStat> {
    private String year;
    private Long enrollmentCount = Long.valueOf(0), unEnrollmentCount = Long.valueOf(0);

    public AnnualEnrollmentStat(String year, Long enrollmentCount, Long unEnrollmentCount) {
        this.year = year;
        this.enrollmentCount = enrollmentCount;
        this.unEnrollmentCount = unEnrollmentCount;
    }

    public AnnualEnrollmentStat() {
    }

    public AnnualEnrollmentStat(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getEnrollmentCount() {
        return enrollmentCount;
    }

    public void setEnrollmentCount(Long enrollmentCount) {
        this.enrollmentCount = enrollmentCount;
    }

    public Long getUnEnrollmentCount() {
        return unEnrollmentCount;
    }

    public void setUnEnrollmentCount(Long unEnrollmentCount) {
        this.unEnrollmentCount = unEnrollmentCount;
    }

    public void incrementEnrollment() {
        this.enrollmentCount++;
    }

    public void incrementUnEnrollment() {
        this.unEnrollmentCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnualEnrollmentStat that = (AnnualEnrollmentStat) o;
        return year.equals(that.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year);
    }

    @Override
    public int compareTo(AnnualEnrollmentStat o) {
        return this.getYear().compareTo(o.getYear());
    }

    @Override
    public String toString() {
        return "AnnualEnrollmentStat{" +
                "year='" + year + '\'' +
                ", enrollmentCount=" + enrollmentCount +
                ", unEnrollmentCount=" + unEnrollmentCount +
                '}';
    }
}

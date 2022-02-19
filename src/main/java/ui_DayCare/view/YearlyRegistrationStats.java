package ui_DayCare.view;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import ui_DayCare.dao.StudentDAO;
import ui_DayCare.model.AnnualEnrollmentStat;
import ui_DayCare.utils.Helper;
import ui_DayCare.utils.db.DBInit;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class YearlyRegistrationStats {
    CategoryDataset dataset;
    JFreeChart chart;

    public YearlyRegistrationStats() {
        StudentDAO studentDAO = new StudentDAO();
        // create dataset object
        dataset = createDataset(studentDAO.getAnnualEnrollments());
        // create chart object
        chart = ChartFactory.createBarChart(
                "Annual student registration renewal", //Chart Title
                "Year", // Category axis
                "Student counts", // Value axis
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );
    }

    public JLabel getChart() {
        JLabel jLabel = new JLabel(new ImageIcon(chart.createBufferedImage(700, 700)));
        return jLabel;
    }

    private CategoryDataset createDataset(List<AnnualEnrollmentStat> annualEnrollmentStats) {
        Double nil = 0.02;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        annualEnrollmentStats.stream().sorted().forEach(x -> {
            dataset.addValue((x.getEnrollmentCount() == 0) ? nil : x.getEnrollmentCount(), "Enrolled", x.getYear());
            dataset.addValue((x.getUnEnrollmentCount() == 0) ? nil : x.getUnEnrollmentCount(), "Un-enrolled", x.getYear());
        });
        return dataset;
    }

    public static void main(String[] args) {
        DBInit.initializeDataBase();
        YearlyRegistrationStats yearlyRegistrationStats = new YearlyRegistrationStats();
        Helper.wrapInWindow(yearlyRegistrationStats.getChart(), JFrame.DISPOSE_ON_CLOSE);
    }
}

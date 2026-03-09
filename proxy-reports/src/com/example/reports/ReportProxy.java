package com.example.reports;

public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();

    private RealReport real = null;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: " + user.getName() + " cannot access '" + title + "'");
            return;
        }
        if (real == null) {
            System.out.println("[proxy] first access — loading real report for " + reportId);
            real = new RealReport(reportId, title, classification);
        }
        real.display(user);
    }
}

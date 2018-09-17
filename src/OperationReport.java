
/**
 *
 * This class basically returns an operation report for whatever operation needs to be done.
 * 
 */
public class OperationReport {
    
    public boolean status;
    public String reportName;
    public String reportBody;
    
    public OperationReport(boolean status, String reportName, String reportBody){
        this.status = status;
        this.reportName = reportName;
        this.reportBody = reportBody;
    }
}

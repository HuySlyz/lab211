import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    private int TaskID;
    private String TaskTypeID;
    private String RequirementName;
    private Date date;
    private double planFrom;
    private double planTo;
    private String Assignee;
    private String Reviewer;
    
    //constructor
    public Task() {
    }
    
    public Task(int TaskID, String TaskTypeID, String RequirementName, Date date, double planFrom, double planTo,
            String Assignee, String Reviewer) {
        this.TaskID = TaskID;
        this.TaskTypeID = TaskTypeID;
        this.RequirementName = RequirementName;
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
        this.Assignee = Assignee;
        this.Reviewer = Reviewer;
    }

    public int getTaskID() {
        return TaskID;
    }

    public void setTaskID(int TaskID) {
        this.TaskID = TaskID;
    }

    public String getTaskTypeID() {
        return TaskTypeID;
    }

    public void setTaskTypeID(String TaskTypeID) {
        this.TaskTypeID = TaskTypeID;
    }

    public String getRequirementName() {
        return RequirementName;
    }

    public void setRequirementName(String RequirementName) {
        this.RequirementName = RequirementName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPlanFrom() {
        return planFrom;
    }

    public void setPlanFrom(double planFrom) {
        this.planFrom = planFrom;
    }

    public double getPlanTo() {
        return planTo;
    }

    public void setPlanTo(double planTo) {
        this.planTo = planTo;
    }

    public String getAssignee() {
        return Assignee;
    }

    public void setAssignee(String Assignee) {
        this.Assignee = Assignee;
    }

    public String getReviewer() {
        return Reviewer;
    }

    public void setReviewer(String Reviewer) {
        this.Reviewer = Reviewer;
    }
    
    //print out
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formatedDate = sdf.format(date);
        double time = planTo - planFrom;
        String planTime = String.format("%.1f", time);
        String result = String.format("%-7d%-20s%-12s%-15s%-7s%-15s%-15s", TaskID, RequirementName, TaskTypeID,
                formatedDate,
                planTime, Assignee, Reviewer);
        return result + "\n";
    }

}

import java.util.*;

public class TaskController {

    ArrayList<Task> list = new ArrayList<>();
    Task lastTask = new Task();

    // add task to list and return id, if can not add return -1
    int addTask(String taskTypeId, String requirementName, Date expertDate, double planFrom, double planTo,
            String assignee, String reviewer) throws Exception {
        int id = lastTask.getTaskID() + 1;
        lastTask = new Task(id, taskTypeId, requirementName, expertDate, planFrom, planTo, assignee, reviewer);
        list.add(lastTask);
        return id;
    }

    // delete task, id be checked in view
    public void deleteTask(String str) throws Exception {
        int id = Integer.parseInt(str);
        for (Task task : list) {
            if (task.getTaskID() == id) {
                list.remove(task);
                break;
            }
        }
    }

    // check if task exist or not
    public boolean checkExistTask(String id) {
        try {
            int checkID = Integer.valueOf(id);
            for (Task task : list) {
                if (task.getTaskID() == checkID) {
                    return true;
                }
            }
            return false;
            // id wrong format
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // check if task exist or not
    public boolean checkDuplicateTask(String taskTypeId, String requirementName, Date expertDate, double planFrom,
            double planTo, String assignee, String reviewer) {
        for (Task task : list) {
            if (task.getAssignee().equals(assignee)
                    && task.getDate().equals(expertDate)
                    && task.getPlanFrom() == planFrom
                    && task.getPlanTo() == (planTo)
                    && task.getRequirementName().equals(requirementName)
                    && task.getReviewer().equals(reviewer)
                    && task.getTaskTypeID().equals(taskTypeId)) {
                return true;
            }
        }
        return false;
    }

    // return list of task
    public ArrayList<Task> getDataTask() {
        return this.list;
    }

}

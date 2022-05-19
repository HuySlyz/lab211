
import Utils.Validation;
import java.util.*;

public class TaskView {

    TaskController controller = new TaskController();
    Scanner sc = new Scanner(System.in);

    // menu
    void menu() {
        try {
            while (true) {
                System.out.println("=====Task Program=====");
                System.out.println("1. Add Task");
                System.out.println("2. Delete Task");
                System.out.println("3. Display Task");
                System.out.println("4. exit");
                int options = Integer.parseInt(sc.nextLine());
                switch (options) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        deleteTask();
                        break;
                    case 3:
                        showTask();
                        break;
                    case 4:
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    // add task
    void addTask() {
        try {

            System.out.println("--------------Add Task---------------");
            // requirement name
            String requirementName;
            System.out.print("Requirement Name: ");
            requirementName = sc.nextLine();
            // check requirement name blank or not
            while (true) {
                if (Validation.checkBlank(requirementName)) {
                    System.out.print("Requirement Name: ");
                    requirementName = sc.nextLine();
                } else {
                    break;
                }
            }
            // task type id
            String taskTypeID;
            System.out.print("Task Type: ");
            taskTypeID = sc.nextLine();
            // check task type id and convert it to value 1- code, 2- test, 3- design,
            // 4-review
            while (true) {
                if (Validation.checkTaskTypeId(taskTypeID)) {
                    System.out.print("Task Type: ");
                    taskTypeID = sc.nextLine();
                } else {
                    break;
                }
            }
            switch (taskTypeID) {
                case "1":
                    taskTypeID = "Code";
                    break;
                case "2":
                    taskTypeID = "Test";
                    break;
                case "3":
                    taskTypeID = "Design";
                    break;
                case "4":
                    taskTypeID = "Review";
                    break;
                default:
                    break;
            }
            ;
            // date
            String date;
            System.out.print("Date: ");
            date = sc.nextLine();
            // check if date valid or not
            Date convertedDate = Validation.checkDate(date);
            while (true) {
                if (convertedDate == null) {
                    System.out.print("Date: ");
                    date = sc.nextLine();
                    convertedDate = Validation.checkDate(date);
                } else {
                    break;
                }
            }
            // check time of plan from
            double planFrom;
            System.out.print("From: ");
            planFrom = sc.nextDouble();
            while (true) {
                if (Validation.checkPlanFrom(planFrom)) {
                    System.out.print("From: ");
                    planFrom = sc.nextDouble();
                } else {
                    break;
                }

            }
            // check time of plan to
            double planTo;
            System.out.print("To: ");
            planTo = sc.nextDouble();
            while (true) {
                if (Validation.checkPlanTo(planTo, planFrom)) {
                    System.out.print("To: ");
                    planTo = sc.nextDouble();
                } else {
                    break;
                }

            }
            sc = new Scanner(System.in);
            // assignee
            System.out.print("Assignee: ");
            String assignee = sc.nextLine();
            while (true) {
                if (Validation.checkBlank(assignee)) {
                    System.out.print("Assignee: ");
                    assignee = sc.nextLine();
                } else {
                    break;
                }
            }
            // check reviewer, must not be blank
            System.out.print("Reviewer: ");
            String reviewer = sc.nextLine();
            while (true) {
                if (Validation.checkBlank(reviewer)) {
                    System.out.print("Reviewer: ");
                    reviewer = sc.nextLine();
                } else {
                    break;
                }
            }
            if (!controller.checkDuplicateTask(taskTypeID, requirementName, convertedDate, planFrom, planTo, assignee,
                    reviewer)) {
                int id = controller.addTask(taskTypeID, requirementName, convertedDate, planFrom, planTo, assignee,
                        reviewer);
                System.out.println("add task successfully");
                menu();
            } else {
                System.out.println("Task already existed");
            }
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    // delete task with input id
    void deleteTask() {
        try {
            System.out.println("----------Del Task-----------");
            System.out.print("ID: ");
            String id = sc.nextLine();
            // check id exist or not
            if (!controller.checkExistTask(id)) {
                System.out.println("ID invalid or not exist.");
            } else {
                // delete task in list
                controller.deleteTask(id);
                System.out.println("Task deleted");
                menu();
            }
        } catch (Exception e) {
            System.err.print(e);
        }
    }

    // print out all task
    void showTask() {
        if (controller.getDataTask().size() == 0) {
            System.out.println("Empty");
        } else {
            System.out.println(
                    "------------------------------------------Task----------------------------------------------");
            System.out.format("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n", "Id", "Name", "Task Type", "Date", "Time", "Assignee",
                    "Reviewer");
            for (Task a : controller.getDataTask()) {
                System.out.println(a);
            }
        }
    }
}

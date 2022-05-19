/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.text.*;
import java.util.*;

public class Validation {

    public static final Scanner sc = new Scanner(System.in);

    public static boolean checkBlank(String requirementName) {
        if (requirementName.length() < 1) {
            System.out.println("this field cannot be blank");
            return true;
        } else {
            return false;
        }
    }

    // check if id in range 1-4, if invalid return true, else return false
    public static boolean checkTaskTypeId(String id) {
        try {
            int digitID = Integer.parseInt(id);
            if (id.matches("\\d+")) {
                if (digitID <= 4 && digitID >= 1) {
                    return false;
                }
                System.out.println(" task type in range 1-4");
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("id must is number and not blank");
            return true;
        }
    }

    // return true if date wrong format or not exist
    public static Date checkDate(String input) {
        Date date;
        // create date format dd-MM-yyyy, (dd-mm-yyyy) can make wrong month compare
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        // sometime setLenient(true) can create hidden bug vd: 31-2 -> 3-3
        dateFormat.setLenient(false);
        try {
            // date regex check day and month in 1 or 2 digit, year is 4 digits
            String dateReg = "\\d{1,2}[-]\\d{1,2}[-]\\d{4}";
            if (input.matches(dateReg)) {
                date = dateFormat.parse(input);
                // create date now to compare date, if date in the pass, show error message
                Date now = new Date();
                if (date.compareTo(now) < 0) {
                    System.out.println("Date need in the future");
                    return null;
                }
            } else {
                System.out.println("please type date in format dd-mm-yyyy");
                return null;
            }
            // if date not exist, show error message
        } catch (ParseException e) {
            System.out.println("Date not exist");
            return null;
        }
        return date;
    }

    // check time start of task, from 8.0 -> 17.5 but it cant be 17.5 so set it to
    // max is 17.0
    public static boolean checkPlanFrom(double planFrom) {
        String regEx = "\\d+(\\.[0, 5])?";
        if (String.valueOf(planFrom).matches(regEx)) {
            if (planFrom < 8 || planFrom >= 17.5) {
                System.out.println("time start need in range: 8h-> 17h30");
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("time need to in format X.0 or X.5");
            return true;
        }
    }

    // check end time of task, must be less than start time,
    public static boolean checkPlanTo(double planTo, double planFrom) {
        String regEx = "\\d+(\\.[0, 5])?";
        if (String.valueOf(planTo).matches(regEx)) {
            if (planTo < 8 || planFrom > 17.5 || planTo <= planFrom) {
                System.out
                        .println("time start need in range: 8h-> 17h30 and time plan to need less than time plan from");
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("time need to in format X.0 or X.5");
            return true;
        }
    }

    // public static boolean checkReviewer(String reviewer, String assignee) {
    // if (reviewer.trim().equalsIgnoreCase(assignee)) {
    // System.out.println("reviewer cannot be assignee");
    // return true;
    // }
    // return false;
    // }
}

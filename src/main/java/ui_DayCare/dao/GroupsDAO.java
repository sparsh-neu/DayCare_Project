package ui_DayCare.dao;

import ui_DayCare.model.GroupInfo;
import ui_DayCare.model.Student;
import ui_DayCare.model.Teacher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupsDAO {
    // Generate 3 Groups for 6 - 12
    // Generate 3 Groups for 13 - 24
    // Generate 3 Groups for 25 - 35
    // Generate 3 Groups for 36 - 47
    // Generate 2 Groups for 48 - 59
    // Generate 2 Groups for 60 & up
    //Add function to pick the group and update it.
    public List<String[]> getListOfStudentsToDisplay() {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students = studentDAO.getStudents();

        List<Student> grp6To12 = students.stream()
                .filter(x -> x.getGroupInfo().getValue() == GroupInfo.SixToTwelve.getValue())
                .collect(Collectors.toList());

        List<Student> grp13To24 = students.stream()
                .filter(x -> x.getGroupInfo().getValue() == GroupInfo.ThirteenToTwentyFour.getValue())
                .collect(Collectors.toList());

        List<Student> grp25To35 = students.stream()
                .filter(x -> x.getGroupInfo().getValue() == GroupInfo.TwentyFiveToThirtyFive.getValue())
                .collect(Collectors.toList());

        List<Student> grp36To47 = students.stream()
                .filter(x -> x.getGroupInfo().getValue() == GroupInfo.ThirtySixToFortySeven.getValue())
                .collect(Collectors.toList());

        List<Student> grp48To59 = students.stream()
                .filter(x -> x.getGroupInfo().getValue() == GroupInfo.FortyEightToFiftyNine.getValue())
                .collect(Collectors.toList());

        List<Student> grp60Above = students.stream()
                .filter(x -> x.getGroupInfo().getValue() == GroupInfo.SixtyAbove.getValue())
                .collect(Collectors.toList());


        // Converting to Display Str
//        TeacherDAO teacherDao = new TeacherDAO();
//
//        List<Teacher> teachers = teacherDao.getAllTeachers().stream().map(x -> (Teacher)x).collect(Collectors.toList());
//
//        teachers.stream().forEach(System.out::println);
//
//        List<Teacher> teachers6To12 = teachers.stream()
//                .filter(x -> x.getAssignedGroup() == GroupInfo.SixToTwelve.getStringValue())
//                .collect(Collectors.toList());
//
//        List<Teacher> teachers13To24 = teachers.stream()
//                .filter(x -> x.getAssignedGroup() == GroupInfo.ThirteenToTwentyFour.getStringValue())
//                .collect(Collectors.toList());
//
//        List<Teacher> teachers25To35 = teachers.stream()
//                .filter(x -> x.getAssignedGroup() == GroupInfo.TwentyFiveToThirtyFive.getStringValue())
//                .collect(Collectors.toList());
//
//        List<Teacher> teachers36To47 = teachers.stream()
//                .filter(x -> x.getAssignedGroup() == GroupInfo.ThirtySixToFortySeven.getStringValue())
//                .collect(Collectors.toList());
//
//        List<Teacher> teachers48To59 = teachers.stream()
//                .filter(x -> x.getAssignedGroup() == GroupInfo.FortyEightToFiftyNine.getStringValue())
//                .collect(Collectors.toList());
//        List<Teacher> teachers60Above = teachers.stream()
//                .filter(x -> x.getAssignedGroup() == GroupInfo.SixtyAbove.getStringValue())
//                .collect(Collectors.toList());



        List<String[]> grp6To12ArrDisp = grp6To12.stream()
                .map(x -> x.toStringArrayWithGroup(GroupInfo.SixToTwelve.getStringValue(), "Daniel Peters"))
                .collect(Collectors.toList());

        List<String[]> grp13To24ArrDisp = grp13To24.stream()
                .map(x -> x.toStringArrayWithGroup(GroupInfo.ThirteenToTwentyFour.getStringValue(), "Dinesh Chugtai"))
                .collect(Collectors.toList());

        List<String[]> grp25To35ArrDisp = grp25To35.stream()
                .map(x -> x.toStringArrayWithGroup(GroupInfo.TwentyFiveToThirtyFive.getStringValue(), "Gilfoyle Bertram"))
                .collect(Collectors.toList());

        List<String[]> grp36To47ArrDisp = grp36To47.stream()
                .map(x -> x.toStringArrayWithGroup(GroupInfo.ThirtySixToFortySeven.getStringValue(), "Robin Hillyard"))
                .collect(Collectors.toList());

        List<String[]> grp48To59ArrDisp = grp48To59.stream()
                .map(x -> x.toStringArrayWithGroup(GroupInfo.FortyEightToFiftyNine.getStringValue(), "Raushan Khan"))
                .collect(Collectors.toList());

        List<String[]> grp60AboveArrDisp = grp60Above.stream()
                .map(x -> x.toStringArrayWithGroup(GroupInfo.SixtyAbove.getStringValue(),"Bo fu"))
                .collect(Collectors.toList());

        return Stream.of(grp6To12ArrDisp, grp13To24ArrDisp, grp25To35ArrDisp, grp36To47ArrDisp, grp48To59ArrDisp, grp60AboveArrDisp)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}

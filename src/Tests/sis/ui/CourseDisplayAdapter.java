package Tests.sis.ui;

import Tests.sis.studentinfo.Course;

public class CourseDisplayAdapter extends Course {
    private Course course;

    public CourseDisplayAdapter(Course course) {
        super(course.getDepartment(), course.getNumber());
        this.course = course;
    }

    @Override
    public String toString() {
        return course.getDepartment() + "-" + course.getNumber();
    }
}

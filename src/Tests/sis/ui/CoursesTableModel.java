package Tests.sis.ui;

import javax.swing.table.*;
import java.text.*;
import java.util.*;
import Tests.sis.studentinfo.*;

class CoursesTableModel extends AbstractTableModel {
    private List<Course> courses = new ArrayList<Course>();
    private SimpleDateFormat formatter =
            new SimpleDateFormat("MM/dd/yy");
    private FieldCatalog catalog = new FieldCatalog();
    private String[] fields = {
            FieldCatalog.DEPARTMENT_FIELD_NAME,
            FieldCatalog.NUMBER_FIELD_NAME,
            FieldCatalog.EFFECTIVE_DATE_FIELD_NAME };

    void add(Course course) {
        courses.add(course);
        fireTableRowsInserted(courses.size() - 1, courses.size());
    }

    Course get(int index) {
        return courses.get(index);
    }

    public String getColumnName(int column) {
        Field field = catalog.get(fields[column]);
        return field.getShortName();
    }

    // métodos abstratos (obrigatórios): getValueAt, getColumnCount, getRowCount
    public Object getValueAt(int row, int column) {
        Course course = courses.get(row);
        String fieldName = fields[column];
        if (fieldName.equals(FieldCatalog.DEPARTMENT_FIELD_NAME))
            return course.getDepartment();
        else if (fieldName.equals(FieldCatalog.NUMBER_FIELD_NAME))
            return course.getNumber();
        else if (fieldName.equals(FieldCatalog.EFFECTIVE_DATE_FIELD_NAME))
            return formatter.format(course.getEffectiveDate());
        return "";
//        switch (column) {
//            case 0: return course.getDepartment();
//            case 1: return course.getNumber();
//            case 2: return formatter.format(course.getEffectiveDate());
//            default: return "";
//        }
    }

    public int getColumnCount() {
        return fields.length;
    }

    public int getRowCount() {
        return courses.size();
    }
}

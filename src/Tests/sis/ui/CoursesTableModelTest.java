package Tests.sis.ui;

import junit.framework.*;
import Tests.sis.studentinfo.*;
import util.*;
import java.util.*;

public class CoursesTableModelTest extends TestCase {
    private CoursesTableModel model;
    protected void setUp() {
        model = new CoursesTableModel();
    }

    public void testCreate() {
        assertEquals(0, model.getRowCount());
        assertEquals(3, model.getColumnCount());
        FieldCatalog catalog = new FieldCatalog();
        Field department =
                catalog.get(FieldCatalog.DEPARTMENT_FIELD_NAME);
        assertEquals(department.getShortName(), model.getColumnName(0));
        Field number = catalog.get(FieldCatalog.NUMBER_FIELD_NAME);
        assertEquals(number.getShortName(), model.getColumnName(1));
        Field effectiveDate =
                catalog.get(FieldCatalog.EFFECTIVE_DATE_FIELD_NAME);
        assertEquals(effectiveDate.getShortName(),
                model.getColumnName(2));
    }

    public void testAddRow() {
        Course course = new Course("CMSC", "110");
        course.setEffectiveDate(DateUtil.createDate(2006, 3, 17));
        model.add(course);
        assertEquals(1, model.getRowCount());
        final int row = 0;
        assertEquals("CMSC", model.getValueAt(row, 0));
        assertEquals("110", model.getValueAt(row, 1));
        assertEquals("03/17/06", model.getValueAt(row, 2));
    }
}

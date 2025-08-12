package Tests.sis.ui;

import javax.swing.*;
import javax.swing.text.*;
import java.util.*;
import java.text.*;

import Tests.sis.studentinfo.DateUtil;
import junit.framework.*;
import util.*;

public class TextFieldFactoryTest extends TestCase {
    private Field fieldSpec;
    private static final String FIELD_NAME = "fieldName";
    private static final int COLUMNS = 1;

    protected void setUp() {
        fieldSpec = new Field(FIELD_NAME);
        fieldSpec.setColumns(COLUMNS);
    }

    public void testCreateSimpleField() {
        final String textValue = "value";
        fieldSpec.setInitialValue(textValue);
        JTextField field = TextFieldFactory.create(fieldSpec);
        assertEquals(COLUMNS, field.getColumns());
        assertEquals(FIELD_NAME, field.getName());
        assertEquals(textValue, field.getText());
    }

    public void testLimit() {
        final int limit = 3;
        fieldSpec.setLimit(limit);
        JTextField field = TextFieldFactory.create(fieldSpec);
        AbstractDocument document = (AbstractDocument)field.getDocument();
        ChainableFilter chainableFilter = (ChainableFilter)document.getDocumentFilter();
        LimitFilter limitFilter = (LimitFilter) chainableFilter.getFilter();
        assertEquals(limit, limitFilter.getLimit());
    }

    public void testUpcase() {
        fieldSpec.setUpcaseOnly();
        JTextField field = TextFieldFactory.create(fieldSpec);
        AbstractDocument document = (AbstractDocument)field.getDocument();
        ChainableFilter chainableFilter = (ChainableFilter)document.getDocumentFilter();
        assertEquals(UpcaseFilter.class, chainableFilter.getFilter().getClass());
    }

    public void testMultipleFilters() {
        fieldSpec.setLimit(3);
        fieldSpec.setUpcaseOnly();
        JTextField field = TextFieldFactory.create(fieldSpec);
        AbstractDocument document = (AbstractDocument)field.getDocument();
        ChainableFilter firstFilter = (ChainableFilter)document.getDocumentFilter();
        ChainableFilter secondFilter = firstFilter.getNext();

//        Set<Class> filters = new HashSet<Class>();
//        filters.add(filter.getClass());
//        filters.add(filter.getNext().getClass());

        assertTrue(firstFilter.getFilter() instanceof LimitFilter);
        assertTrue(secondFilter.getFilter() instanceof UpcaseFilter);
    }

    public void testCreateFormattedField() {
        final int year = 2006;
        final int month = 3;
        final int day = 17;
        fieldSpec.setInitialValue(DateUtil.createDate(year, month, day));
        JFormattedTextField field = (JFormattedTextField)TextFieldFactory.create(fieldSpec);
        assertEquals(1, field.getColumns());
        assertEquals(FIELD_NAME, field.getName());
        DateFormatter formatter = (DateFormatter)field.getFormatter();
        SimpleDateFormat format = (SimpleDateFormat)formatter.getFormat();
        String pattern = "MM/dd/yy";
        assertEquals(pattern, format.toPattern());
        assertEquals(Date.class, field.getValue().getClass());
        assertEquals("03/17/06", field.getText());
        TestUtil.assertDateEquals(year, month, day,
                (Date)field.getValue()); // a new utility method
    }
}

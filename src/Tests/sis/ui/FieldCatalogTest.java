package Tests.sis.ui;

import junit.framework.*;
import static Tests.sis.ui.FieldCatalog.*;

public class FieldCatalogTest extends TestCase {
    public void testAllFields() {
        FieldCatalog catalog = new FieldCatalog();
        assertEquals(3, catalog.size());
        Field field = catalog.get(NUMBER_FIELD_NAME);
        assertEquals(DEFAULT_COLUMNS, field.getColumns());
        assertEquals(NUMBER_LABEL_TEXT, field.getLabel());
        assertEquals(NUMBER_FIELD_LIMIT, field.getLimit());

        field = catalog.get(DEPARTMENT_FIELD_NAME);
        assertEquals(DEFAULT_COLUMNS, field.getColumns());
        assertEquals(DEPARTMENT_LABEL_TEXT, field.getLabel());
        assertEquals(DEPARTMENT_FIELD_LIMIT, field.getLimit());
        assertTrue(field.isUpcaseOnly());

        field = catalog.get(EFFECTIVE_DATE_FIELD_NAME);
        assertEquals(DEFAULT_COLUMNS, field.getColumns());
        assertEquals(EFFECTIVE_DATE_LABEL_TEXT, field.getLabel());
        assertSame(DEFAULT_DATE_FORMAT, field.getFormat());
    }
}

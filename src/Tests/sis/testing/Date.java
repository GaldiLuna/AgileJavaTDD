package Tests.sis.testing;

public @interface Date {
    int month();
    int day();
    int year();

    /**
     * private void verifyEffectiveDate() {
     *   assertLabelText(EFFECTIVE_DATE_LABEL_NAME,
     *       EFFECTIVE_DATE_LABEL_TEXT);
     *   JFormattedTextField dateField =
     *       (JFormattedTextField)panel.getField(EFFECTIVE_DATE_FIELD_NAME);
     *   DateFormatter formatter = (DateFormatter)dateField.getFormatter();
     *   SimpleDateFormat format = (SimpleDateFormat)formatter.getFormat();
     *   assertEquals("MM/dd/yy", format.toPattern());
     *   assertEquals(Date.class, dateField.getValue().getClass());
     * }
     */
}

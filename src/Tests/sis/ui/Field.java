package Tests.sis.ui;

import java.text.DateFormat;
import java.util.Date;

public class Field {
    private String name;
    private String label;
    private String labelName;
    private int columns;
    private int limit;
    private boolean upcaseOnly;
    private Object initialValue;
    private DateFormat format;

    public Field(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean isUpcaseOnly() {
        return upcaseOnly;
    }

    public void setUpcaseOnly() {
        this.upcaseOnly = true;
    }

    public Object getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Object initialValue) {
        this.initialValue = initialValue;
    }

    public DateFormat getFormat() {
        return format;
    }

    public void setFormat(DateFormat format) {
        this.format = format;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}

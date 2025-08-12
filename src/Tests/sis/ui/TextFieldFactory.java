package Tests.sis.ui;
import Tests.sis.ui.*;

import javax.swing.*;
import javax.swing.text.*;

public class TextFieldFactory {
    public static JTextField create(Field fieldSpec) {
        JTextField field;

        if (fieldSpec.getFormat() != null) {
            //JFormattedTextField formattedField = createFormattedTextField(fieldSpec);
            field = createFormattedTextField(fieldSpec);
        } else {
            field = new JTextField();
            if (fieldSpec.getInitialValue() != null) {
                field.setText(fieldSpec.getInitialValue().toString());
            }
        }

        if (fieldSpec.getLimit() > 0) {
            attachLimitFilter(field, fieldSpec.getLimit());
        }
        if (fieldSpec.isUpcaseOnly()) {
            attachUpcaseFilter(field);
        }

        field.setColumns(fieldSpec.getColumns());
        field.setName(fieldSpec.getName());
        return field;
    }

    private static void attachLimitFilter(JTextField field, int limit) {
        attachFilter(field, new ChainableFilter(new LimitFilter(limit)));
    }

    private static void attachUpcaseFilter(JTextField field) {
        attachFilter(field, new ChainableFilter(new UpcaseFilter()));
    }

    private static void attachFilter(JTextField field, ChainableFilter filter) {
        AbstractDocument document = (AbstractDocument)field.getDocument();
        DocumentFilter currentFilter = document.getDocumentFilter();

        if (currentFilter == null) {
            document.setDocumentFilter(filter);
        } else if (currentFilter instanceof ChainableFilter) {
            ChainableFilter lastFilter = (ChainableFilter) currentFilter;
            while (lastFilter.getNext() != null && lastFilter.getNext() instanceof ChainableFilter) {
                lastFilter = lastFilter.getNext();
            }
            lastFilter.setNext(filter);
        } else {
            throw new IllegalStateException("O primeiro filtro não é um ChainableFilter.");
        }

//        ChainableFilter existingFilter =
//                (ChainableFilter)document.getDocumentFilter();
//        if (existingFilter == null)
//            document.setDocumentFilter(filter);
//        else
//            existingFilter.setNext(filter);
    }

    private static JFormattedTextField createFormattedTextField(Field fieldSpec) {
        JFormattedTextField field = new JFormattedTextField(fieldSpec.getFormat());
        field.setValue(fieldSpec.getInitialValue());
        return field;
    }
}

package Tests.sis.ui;

import javax.swing.text.*;

public class UpcaseFilter extends DocumentFilter {

    @Override
    public void insertString(
            DocumentFilter.FilterBypass bypass,
            int offset,
            String text,
            AttributeSet attr) throws BadLocationException {
        bypass.insertString(offset, text.toUpperCase(), attr);
    }

    @Override
    public void replace(
            DocumentFilter.FilterBypass bypass,
            int offset,
            int length,
            String text,
            AttributeSet attr) throws BadLocationException {
        if (text != null) {
            text = text.toUpperCase();
        }
        bypass.replace(offset, length, text, attr);
    }
}

package Tests.sis.ui;

import javax.swing.text.*;

public class LimitFilter extends DocumentFilter {
    private int limit;

    public LimitFilter(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public void insertString(
            DocumentFilter.FilterBypass bypass,
            int offset,
            String str,
            AttributeSet attrSet) throws BadLocationException {
        replace(bypass, offset, 0, str, attrSet);
    }

    @Override
    public void replace(
            DocumentFilter.FilterBypass bypass,
            int offset,
            int length,
            String str,
            AttributeSet attrSet) throws BadLocationException {
        int newLength = bypass.getDocument().getLength() - length + (str != null ? str.length() : 0);
        if (newLength > limit)
            throw new BadLocationException(
                    "New characters exceeds max size of document", offset);
        bypass.replace(offset, length, str, attrSet);
    }
}

package Tests.sis.ui;

import javax.swing.text.*;

public class ChainableFilter extends DocumentFilter {
    private DocumentFilter filter;
    private ChainableFilter next;

    public ChainableFilter(DocumentFilter filter) {
        this.filter = filter;
    }

    public DocumentFilter getFilter() {
        return this.filter;
    }

    public void setNext(ChainableFilter next) {
        this.next = next;
    }

    public ChainableFilter getNext() {
        return next;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        filter.insertString(fb, offset, string, attr);
        if (next != null) {
            next.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        filter.replace(fb, offset, length, text, attrs);
        if (next != null) {
            next.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        filter.remove(fb, offset, length);
        if (next != null) {
            next.remove(fb, offset, length);
        }
    }
}

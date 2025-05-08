package Tests.sis.studentinfo;

public class Name {
    String first, mid, last;

    public Name(String first, String mid, String last) {
        this.first = first;
        this.mid = mid;
        this.last = last;
    }
    public Name(String first, String last) {
        this(first, "", last);
    }
}

import chess.BoardTest;
import chess.pieces.PawnTest;
import junit.framework.TestSuite;

//import org.junit.runner.RunWith;
//import org.junit.runners.Suite;
//@RunWith(Suite.class)
//@Suite.SuiteClasses({ PawnTest.class, BoardTest.class})

public class TestsSuite {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(PawnTest.class);
        suite.addTestSuite(BoardTest.class);
        return suite;
    }

}
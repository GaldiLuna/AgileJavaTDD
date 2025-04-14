import chess.BoardTest;
import chess.CharacterTest;
import chess.pieces.PawnTest;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses({ PawnTest.class, BoardTest.class, CharacterTest.class })

public class TestsSuite {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(PawnTest.class);
        suite.addTestSuite(BoardTest.class);
        suite.addTestSuite(CharacterTest.class);
        return suite;
    }

}


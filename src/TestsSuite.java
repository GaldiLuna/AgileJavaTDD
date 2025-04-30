import chess.BoardTest;
import chess.CharacterTest;
import chess.pieces.PieceTest;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    PieceTest.class,
    BoardTest.class,
    CharacterTest.class
})

public class TestsSuite{

}


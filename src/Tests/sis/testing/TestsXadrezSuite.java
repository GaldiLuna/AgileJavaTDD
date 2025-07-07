package Tests.sis.testing;

import chess.BoardTest;
import chess.CharacterTest;
import chess.pieces.PieceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//TESTES DO XADREZ

@RunWith(Suite.class)
@SuiteClasses({
    PieceTest.class,
    BoardTest.class,
    CharacterTest.class
})

public class TestsXadrezSuite {

}


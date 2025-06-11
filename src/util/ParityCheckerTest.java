package util;

import junit.framework.TestCase;

public class ParityCheckerTest extends TestCase {
    public void testSingleByte() {
        ParityChecker checker = new ParityChecker();
        byte source1 = 10; //1010
        byte source2 = 13; //1101
        byte source3 = 2; //0010
        byte[] data = new byte[] { source1, source2, source3 };
        byte checksum = 5; //0101
        assertEquals(checksum, checker.checksum(data));

        //corrompe o elemento source2
        data[1] = 14; //1110
        assertFalse(checksum == checker.checksum(data));
    }
}

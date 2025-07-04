package Tests;
import Tests.sis.db.DataFileTest;
import Tests.sis.db.KeyFileTest;
import Tests.sis.report.*;
import Tests.sis.studentinfo.*;
import util.*;
import Tests.sis.summer.SummerCourseSessionTest;
import Tests.sis.ui.StudentUITest;
import junit.framework.TestSuite;

public class StudentsAllTests {
    public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(TestStringUtil.class);
        suite.addTestSuite(ParityCheckerTest.class);
        suite.addTestSuite(PasswordGeneratorTest.class);
        suite.addTestSuite(StudentTest.class);
        suite.addTestSuite(CourseTest.class);
        suite.addTestSuite(CourseSessionTest.class);
        suite.addTestSuite(CourseReportTest.class);
        suite.addTestSuite(ReportCardTest.class);
        suite.addTestSuite(RosterReporterTest.class);
        suite.addTestSuite(StudentDirectoryTest.class);
        suite.addTestSuite(DateUtilTest.class);
        suite.addTestSuite(CourseCatalogTest.class);
        suite.addTestSuite(BasicGradingStrategyTest.class);
        suite.addTestSuite(HonorsGradingStrategyTest.class);
        suite.addTestSuite(SummerCourseSessionTest.class);
        suite.addTestSuite(PerformanceTest.class);
        suite.addTestSuite(ScorerTest.class);
        suite.addTestSuite(SerializationTest.class);
        suite.addTestSuite(StudentUITest.class);
        suite.addTestSuite(DataFileTest.class);
        suite.addTestSuite(KeyFileTest.class);
        suite.addTestSuite(IOUtilTest.class);
        //suite.addTestSuite(SessionTest.class); //Classe abstrata não pode ser chamada na suite.
        return suite;
    }
}
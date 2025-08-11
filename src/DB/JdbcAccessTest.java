package DB;

import junit.framework.TestCase;
import java.sql.*;
import java.util.List;

public class JdbcAccessTest extends TestCase {
    private JdbcAccess access;

    public void setUp() {
        access = new JdbcAccess("test");
    }

    public void testConnection() throws SQLException {
        JdbcAccess access = new JdbcAccess("test");
        Connection connection = null;
        try {
            connection = access.getConnection();
            assertFalse(connection.isClosed());
        }
        finally {
            connection.close();
        }
    }

    public void testExecute() throws SQLException {
        access.execute("create table testExecute (fieldA char)");
        try {
            assertEquals("fieldA", access.getFirstRowFirstColumn("desc testExecute"));
        }
        finally {
            access.execute("drop table testExecute");
        }
    }

    public void testQueryBy() throws SQLException {
        drop("testQueryBy");
        access.execute("create table testQueryBy (id varchar(10), name varchar(30))");

        PreparedStatement statement = null;
        try {
            access.execute("insert into testQueryBy values('123', 'schmoe')");
            access.execute("insert into testQueryBy values('234', 'patella')");

            statement = access.prepare("select id, name from testQueryBy where id =?");
            List<String> row = access.getUnique(statement, "234");
            assertEquals("234", row.get(0));
            assertEquals("patella", row.get(1));
            row = access.getUnique(statement, "123");
            assertEquals("123", row.get(0));
            assertEquals("schmoe", row.get(1));
        }
        finally {
            statement.close();
            drop("testQueryBy");
        }
    }

    private void drop(String tableName) {
        try {
            access.execute("drop table " + tableName);
        }
        catch (SQLException ignore) {
            // exceção lançada se a tabela não existir; não nos importamos
        }
    }
}

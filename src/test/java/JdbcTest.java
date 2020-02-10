import git.olin.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static git.olin.util.JdbcUtil.release;

public class JdbcTest {
    private final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private final String user = "scott";
    private final String password = "tiger";
    private final String className = "oracle.jdbc.driver.OracleDriver";
    private String sql = null;

    @Test
    public void testJdbcUtil(){
       Connection connection = JdbcUtil.getConnection();
        System.out.println(connection);
    }
    @Test
    public void testSelect() throws Exception{
        Class.forName(className);
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = conn.prepareStatement("select * from emp");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            int empno = rs.getInt("empno");
            String ename = rs.getString("ename");
            String job = rs.getString("job");
            int mgr = rs.getInt("mgr");
            java.sql.Date hiredate =rs.getDate("hiredate");
            int sal = rs.getInt("sal");
            int comm = rs.getInt("comm");
            int deptno = rs.getInt("deptno");
            System.out.println(empno + "-" + ename + "-" + job + "-" + mgr + "-"
                    + hiredate + "-" + sal + "-" + comm + "-" + deptno);
        }
        //关闭对象
        release(rs,preparedStatement,conn);
    }
}

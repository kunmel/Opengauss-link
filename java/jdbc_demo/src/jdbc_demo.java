import java.sql.*;

public class jdbc_demo {
    public static Connection GetConnection(String url, String username, String passwd) {
        Connection conn;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Get driver success!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        try {
            conn = DriverManager.getConnection(url, username, passwd);
            System.out.println("Connect to " + url + " success!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }
    public static void CloseConnection(Connection conn) {
        try {
            conn.close();
            System.out.println("Connection close success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void InsertData(Connection conn, String sql){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static ResultSet QueryData(Connection conn, String sql, Boolean ifshow){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(ifshow){ ShowResult(rs);}
            stmt.close();
            return rs;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void ShowResult(ResultSet rs){
        ResultSetMetaData md;
        int col;
        String labels = "";
        try {
            md = rs.getMetaData();
            col = md.getColumnCount();
            for(int i=1;i<col; i++) {
                String colName = md.getColumnName(i) + "\t";
                labels += colName;
            }
            System.out.println(labels);
            while(rs.next()){
                String row = "";
                for(int i=1;i<col; i++) {
                    String block = rs.getString(i) + "\t";
                    row += block;
                }
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
//        Connection c = null;
//        Statement stmt = null;
//        try {
//            // 获取数据库源，固定写法
//            Class.forName("org.postgresql.Driver");
//            c = DriverManager
//                    .getConnection("jdbc:postgresql://39.107.98.10:15432/newdb",
//                            "gaussdb", "Secretpassword@123");
//            // 连接成功
//            System.out.println("Opened database successfully");
////          这里创建一个类似于可视化工具中的console的那个脚本文件
//            stmt = c.createStatement();
////            这里写sql语句，做创建表的演示
//            String sql = "CREATE TABLE COMPANY2 " +
//                    "(ID INT PRIMARY KEY     NOT NULL," +
//                    " NAME           TEXT    NOT NULL, " +
//                    " AGE            INT     NOT NULL, " +
//                    " ADDRESS        CHAR(50), " +
//                    " SALARY         REAL)";
////            String sql = "DROP TABLE COMPANY";
//            stmt.executeUpdate(sql);
////            关闭脚本文件
//            stmt.close();
////            结束连接
//            c.close();
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
//            System.exit(0);
//        }
//        System.out.println("Table created successfully");

        String url = "jdbc:postgresql://39.107.98.10:15432/newdb";
        String username = "gaussdb";
        String passwd = "Secretpassword@123";
        //String sql = "insert into company2 values(2, 'guo', 21, 'liaoning')";
        String sql = "select * from company2";

        Connection conn = GetConnection(url, username, passwd);
        //InsertData(conn, sql);
        ResultSet rs = QueryData(conn, sql, true);
        CloseConnection(conn);
    }

}

package tools;

/**
 * Created by Chuckie on 11/17/15.
 */
import java.sql.*;

public class MysqlTool {
    public static String baseType = "com.mysql.jdbc.Driver";
    public String baseUrl = "";
    public String basename = "";
    public String port = "";
    public String userName = "";
    public String pwd = "";
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    public void DBConn(){
        try{
            Class.forName(baseType);
            String url = "jdbc:mysql://"+baseUrl+":"+port+"/"+this.getBasename()+"?"
                    + "user="+userName+"&password="+pwd+"&useUnicode=true&characterEncoding=UTF8";
            this.conn = DriverManager.getConnection(url);
            this.stmt = this.conn.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //执行查询语句
    public ResultSet excuteQuery(String strsql){
        try{
            this.rs = stmt.executeQuery(strsql);
            System.out.print(rs.toString());
            return this.rs;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }catch(NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }
    //执行update,insert,delete语句
    public boolean excuteOperate(String strsql){
        try{
            this.stmt.executeUpdate(strsql);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }catch(NullPointerException e){
            e.printStackTrace();
            return false;
        }
    }
    //获取查询结果集
    public boolean rs_next(){
        try{
            return this.rs.next();
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    //获取rs中的某一列
    public String rs_getString(String colum){
        try{
            return this.rs.getString(colum);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public String rs_getInt(int num){
        try{
            return String.valueOf(this.rs.getInt(num));
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    //设置和获取basename的方法
    public String getBasename(){
        return this.basename;
    }
    public void setBasename(String basename){
        this.basename = basename;
    }
    public void closeStmt() throws SQLException {
        this.stmt.close();
    }
    public void closeConn() throws SQLException {
        this.conn.close();
    }
    public void closeRs() throws SQLException {
        this.rs.close();
    }
}

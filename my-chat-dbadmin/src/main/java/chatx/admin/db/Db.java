package chatx.admin.db;


import chatx.admin.utils.LOG;
import chatx.admin.utils.PropertiesLoader;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

//**********************************************************************************************************************
public class Db
{
    static HikariDataSource pool; 
    //******************************************************************************************************************
    static
    {
        PropertiesLoader loader = new PropertiesLoader ();

        String host = loader._get ("db.host");
        String name = loader._get ("db.name");
        String user = loader._get ("db.user");
        String pswd = loader._get ("db.pswd");
        
        if (host==null     || name==null     || user==null     || pswd==null   ||
            host.isEmpty() || name.isEmpty() || user.isEmpty() || pswd.isEmpty())
        {
            LOG._err("Cannot create db connect due to database username/password unaccessible");
        }
        
        HikariConfig conf = new HikariConfig ();
        
        conf. setMaximumPoolSize(5);
        conf. setDataSourceClassName("org.postgresql.ds.PGPoolingDataSource");
        
        conf. addDataSourceProperty ("databaseName", name);
        conf. addDataSourceProperty ("serverName", host);
        conf. addDataSourceProperty ("password", pswd);            
        conf. addDataSourceProperty ("user", user);
        
        try
        {
            pool = new HikariDataSource(conf);        
            pool. setConnectionTimeout (800);
        }
        catch (Exception ex)
        {
            LOG._dbg(ex, "Postgres is not avalable");            
        }
    }
    //******************************************************************************************************************
    public static Connection _conn () throws SQLException
    {
        if (null == pool)
            return null;
        
        return pool.getConnection();
    }
    //******************************************************************************************************************
    public static boolean _isValid ()
    {
        return null != pool;
    }
    //******************************************************************************************************************
}

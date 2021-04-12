package chatx.serv.web.mod.service;

import chatx.serv.db.Db;
import chatx.serv.utils.LOG;
import chatx.serv.utils.Retrepa;
import com.cb.web.mod.service.TemplParamDbCheck;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//******************************************************************************
public class DbInstallModService
{
    protected boolean dbValid = false;
    protected boolean initRequired = false;

    //**************************************************************************
    public DbInstallModService ()
    {        
        super ();
    }    
    //**************************************************************************
    public TemplParamDbCheck check ()
    {
        Retrepa ret = new Retrepa ();
        
        String opera = 
          "SELECT DISTINCT tablename FROM pg_tables "
        + "WHERE tablename IN "
        + "("
                + "'user', "
                + "'phone', "
                + "'department', "
                + "'book_phone' "                
        + ") AND "
                + "schemaname='public'";
        try
        {
            try (Connection conn = Db._conn();)
            {
                /*
                if (conn != null)
                {
                    DatabaseMetaData meta= conn.getMetaData();
                    int verT = meta.getJDBCMajorVersion ();
                    int verB = meta.getJDBCMinorVersion ();
                    boolean supp = meta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);

                    String mess = "JDBC ver " + verT + "." + verB + ". Scroll support " + supp;                
                    LOG._info(mess);
                }*/
                        
                try ( Statement stmt = 
                conn.createStatement (ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                      ResultSet.CONCUR_READ_ONLY);)
                 {
                        ResultSet rs = stmt.executeQuery(opera);

                        int rowcount=0;                        
                        /*
                        List check = new ArrayList();                        
                        while(rs.next())
                        {
                            check.add (rs.getString("tablename"));                            
                        }
                        */
                        if(rs.last())
                            rowcount = rs.getRow();                        
                        
                        switch (rowcount)
                        {
                            case 0:
                                initRequired = true;
                                dbValid = true;
                                
                                ret.msg = ""; //"Требуется инициализация базы данных.";
                                ret.ret = true;
                                ret.code = 1;
                            break;
                            
                            case 4:
                                initRequired = false;
                                dbValid = true;
                                
                                ret.msg = "Похоже структура базы данных годная, инициализация не требуется.";
                                ret.ret = true;
                                ret.code = 2;
                            break;
                                
                            default:
                                initRequired = false;
                                dbValid = false;
                                
                                ret.msg = "Схема public не пуста."
                                        + "Существующая структура таблиц базы данных отличается от проектной."
                                        + "При манипуляции с ними возможна потеря данных."
                                        + "Обратитесь к администратору базы данных для исправления ситуации.";
                                ret.ret = false;
                                ret.code = 3;
                        }
                        
                        LOG._dbg_if (ret.msg);
                 }            
            }
            catch (SQLException ex)
            {                
                initRequired = false;
                dbValid = false;
                
                ret.msg = "Соединение к базе данных с предоставленными параметрами невозможно. Обратитесь к администратору системы.";
                ret.ret = false;
                ret.code = 4;
                
                LOG._dbg(ex, ret.msg);
            }        
        }
        catch (Exception ex)
        {                            
            initRequired = false;
            dbValid = false;

            ret.msg = "Соединение к базе данных с предоставленными параметрами невозможно. Обратитесь к администратору системы.";
            ret.ret = false;
            ret.code = 5;
            
            LOG._dbg(ex, ret.msg);
        }
        //////////////////////////////////////////
        
        String color = ret.ret ? "" : "color:red;";
        
        return isInitRequired() ? new TemplParamDbCheck (ret.msg, "", true) : new TemplParamDbCheck (ret.msg, color, false);        
    }
    //**************************************************************************        
    public Retrepa create ()
    {
        Retrepa ret = new Retrepa ();
        try
        {
            try (Connection conn = Db._conn();)
            {
                conn.setAutoCommit(false);
                try ( Statement stmt = conn.createStatement ();)
                {  
                    stmt.executeUpdate (PSql.CreateTables);
                    stmt.executeUpdate (PSql.CreateIdxs);
                        
                    conn.commit();

                    ret.code = 0;
                    ret.ret  = true;
                    ret.msg  = "Таблицы разверныты штатно.";
                }            
                catch (SQLException ex)
                {       
                    conn.rollback();
                    initRequired = false;
                    dbValid = false;

                    ret.msg = "Создание проектных таблиц при текущих настройках базы данных невозможно. Обратитесь к администратору системы.";
                    ret.ret = false;
                    ret.code = 1;
                }
                finally {conn.setAutoCommit(false);}
            }
            catch (SQLException ex)
            {   
                initRequired = false;
                dbValid = false;
           /*     
                ret.msg = "Соединение к базе данных с предоставленными параметрами невозможно. Обратитесь к администратору системы.";
                ret.ret = false;
                ret.code = 2;
*/
            }        
        }
        catch (Exception ex)
        {                            
            initRequired = false;
            dbValid = false;

            ret.msg = "Выполнение затребованной операции невозможно. Обратитесь к администратору системы.";
            ret.ret = false;
            ret.code = 3;
        }
            
        LOG._dbg(ret.msg);
        return ret;
    }
    //**************************************************************************    
    public boolean isDbValid()
    {
        return dbValid;
    }
    public boolean isInitRequired()
    {
        return initRequired;
    }
    public void setDbValid(boolean dbValid)
    {
        this.dbValid = dbValid;
    }
    public void setInitRequired(boolean initRequired)
    {
        this.initRequired = initRequired;
    }    

    //**************************************************************************    
}
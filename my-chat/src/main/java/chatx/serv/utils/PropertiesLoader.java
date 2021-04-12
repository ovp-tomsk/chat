package chatx.serv.utils;

import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

 //**************************************************************************
public class PropertiesLoader
{
    protected  Logger log = Logger.getLogger (PropertiesLoader.class.getName());

    protected   Properties fProp        = null;
    final protected String fPropPath  = "/app.properties";
    //**************************************************************************

    public PropertiesLoader ()
    {
        try
        {
            fProp  = new Properties();
            InputStream inStrm = getClass().getResourceAsStream(fPropPath);
            fProp. load (inStrm);
        }
        catch(Exception e)
        {
            log. error (
                    "Исключение во время инициализации PropertiesLoader с '"
                    + fPropPath 
                    + "'. Exception: "+e.getMessage ());
        }
    }
    //**************************************************************************
    public String _get (String key)
    {
        String val;
        try
        {
            val = fProp. getProperty (key);
            if (null == val)
            {
                log. error ("Не могу взять ключ '" + key + "'");
                return "";
            }
        }
        catch(Exception e)
        {
            log. error (
                    "Props !!!! Не могу взять ключ '"
                    + key + "'"
                    + "' . Exception: "+e.getMessage ());
            return "";
        }

        return val;
    }
    //**************************************************************************    
}

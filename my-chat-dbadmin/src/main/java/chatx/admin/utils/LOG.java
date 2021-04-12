package chatx.admin.utils;

//**********************************************************************************************************************

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.log4j.Logger;

public class LOG
{
    private static final String PREF_DBG =  "<::_DBG__> ";
    private static final String PREF_INF =  "<::_INFO_> ";
    private static final String PREF_ERR =  "<::_ERR__> ";    
    
    //******************************************************************************************************************
    static protected boolean b_do_dbg_trace = false;
    static protected boolean b_do_info_trace = false;    
    
    static protected Logger log = Logger.getLogger (LOG.class.getName());
    static private   AtomicInteger run_counter = new AtomicInteger (0);
    
    static
    {
            PropertiesLoader loader = new PropertiesLoader ();
            String do_trace = loader._get ("app.dbg_trace");
            String do_info_trace = loader._get ("app.info_trace");

            if (do_trace. equalsIgnoreCase("on"))
                b_do_dbg_trace = true;  

            if (do_info_trace. equalsIgnoreCase("on"))
                b_do_info_trace = true;  

            int i = run_counter.incrementAndGet();

            log.info("\n\n\n  %%%%%%%%%%%%%  LOG started (" + i + " time)   %%%%%%%%%%%%%\n" +
                    "                 in thread " + Thread.currentThread().getName() + " %%%%" +
                    "\n\n\n");
    }
     // ****************************************************************************************************************
    private static String place (String prefix, String msg)
    {
            String out = "";
            Throwable t = new Throwable();
            StackTraceElement trace[] = t.getStackTrace();

            if (trace.length > 2)
            {
                StackTraceElement el = trace[2];
                String[] ase = el.getClassName().split ("\\.");            

                out = String. format ("%s : [%-20s]::[%8s (), line %3d] : %s ", prefix, ase[ase.length-1], el.getMethodName(), el.getLineNumber(), msg);
            }
            return out;
    }
    //******************************************************************************************************************
    public static void _dbg_if (String msg)
    {
            if (!b_do_dbg_trace)
                return;

            if (msg.isEmpty())
                return;

            String message = place (LOG.PREF_DBG, msg);
            log.info(message);
    }    
    public static void _dbg (String msg)
    {
            if (!b_do_dbg_trace)
                return;

            String message = place (LOG.PREF_DBG, msg);
            log.info(message);
    }
    public static void _dbg (Exception exc, String msg)
    {
            if (!b_do_dbg_trace)
                return;

            String 
            message = place (LOG.PREF_DBG, msg);
            message = String.join ("", message, "Exception: ", exc.getClass().getSimpleName(), ": ", exc.getMessage());
            log.error (message);
    }    
    public static void _dbge (Exception exc)
    {
            if (!b_do_dbg_trace)
                return;

            String 
            message = place (LOG.PREF_DBG +"Exception: ", exc.getMessage ());        
            log.error (message);
    }
    //******************************************************************************************************************
    public static void _erre (Exception exc)
    {
            String message = place (LOG.PREF_ERR + "Exception: ", exc.getMessage());
            log.error (message);
    }   
    public static void _err (String msg)
    {
            String message = place (LOG.PREF_ERR, msg);
            log.error (message);
    }
    public static void _err (Exception exc, String msg)
    {       String 
            message = place (LOG.PREF_ERR, msg);
            message = String.join ("", message, "Exception: ", exc.getClass().getSimpleName(), ": ", exc.getMessage());
            log.error (message);
    }
    //******************************************************************************************************************
    public static void _selfCheckErr (String msg)
    {
            String message = place (LOG.PREF_ERR + "selfcheck: ", msg);
            log.error (message);
    }
    //******************************************************************************************************************
    public static void _info (String msg)
    {
        if (!b_do_info_trace)
            return;
        
        String message = place (LOG.PREF_INF, msg);
        log.info(message);
    }
    //******************************************************************************************************************
}
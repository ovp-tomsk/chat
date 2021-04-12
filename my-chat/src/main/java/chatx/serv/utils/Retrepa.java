package chatx.serv.utils;

//******************************************************************************
public class Retrepa
{
    public boolean ret   = false;
    public int     code  = 0;
    public String  msg   = "";
    
    public Retrepa ()
    {
    }
    
    public Retrepa (boolean ret)
    {
        this.ret = ret;
    }
    public Retrepa (boolean ret, String msg)
    {
        this.ret = ret;
        this.msg = msg;
    }
    public Retrepa (boolean ret, String msg, int code)
    {
        this.ret = ret;
        this.msg = msg;
        this.code = code;
    }
    //******************************************************************************
    public Retrepa return_self (String msg)
    {
        this.msg = msg;
        return this;
    }
    //******************************************************************************
    public Object return_null (String msg)
    {
        this.msg = msg;
        return null;
    }
    //******************************************************************************
    public Object return_null (boolean ret, String msg)
    {
        this.ret = ret;
        this.msg = msg;
        return null;
    }
    //******************************************************************************
    public Object return_null (boolean ret, String msg, int code)
    {
        this.ret = ret;
        this.msg = msg;
        this.code = code;
        
        return null;
    }
}
//******************************************************************************
package chatx.serv.web.controller;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//******************************************************************************
@Controller
public class UserRegController 
{
    private static AtomicInteger name_counter = new AtomicInteger (0);     
    //**************************************************************************    
    @RequestMapping (value = {"/user-reg.html", "/user-reg"},  
                     method = RequestMethod.GET,
                     produces = "text/html;charset=utf-8")    
    public String showPgUserReg (final Model model) 
    {                
        return "user-reg";
    }
        
    //**************************************************************************    
    @RequestMapping (value = {"/do-register-user", "/do-register-user.html"},  
                     method = RequestMethod.GET,
                     produces = "text/html;charset=utf-8")    
    public String doRegisterUser (final Model model) 
    {         
        boolean isRegistered = true;
       
        fillModel (model);              
        
        if (isRegistered)
            return "user-reg";
        else
            return "user-reg";
    }       
    //**************************************************************************    
    void fillModel (final Model model) 
    {
       
    }
    //**************************************************************************    
}

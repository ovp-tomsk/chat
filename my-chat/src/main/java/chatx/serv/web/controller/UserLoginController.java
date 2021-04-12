package chatx.serv.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class UserLoginController
{   
    //**************************************************************************    
    @RequestMapping (value = "/user-login",  
                     method = RequestMethod.GET,                      
                     produces = "text/html;charset=utf-8")
    
    public 
        String showPgUserLogin (final Model model) 
    {    
        //LOG._info("get /dbinstall page");
        //TemplParamDbCheck mRet = (new DbInstallModService ()).check ();                 
        //model.addAttribute("pgVars", mRet);        
            
        return "user-login";
    }        
    //**************************************************************************    
}

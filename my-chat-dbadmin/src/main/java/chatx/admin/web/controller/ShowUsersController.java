package chatx.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ShowUsersController
{   
    //**************************************************************************    
    @RequestMapping (value = "/show-users",  
                     method = RequestMethod.GET,                      
                     produces = "text/html;charset=utf-8")    
    public 
        String showUsers (final Model model) 
    {        
        return "show-users";
    }        
   //**************************************************************************             
}

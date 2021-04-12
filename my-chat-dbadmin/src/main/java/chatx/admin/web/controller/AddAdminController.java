package chatx.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AddAdminController
{   
    //**************************************************************************    
    @RequestMapping (value = "/add-admin",  
                     method = RequestMethod.GET,                      
                     produces = "text/html;charset=utf-8")    
    public 
        String showAddAdmin (final Model model) 
    {                    
        return "add-admin";
    }      
   //**************************************************************************    
}

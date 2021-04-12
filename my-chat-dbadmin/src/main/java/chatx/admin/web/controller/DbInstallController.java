package chatx.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class DbInstallController
{   
    //**************************************************************************    
    @RequestMapping (value = "/dbinstall",  
                     method = RequestMethod.GET,                      
                     produces = "text/html;charset=utf-8")
    
    public 
        String checkTables (final Model model) 
    {       
        return "dbinstall";
    }
    //**************************************************************************    
}

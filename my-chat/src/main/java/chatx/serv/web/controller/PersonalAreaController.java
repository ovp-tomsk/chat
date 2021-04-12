package chatx.serv.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//******************************************************************************
@Controller
public class PersonalAreaController 
{
    //**************************************************************************    
    @RequestMapping (value = {"/personal-area/", "/personal-area.html"},  
                     method = RequestMethod.GET,
                     produces = "text/html;charset=utf-8")    
    
    public String showPgPersonalArea () 
    {  
        return "personal-area";
    }
        
    //**************************************************************************    
}

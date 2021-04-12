package chatx.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//******************************************************************************
@Controller
public class IndexController 
{
    //**************************************************************************    
    @RequestMapping (value = {"/", "/index.html"},  
                     method = RequestMethod.GET,
                     produces = "text/html;charset=utf-8")    
    
    public String showIndex () 
    {  
        return "index";
    }
        
    //**************************************************************************    
}

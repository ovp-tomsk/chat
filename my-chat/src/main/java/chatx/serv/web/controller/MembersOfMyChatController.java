package chatx.serv.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//******************************************************************************
@Controller
public class MembersOfMyChatController
{
    //**************************************************************************    
    @RequestMapping (value = {"/members-of-my-chat/", "/members-of-my-chat.html"},  
                     method = RequestMethod.GET,
                     produces = "text/html;charset=utf-8")    
    
    public String showPgMembersOfMyChat () 
    {  
        return "members-of-my-chat";
    }
        
    //**************************************************************************    
}

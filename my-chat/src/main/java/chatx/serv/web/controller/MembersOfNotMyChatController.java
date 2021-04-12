package chatx.serv.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//******************************************************************************
@Controller
public class MembersOfNotMyChatController
{
    //**************************************************************************    
    @RequestMapping (value = {"/members-of-not-my-chat/", "/members-of-not-my-chat.html"},  
                     method = RequestMethod.GET,
                     produces = "text/html;charset=utf-8")    
    
    public String showPgMembersOfNotMyChat () 
    {  
        return "members-of-not-my-chat";
    }
        
    //**************************************************************************    
}

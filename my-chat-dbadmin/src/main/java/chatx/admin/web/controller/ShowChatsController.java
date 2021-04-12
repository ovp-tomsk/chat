package chatx.admin.web.controller;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//******************************************************************************
@Controller
public class ShowChatsController 
{
    private static AtomicInteger name_counter = new AtomicInteger (0);     
    //**************************************************************************    
    @RequestMapping (value = {"/show-chats.html", "/show-chats"},  
                     method = RequestMethod.GET,
                     produces = "text/html;charset=utf-8")    
    public 
        String showChats (final Model model) 
    {                
        return "show-chats";
    }
}

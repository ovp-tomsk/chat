package chatx.serv.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
//**********************************************************************************************************************

@Configuration
@EnableWebMvc
@ComponentScan
public class SpringWebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware
{
    private ApplicationContext applicationContext;
    //******************************************************************************************************************

    public SpringWebConfig() 
    {
        super();
    }
    //******************************************************************************************************************

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException    
    {
        this.applicationContext = applicationContext;
    }    
    
    //******************************************************************************************************************
    // статические ресурсы
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) 
    {
        super.addResourceHandlers (registry);
        
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**"). addResourceLocations("/js/");
    }
    //******************************************************************************************************************
    
    @Bean
    public SpringResourceTemplateResolver templateResolver()
    {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        
        templateResolver. setApplicationContext (this.applicationContext);
        
        templateResolver. setPrefix ("/WEB-INF/templates/");
        templateResolver. setSuffix (".html");
        
        templateResolver. setTemplateMode (TemplateMode.HTML);
        templateResolver. setCacheable (false);
        
        return templateResolver;
    }
    //******************************************************************************************************************

    @Bean
    public SpringTemplateEngine templateEngine()
    {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine ();
        
        templateEngine. setTemplateResolver (templateResolver());        
        templateEngine. setEnableSpringELCompiler (true);
        
        return templateEngine;
    }
    //******************************************************************************************************************

    @Bean
    public ThymeleafViewResolver viewResolver()
    {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver ();
        
        viewResolver. setTemplateEngine    (templateEngine());
        viewResolver. setCharacterEncoding ("UTF-8");
                
        return viewResolver;
    }
    //******************************************************************************************************************
}

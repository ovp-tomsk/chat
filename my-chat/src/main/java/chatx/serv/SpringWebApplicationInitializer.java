package chatx.serv;

import chatx.serv.business.SpringBusinessConfig;
import chatx.serv.web.SpringWebConfig;
import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//**********************************************************************************************************************
public class SpringWebApplicationInitializer  extends AbstractAnnotationConfigDispatcherServletInitializer
{
    //******************************************************************************************************************
    public SpringWebApplicationInitializer() 
    {
        super();
    }        
    //******************************************************************************************************************
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[] {SpringBusinessConfig.class};
    }
    
    //******************************************************************************************************************
    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[] {SpringWebConfig.class};        
    }

    //******************************************************************************************************************
    @Override
    protected String[] getServletMappings()
    {
        return new String[] {"/"};        
    }
    //******************************************************************************************************************
    @Override
    protected Filter[] getServletFilters() 
    {
        final CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        
        encodingFilter. setEncoding ("UTF-8");
        encodingFilter. setForceEncoding (true);
        encodingFilter. setForceResponseEncoding(true);        
        encodingFilter. setForceRequestEncoding(true);        
        
        return new Filter[] {encodingFilter};
    }
    //******************************************************************************************************************    
}

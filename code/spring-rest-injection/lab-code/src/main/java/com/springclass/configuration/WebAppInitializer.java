package com.springclass.configuration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Replaces web.xml in Servlet v.3.0+
 */
public class WebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {


    //-------------------------------------------------------------------//
    // Lab: Add Root Level context
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { JavaConfig.class };
    }



    //-------------------------------------------------------------------//
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { RestConfig.class };
    }



    //-------------------------------------------------------------------//
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/rest/*" }; // or *.do, *.view, *.form
    }





    @Override
    public void onStartup(final ServletContext servletContext)
            throws ServletException {

        // Register DispatcherServlet
        super.onStartup(servletContext);

        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter",
                CharacterEncodingFilter.class);

        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.setAsyncSupported(true);

        encodingFilter.addMappingForUrlPatterns(null, true, "/*");

    }

} // The End...

package com.springclass.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Replaces web.xml in Servlet v.3.0+
 */
public class WebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer
        implements WebApplicationInitializer {

    //-------------------------------------------------------------------//
    // Lab: Add Root Level context
	 @Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {JavaConfig.class};
	}
	
    //-------------------------------------------------------------------//
    // Lab: Add MvcConfig.class to the Servlet Configuration registry
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {MvcConfig.class};
	}

    //-------------------------------------------------------------------//
    // Lab: Add *.html to the Servlet Mapping
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"*.html"};
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

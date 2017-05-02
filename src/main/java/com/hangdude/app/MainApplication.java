package com.hangdude.app;

import static org.apache.commons.lang.StringUtils.isEmpty;

import java.io.File;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * A class that works as Tomcat server launcher
 * 
 * @author ahamouda
 *
 */
public class MainApplication {

	public static void main(String[] args) throws Exception {

		String webappDirLocation = "src/main/webapp/";
		Tomcat tomcat = new Tomcat();

		String webPort = System.getenv("PORT");
		if (isEmpty(webPort)) {
			webPort = "8080";
		}

		tomcat.setPort(Integer.valueOf(webPort));

		// try with /hangdude!
		StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

//		File configFile = new File(webappDirLocation + "WEB-INF/web.xml");
//		ctx.setConfigFile(configFile.toURI().toURL());

		 Tomcat.addServlet(ctx, "jersey-container-servlet", resourceConfig());
		 ctx.addServletMapping("/api/*", "jersey-container-servlet");

		tomcat.start();
		tomcat.getServer().await();
	}

	private static ServletContainer resourceConfig() {
		return new ServletContainer(new WebApplication());
	}
}
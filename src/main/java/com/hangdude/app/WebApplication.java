package com.hangdude.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * A class that replaces the <b>web.xml</b> file, by using the {@link ApplicationPath} annotation.
 */
@ApplicationPath("/hangdude")
public class WebApplication extends ResourceConfig {

	private final String[] packages = new String[] { "com.hangdude.controller" };

	public WebApplication() {
		packages(packages);
	}
}

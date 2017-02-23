package com.pcf.training.weatherapp.client;

public class Env {

	public static String serviceHost;
	public static String servicePort;
	public static String serviceAdminUserName;
	public static String serviceAdminPassword;

	static {
		serviceHost = System.getenv("T_SERVICE_HOST");
		// servicePort = System.getenv("T_SERVICE_PORT");
		// serviceAdminUserName = System.getenv("T_SERVICE_ADMIN_USERNAME");
		// serviceAdminPassword = System.getenv("T_SERVICE_ADMIN_PASSWORD");
	}
}

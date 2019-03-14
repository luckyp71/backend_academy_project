package com.training.models;

public class EmailConfig {
	
	private String host;
	
	private String port;
	
	private String ssl;
	
	private String auth;
	
	private String smtpPort;

	public EmailConfig() {
		this.host = "smtp.gmail.com";
		this.port = "465";
		this.ssl = "javax.net.ssl.SSLSocketFactory";
		this.auth = "true";
		this.smtpPort = "465";
	}
	
	public EmailConfig(String host, String port, String ssl,
			String auth, String smtpPort) {
		this.host = host;
		this.port = port;
		this.ssl = ssl;
		this.auth = auth;
		this.smtpPort = smtpPort;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSsl() {
		return ssl;
	}

	public void setSsl(String ssl) {
		this.ssl = ssl;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
}

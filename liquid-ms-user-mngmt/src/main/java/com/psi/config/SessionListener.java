package com.psi.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	final int HOUR = 60*60;

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setMaxInactiveInterval(HOUR*8);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
	}
}

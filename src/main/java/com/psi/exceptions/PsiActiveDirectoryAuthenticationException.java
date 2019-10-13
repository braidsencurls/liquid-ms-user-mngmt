package com.psi.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mrprintedwall on 2/23/16.
 */
public final class PsiActiveDirectoryAuthenticationException extends AuthenticationException {
	private final String dataCode;

	public PsiActiveDirectoryAuthenticationException(String dataCode, String message, Throwable cause) {
		super(message, cause);
		this.dataCode = dataCode;
	}

	public String getDataCode() {
		return this.dataCode;
	}
}

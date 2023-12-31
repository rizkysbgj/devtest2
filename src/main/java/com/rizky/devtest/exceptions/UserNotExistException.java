package com.rizky.devtest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotExistException extends RuntimeException {
	private static final long serialVersionUID = 193685211500040329L;
}

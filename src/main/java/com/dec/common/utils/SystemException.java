package com.dec.common.utils;

/**
 * 系统异常类
 * 
 * @author NanZihao
 * 
 */
public class SystemException extends RuntimeException {

	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = 1L;

}

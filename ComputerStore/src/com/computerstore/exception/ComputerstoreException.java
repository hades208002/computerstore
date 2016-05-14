package com.computerstore.exception;

public class ComputerstoreException extends Exception {
		private static final long serialVersionUID = -4369168754088871010L;

		public ComputerstoreException() {
			super();
		}

		public ComputerstoreException(String message, Throwable cause) {
			super(message, cause);
		}

		public ComputerstoreException(String message) {
			super(message);
		}

		public ComputerstoreException(Throwable cause) {
			super(cause);
		}

}

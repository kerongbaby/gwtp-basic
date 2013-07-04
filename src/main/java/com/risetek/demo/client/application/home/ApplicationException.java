package com.risetek.demo.client.application.home;

public class ApplicationException extends Exception {

 	private static final long serialVersionUID = 706483731185220912L;

	public ApplicationException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ApplicationException(String s) {
        super(s);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ApplicationException(String s, Throwable throwable) {
        super(s, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ApplicationException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
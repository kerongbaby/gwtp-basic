package com.risetek.demo.server.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;

@Singleton
public class Token extends HttpServlet {

	private static final long serialVersionUID = -6288646608696739714L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("token doGet");
		resp.getWriter().print("Hello!");
		resp.flushBuffer();
	}
}

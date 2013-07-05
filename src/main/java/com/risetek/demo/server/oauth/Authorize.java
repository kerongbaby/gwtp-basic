package com.risetek.demo.server.oauth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.amber.oauth2.as.issuer.MD5Generator;
import org.apache.amber.oauth2.as.issuer.OAuthIssuer;
import org.apache.amber.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.amber.oauth2.as.request.OAuthAuthzRequest;
import org.apache.amber.oauth2.as.request.OAuthTokenRequest;
import org.apache.amber.oauth2.as.response.OAuthASResponse;
import org.apache.amber.oauth2.common.exception.OAuthProblemException;
import org.apache.amber.oauth2.common.exception.OAuthSystemException;
import org.apache.amber.oauth2.common.message.OAuthResponse;

import com.google.inject.Singleton;

@Singleton
public class Authorize extends HttpServlet {

	private static final long serialVersionUID = -6288646608696739714L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {

		// Stage 1:
		PrintWriter pw = response.getWriter();
		pw.print("<html><form><button>YES</button></form></html>");
		pw.flush();
		pw.close();
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String redirectURI = "/";
			OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(
					new MD5Generator());
			System.out.println("Get oauthIssuerImpl");
			try {
				// dynamically recognize an OAuth profile based on request
				// characteristic (params,
				// method, content type etc.), perform validation
				OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(req);

				// some code ....

				redirectURI = oauthRequest.getRedirectURI();

				// build OAuth response
				OAuthResponse resp = OAuthASResponse
						.authorizationResponse(req,
								HttpServletResponse.SC_FOUND)
						.setCode(oauthIssuerImpl.authorizationCode())
						.location(redirectURI).buildQueryMessage();

				response.sendRedirect(resp.getLocationUri());

				// if something goes wrong
			} catch (OAuthProblemException ex) {
				final OAuthResponse resp = OAuthASResponse
						.errorResponse(HttpServletResponse.SC_FOUND).error(ex)
						.location(redirectURI).buildQueryMessage();

				response.sendRedirect(resp.getLocationUri());
			}
		} catch (OAuthSystemException ex) {
			// TODO:
		}
	}

}

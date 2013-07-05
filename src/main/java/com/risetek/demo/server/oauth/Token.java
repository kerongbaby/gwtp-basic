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
public class Token extends HttpServlet {

	private static final long serialVersionUID = 8903879613494154947L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		OAuthTokenRequest oauthRequest = null;

		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
		try {
			try {
				oauthRequest = new OAuthTokenRequest(request);

				String authzCode = oauthRequest.getCode();

				// some code

				String accessToken = oauthIssuerImpl.accessToken();
				String refreshToken = oauthIssuerImpl.refreshToken();

				// some code

				OAuthResponse r = OAuthASResponse
						.tokenResponse(HttpServletResponse.SC_OK)
						.setAccessToken(accessToken).setExpiresIn("3600")
						.setRefreshToken(refreshToken).buildJSONMessage();

				response.setStatus(r.getResponseStatus());
				PrintWriter pw = response.getWriter();
				pw.print(r.getBody());
				pw.flush();
				pw.close();

				// if something goes wrong
			} catch (OAuthProblemException ex) {

				OAuthResponse r = OAuthResponse.errorResponse(401).error(ex)
						.buildJSONMessage();

				response.setStatus(r.getResponseStatus());

				PrintWriter pw = response.getWriter();
				pw.print(r.getBody());
				pw.flush();
				pw.close();

				response.sendError(401);
			}
		} catch (OAuthSystemException sex) {
		}
	}
}

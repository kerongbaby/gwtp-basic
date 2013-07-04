/**
 * Copyright 2012 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.risetek.demo.client.application.home;

import com.risetek.demo.client.application.ApplicationPresenter;
import com.risetek.demo.client.oauth.Auth;
import com.risetek.demo.client.oauth.AuthRequest;
import com.risetek.demo.client.place.NameTokens;
import com.google.gwt.core.client.Callback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class HomePagePresenter extends Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy>
	implements HomeUiHandlers {
    public interface MyView extends View, HasUiHandlers<HomeUiHandlers> {
    	public void showAuthorizeRequest(String message);
    }

    @ProxyStandard
    @NameToken(NameTokens.home)
    public interface MyProxy extends ProxyPlace<HomePagePresenter> {
    }

    @Inject
    public HomePagePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
        view.setUiHandlers(this);
    }

	private static final Auth AUTH = Auth.get();

    private static final String GOOGLE_AUTH_URL = "/oauth/authorize";

    // This app's personal client ID assigned by the Google APIs Console
    // (http://code.google.com/apis/console).
    private static final String GOOGLE_CLIENT_ID = "452237527106.apps.googleusercontent.com";

    // The auth scope being requested. This scope will allow the application to
    // identify who the authenticated user is.
    private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/plus.me";

	@Override
	public void Authorize() {
        final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID)
        .withScopes(PLUS_ME_SCOPE);

        getView().showAuthorizeRequest(AUTH.toUrl(req));
        
    // Calling login() will display a popup to the user the first time it is
    // called. Once the user has granted access to the application,
    // subsequent calls to login() will not display the popup, and will
    // immediately result in the callback being given the token to use.
    AUTH.login(req, new Callback<String, Throwable>() {
      @Override
      public void onSuccess(String token) {
    	  getView().showAuthorizeRequest("Got an OAuth token:\n" + token + "\n"
            + "Token expires in " + AUTH.expiresIn(req) + " ms\n");
      }

      @Override
      public void onFailure(Throwable caught) {
    	  getView().showAuthorizeRequest("Error:\n" + caught.getMessage());
      }
    });

  }

}

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

package com.risetek.demo.server.guice;

import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.shared.ActionImpl;
import com.risetek.demo.server.oauth.Authorize;
import com.risetek.demo.server.oauth.Token;

public class DispatchServletModule extends ServletModule {
    @Override
    public void configureServlets() {
    	System.out.println("configureServlets");
    	serve("/oauth/authorize").with(Authorize.class);
    	serve("/oauth/access_token").with(Token.class);
    	serve("/oauth/get_token_info").with(Token.class);
    	serve("/oauth/revoke").with(Token.class);
        serve("/" + ActionImpl.DEFAULT_SERVICE_NAME + "*").with(DispatchServiceImpl.class);
    }
}

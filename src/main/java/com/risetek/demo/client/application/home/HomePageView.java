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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class HomePageView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePagePresenter.MyView {
    public interface Binder extends UiBinder<Widget, HomePageView> {}

    final private SimplePanel simplePanel = new SimplePanel();
    final private Button authorizeButton = new Button("Authorize");
    final private Button tokenButton = new Button("Token");
    final private Button accessButton = new Button("Access");
    final private Grid processGrid = new Grid(3,4);
    @Inject
    public HomePageView() {
    	
    	
    	authorizeButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				getUiHandlers().Authorize();
			}});
    	
    	
    	simplePanel.setSize("100%", "500px");
    	simplePanel.getElement().getStyle().setBackgroundColor("#F0F0F0");
    	simplePanel.setWidget(processGrid);
    	processGrid.setSize("100%", "100%");
    	processGrid.setBorderWidth(1);
    	processGrid.setWidget(0, 0, authorizeButton);
    	processGrid.setWidget(1, 0, tokenButton);
    	processGrid.setWidget(2, 0, accessButton);
    	processGrid.setText(0, 1, " ");
    	processGrid.setText(1, 1, " ");
    	processGrid.setText(2, 1, " ");
        initWidget(simplePanel);
//        initWidget(uiBinder.createAndBindUi(this));
    }
	@Override
	public void showAuthorizeRequest(String message) {
    	processGrid.setText(0, 1, message);
	}
}

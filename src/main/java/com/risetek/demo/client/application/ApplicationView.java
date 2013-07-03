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

package com.risetek.demo.client.application;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.gwtplatform.mvp.client.ViewImpl;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    final private DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Unit.PX);
    final private HorizontalPanel horizontalPanel = new HorizontalPanel();
    final private HTMLPanel hello = new HTMLPanel("Hello OpenAPI");
    @Inject
    public ApplicationView() {
        dockLayoutPanel.setSize("100%", "600px");
    	dockLayoutPanel.addNorth(horizontalPanel, 40);
    	horizontalPanel.setSize("100%", "100%");
    	horizontalPanel.getElement().getStyle().setBackgroundColor("#1f1f1f");
    	hello.getElement().getStyle().setColor("#F0F0F0");
    	horizontalPanel.add(hello);
        initWidget(dockLayoutPanel);
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == ApplicationPresenter.SLOT_SetMainContent) {
        	dockLayoutPanel.add(content);
        } else {
            super.setInSlot(slot, content);
        }
    }
}

package com.devol.client.model;

import com.devol.client.resource.MyResource;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;

public class HeaderPanelM extends HeaderMenu {

	public HeaderPanelM() {
		style();
	}

	private void style() {
		MyResource.INSTANCE.getStlModel().ensureInjected();
		addStyleName(MyResource.INSTANCE.getStlModel().mainHeaderPanelM());
	}
}

package com.devol.client.model;

import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.devol.client.resource.MyResource;

public class UIMenuHeader extends HeaderPanel {

	public UIMenuHeader() {
		init();
		style();
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setCenter("Sys Pagos");
	}

	private void style() {
		MyResource.INSTANCE.getStlUIMenu().ensureInjected();
		// TODO Auto-generated method stub
		this.addStyleName(MyResource.INSTANCE.getStlUIMenu().headerTittleUI());
	}
}

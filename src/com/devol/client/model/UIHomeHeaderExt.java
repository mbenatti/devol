package com.devol.client.model;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.widget.HeaderPanel;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarButtonBase;
import com.devol.client.resource.MyResource;
import com.devol.client.view.uihome.UIHome;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.client.view.uimenu.UIMenuImpl;
import com.devol.i18n.DevolConstants;

public class UIHomeHeaderExt extends HeaderMenu implements TouchEndHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private ButtonBarButtonBase btnPais;
	private ButtonBarButtonBase btnAbout;
	private Label lblTitulo;
	

	public UIHomeHeaderExt() {
		init();
		style();
	}

	private void init() {
		// TODO Auto-generated method stub
		lblTitulo=new Label("DEVOL"); 
		this.setCenterWidget(lblTitulo);
		btnPais = new ButtonBarButtonBase(MyResource.INSTANCE.getImgMundo32());		
		btnPais.setTitle(constants.seleccioneIdioma());
		btnPais.addTouchEndHandler(this);		
		this.setLeftWidget(btnPais);
		btnAbout = new ButtonBarButtonBase(MyResource.INSTANCE.getImgAbout32());		
		btnAbout.setTitle(constants.acercaDevol());
		btnAbout.addTouchEndHandler(this);		
		this.setRightWidget(btnAbout);
	}

	private void style() {
		MyResource.INSTANCE.getStlUIHome().ensureInjected();
		// TODO Auto-generated method stub
		this.addStyleName(MyResource.INSTANCE.getStlUIHome().headerPrincipal());
	}

	public void setVisibleBtnPais(boolean valor) {
		this.btnPais.setVisible(valor);
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		goToUIPais();
	}

	public void goToUIPais() {
		// TODO Auto-generated method stub
		UILanguage uiLanguage=new UILanguage();
		uiLanguage.show();
	}

	public Label getLblTitulo() {
		return lblTitulo;
	}
	
	

}

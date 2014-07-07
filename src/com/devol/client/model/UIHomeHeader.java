package com.devol.client.model;

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

public class UIHomeHeader extends HeaderMenu implements TouchEndHandler {

	private ButtonBarButtonBase btnMenu;	
	private ButtonBarButtonBase btnAbout;
	private Label lblTitulo;
	

	public UIHomeHeader() {
		init();
		style();
	}

	private void init() {
		// TODO Auto-generated method stub
		lblTitulo=new Label("DEVOL"); 
		this.setCenterWidget(lblTitulo);
		btnMenu = new ButtonBarButtonBase(MyResource.INSTANCE.getImgMenu32());		
		btnMenu.setTitle("Menu Principal");
		btnMenu.addTouchEndHandler(this);		
		this.setLeftWidget(btnMenu);
		btnAbout = new ButtonBarButtonBase(MyResource.INSTANCE.getImgAbout32());		
		btnAbout.setTitle("Acerca de los Desarrolladores");
		btnAbout.addTouchEndHandler(this);		
		this.setRightWidget(btnAbout);
	}

	private void style() {
		MyResource.INSTANCE.getStlUIHome().ensureInjected();
		// TODO Auto-generated method stub
		this.addStyleName(MyResource.INSTANCE.getStlUIHome().headerPrincipal());
	}

	public void setVisibleBtnMenu(boolean valor) {
		this.btnMenu.setVisible(valor);
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		goToUIMenu();
	}

	public void goToUIMenu() {
		// TODO Auto-generated method stub
		UIHomeSesion.animationHelper.goTo(new UIMenuImpl(), Animation.SLIDE_REVERSE);		
	}

	public Label getLblTitulo() {
		return lblTitulo;
	}
	
	

}

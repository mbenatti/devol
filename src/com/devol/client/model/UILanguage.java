package com.devol.client.model;

import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.ui.client.widget.Button;

public class UILanguage extends DialogBox implements TouchEndHandler{
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private Button btnIngles;
	private Button btnEspanol;
	private VerticalPanel panel;
	
	public UILanguage(){
		super(false,true);
		initComponents();
		style();
		widgetListener();
	}
	
	private void initComponents(){
		panel=new VerticalPanel();
		btnIngles=new Button(constants.ingles());
		btnEspanol=new Button(constants.espanol());
		panel.add(btnIngles);
		panel.add(btnEspanol);
		this.setAnimationEnabled(true);
		this.setText(constants.seleccioneIdioma());
		this.setWidget(panel);	
		this.center();
	}
	
	private void style(){
		panel.getElement().getStyle().setWidth(100, Unit.PCT);
		//btnIngles.getElement().getStyle().setWidth(100, Unit.PCT);
		//btnEspanol.getElement().getStyle().setWidth(100, Unit.PCT);
	}
	
	private void widgetListener(){
		btnIngles.addTouchEndHandler(this);
		btnEspanol.addTouchEndHandler(this);
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(btnIngles)){
			Window.Location.assign("https://devolminguillo.appspot.com/?locale=en");
		}else if(event.getSource().equals(btnEspanol)){
			Window.Location.assign("https://devolminguillo.appspot.com");
		}
	}
}

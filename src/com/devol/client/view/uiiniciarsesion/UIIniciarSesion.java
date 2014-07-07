package com.devol.client.view.uiiniciarsesion;

import com.devol.client.model.UIHomeHeader;
import com.devol.client.model.UIHomeHeaderExt;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;
import com.devol.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MPasswordTextBox;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

public class UIIniciarSesion extends Composite implements InterUIIniciarSesion,
TouchEndHandler, ResizeHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private LayoutPanel main;
	private UIHomeHeaderExt header;
	protected ScrollPanel scrollPanel;
	private FlowPanel content;
	private WidgetList widgetList;
	protected MTextBox txtCorreo;
	protected MPasswordTextBox txtClave;
	protected Button btnEntrar;
	private Button btnCrearCuenta;
	//private FlowPanel container;

	public UIIniciarSesion(){
		init();
		initWidgetListener();
		style();
	}

	private void init() {
		main = new LayoutPanel();
		initWidget(main);
		Window.addResizeHandler(this);
		
		header = new UIHomeHeaderExt();
		//header.setVisibleBtnMenu(false);
		main.add(header);
		
		//container = new FlowPanel();
		//main.add(container);
		
		scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setAutoHandleResize(true);
		scrollPanel.setShowScrollBarX(false);
		scrollPanel.setShowScrollBarY(true);
		//scrollPanel.setScrollingEnabledX(false);
		//scrollPanel.setAutoHandleResize(true);
		//scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());		
		
		content = new FlowPanel();
		
		
		widgetList = new WidgetList();
		widgetList.setRound(true);
		content.add(widgetList);
		
		txtCorreo = new MTextBox();
		txtCorreo.setPlaceHolder(constants.correo());
		widgetList.add(txtCorreo);
		
		txtClave = new MPasswordTextBox();
		txtClave.setPlaceHolder(constants.clave());
		widgetList.add(txtClave);
		
		btnEntrar = new Button(constants.entrar());
		btnEntrar.setConfirm(true);		
		content.add(btnEntrar);
		
		btnCrearCuenta = new Button(constants.crearCuenta());
		content.add(btnCrearCuenta);
		scrollPanel.setWidget(content);
		main.add(scrollPanel);
	}

	private void initWidgetListener() {
		btnEntrar.addTouchEndHandler(this);
		btnCrearCuenta.addTouchEndHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlFormulario().ensureInjected();
		//main.setWidth("100%");
		//container.setWidth("100%");
		setHeightContainer(15);
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("100%");
		//widgetList.setWidth("100");
		widgetList.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.widgetList());
		txtCorreo.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		txtClave.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		
	}
	
	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		main.setHeight((height - heightHeader) + "px");
		this.scrollPanel.refresh();
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void irCuenta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnEntrar)) {
			if(isValidData())
			login();
		}else if (event.getSource().equals(btnCrearCuenta)) {
			irCrearCuenta();
		}
	}

	@Override
	public void irCrearCuenta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		setHeightContainer(15);
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if(FieldVerifier.isEmpty(txtCorreo.getText())){
			Dialogs.alert(constants.alerta(), constants.usuarioClaveIncorrecto(), null);
			return false;
		}else if(FieldVerifier.isEmpty(txtClave.getText())){
			Dialogs.alert(constants.alerta(), constants.usuarioClaveIncorrecto(), null);
			return false;
		}else if(!FieldVerifier.isEmail(txtCorreo.getText())){
			Dialogs.alert(constants.alerta(), constants.digiteCorreo(), null);
			return false;
		}
		return true;
	}
}

package com.devol.client.view.uiregistrarusuario;

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

public class UIRegistrarUsuario extends Composite implements
		InterUIRegistrarUsuario, TouchEndHandler, ResizeHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private LayoutPanel main;
	//private FlowPanel container;
	protected ScrollPanel scrollPanel;
	protected MTextBox txtNombre;
	private FlowPanel pnlContenido;
	private WidgetList widgetList;
	protected MTextBox txtApellido;
	protected MTextBox txtCorreo;
	protected MTextBox txtClave;
	private Button btnRegistrar;
	private Button btnIniciarSesion;
	private UIHomeHeaderExt header;

	public UIRegistrarUsuario() {
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
		/*scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setAutoHandleResize(true);*/
		//scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());
		

		pnlContenido = new FlowPanel();
		

		widgetList = new WidgetList();
		widgetList.setRound(true);
		pnlContenido.add(widgetList);

		txtNombre = new MTextBox();
		txtNombre.setPlaceHolder(constants.nombres()+" (*)");
		widgetList.add(txtNombre);

		txtApellido = new MTextBox();
		txtApellido.setPlaceHolder(constants.apellidos()+" (*)");
		widgetList.add(txtApellido);

		txtCorreo = new MTextBox();
		txtCorreo.setPlaceHolder(constants.correo()+" (*)");
		widgetList.add(txtCorreo);

		txtClave = new MPasswordTextBox();
		txtClave.setPlaceHolder(constants.clave()+" (*)");
		widgetList.add(txtClave);

		btnRegistrar = new Button(constants.registrar());
		btnRegistrar.setConfirm(true);
		pnlContenido.add(btnRegistrar);
		
		btnIniciarSesion = new Button(constants.entrar());
		pnlContenido.add(btnIniciarSesion);
		scrollPanel.setWidget(pnlContenido);
		main.add(scrollPanel);

	}

	protected void cleanForm() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtCorreo.setText("");
		txtClave.setText("");
	}

	private void initWidgetListener() {
		this.btnRegistrar.addTouchEndHandler(this);
		this.btnIniciarSesion.addTouchEndHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlFormulario().ensureInjected();
		setHeightContainer(15);
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("100%");
		
		widgetList.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.widgetList());
		txtCorreo.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		txtNombre.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		txtApellido.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());
		txtClave.addStyleName(MyResource.INSTANCE.getStlFormulario()
				.textBox());

	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnRegistrar)) {
			if(isValidData())
			registrar();
		}else if (event.getSource().equals(btnIniciarSesion)) {
			irIniciarSesion();
		}
	}
	
	

	@Override
	public void registrar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void irIniciarSesion() {
		// TODO Auto-generated method stub
		
	}
	
	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		main.setHeight((height - heightHeader) + "px");
	}

	@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		setHeightContainer(15);
		this.scrollPanel.refresh();
	}

	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if(FieldVerifier.isEmpty(txtNombre.getText())){
			Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			return false;
		}else if(FieldVerifier.isEmpty(txtApellido.getText())){
			Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			return false;
		}else if(FieldVerifier.isEmpty(txtCorreo.getText())){
			Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			return false;
		}else if(FieldVerifier.isEmpty(txtClave.getText())){
			Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			return false;
		}else if(!FieldVerifier.isEmail(txtCorreo.getText())){
			Dialogs.alert(constants.alerta(), constants.digiteCorreo(), null);
			return false;
		}
		return true;
	}
}

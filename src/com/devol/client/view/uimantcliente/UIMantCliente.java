package com.devol.client.view.uimantcliente;

import com.devol.client.beanproxy.ClienteProxy;
import com.devol.client.model.ContentForm;
import com.devol.client.model.HeaderPanelM;
import com.devol.client.model.NumberTextBox;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;
import com.devol.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarButtonBase;

public class UIMantCliente extends Composite implements InterUIMantCliente,
		TouchEndHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private LayoutPanel main;
	private HeaderPanelM header;
	private Label lblCenter;
	private ButtonBarButtonBase btnBack;
	protected ScrollPanel scrollPanel;
	private FlowPanel contenido;
	private Button btnGuardar;
	protected NumberTextBox txtDni;
	protected MTextBox txtNombre;
	protected MTextBox txtApellido;
	protected NumberTextBox txtTelefono;
	protected MTextBox txtDireccion;
	private ContentForm contentForm;
	private FlowPanel pnlForm;
	//private FlowPanel container;
	protected String modo;
	protected ClienteProxy beanCliente;

	public UIMantCliente() {
		init();
		initWidgetListener();
		style();
		reCalcularWindows();
	}

	public void setModo(String modo) {
		this.modo = modo;
		lblCenter.setText(modo);
	}

	public void setBean(ClienteProxy bean) {
		this.beanCliente = bean;
		llenarCampos();
	}

	private void llenarCampos() {
		txtDni.setText(beanCliente.getDni());
		txtNombre.setText(beanCliente.getNombre());
		txtApellido.setText(beanCliente.getApellido());
		txtTelefono.setText(beanCliente.getTelefono());
		txtDireccion.setText(beanCliente.getDireccion());
	}

	/*public void desactivarCampos() {
		txtDni.setReadOnly(true);
		txtNombre.setReadOnly(true);
		txtApellido.setReadOnly(true);
		txtTelefono.setReadOnly(true);
		txtDireccion.setReadOnly(true);
	}*/

	public void activarCampos() {
		if(modo.equalsIgnoreCase(constants.modoEliminar())){
			txtDni.setReadOnly(true);
			txtNombre.setReadOnly(true);
			txtApellido.setReadOnly(true);
			txtTelefono.setReadOnly(true);
			txtDireccion.setReadOnly(true);
		}else if(modo.equalsIgnoreCase(constants.modoEditar())){
			txtDni.setReadOnly(true);
			txtNombre.setReadOnly(false);
			txtApellido.setReadOnly(false);
			txtTelefono.setReadOnly(false);
			txtDireccion.setReadOnly(false);
		}else{
			txtDni.setReadOnly(false);
			txtNombre.setReadOnly(false);
			txtApellido.setReadOnly(false);
			txtTelefono.setReadOnly(false);
			txtDireccion.setReadOnly(false);
		}
		
	}

	private void init() {
		main = new LayoutPanel();
		initWidget(main);
		//Window.addResizeHandler(this);

		header = new HeaderPanelM();
		lblCenter = new Label(constants.modoNuevo());
		header.setCenterWidget(lblCenter);
		main.add(header);

		btnBack = new ButtonBarButtonBase(MyResource.INSTANCE.getImgBack32());
		header.setLeftWidget(btnBack);

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
		//container.add(scrollPanel);

		contenido = new FlowPanel();		

		pnlForm = new FlowPanel();
		contenido.add(pnlForm);

		contentForm = new ContentForm();
		pnlForm.add(contentForm);

		txtDni = new NumberTextBox();
		contentForm.addWidget("* "+constants.dni(), txtDni);

		txtNombre = new MTextBox();
		contentForm.addWidget("* "+constants.nombres(), txtNombre);

		txtApellido = new MTextBox();
		contentForm.addWidget("* "+constants.apellidos(), txtApellido);

		txtTelefono = new NumberTextBox();
		contentForm.addWidget(constants.telefono(), txtTelefono);

		txtDireccion = new MTextBox();
		contentForm.addWidget(constants.direccion(), txtDireccion);

		btnGuardar = new Button(constants.guardar());
		btnGuardar.setConfirm(true);
		contenido.add(btnGuardar);
		scrollPanel.setWidget(contenido);
		main.add(scrollPanel);
		Window.addResizeHandler(new ResizeHandler(){

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				reCalcularWindows();
			}});

	}
	
	private void reCalcularWindows(){
		//setWidthGrid();
		setHeightContainer(55);		
		setWidthPnlFlexTable();
	}

	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		main.setHeight((height - heightHeader) + "px");
	}

	private void initWidgetListener() {
		btnGuardar.addTouchEndHandler(this);
		btnBack.addTouchEndHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlUIMantAmortizar().ensureInjected();
		//main.setWidth("100%");
		//container.setWidth("100%");
		//setHeightContainer(41);
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("100%");
		pnlForm.setWidth("100%");
		contenido.setWidth("100%");
		//pnlForm.getElement().getStyle().setOverflow(Overflow.HIDDEN);
	}

	/*@Override
	public void onResize(ResizeEvent event) {
		// TODO Auto-generated method stub
		
		//setHeightContainer(41);
	}*/

	private void setWidthPnlFlexTable() {
		int width = Window.getClientWidth();
		width = width - 20;
		// pnlForm.setWidth(width + "px");
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnGuardar)) {			
			registrar();
		} else if (event.getSource().equals(btnBack)) {
			goToUICliente();
		}
	}

	/*
	 * private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	 * private final EventBus EVENTBUS = new SimpleEventBus();
	 */

	@Override
	public void goToUICliente() {
		// TODO Auto-generated method stub
	}

	@Override
	public void registrar() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean isValidData() {
		// TODO Auto-generated method stub
		if(!modo.equalsIgnoreCase(constants.modoEliminar())){
		if(FieldVerifier.isEmpty(txtDni.getText())){
			Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			return false;
		}else if(!FieldVerifier.isValidDNI(txtDni.getText())){
			Dialogs.alert(constants.alerta(), constants.dniInvalida(), null);
			return false;
		}else if(FieldVerifier.isEmpty(txtNombre.getText())){
			Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			return false;
		}else if(FieldVerifier.isEmpty(txtApellido.getText())){
			Dialogs.alert(constants.alerta(), constants.camposObligatorios(), null);
			return false;
		}	
		}else{
		int numPrestamo=beanCliente.getNumPrestamo();
		if(numPrestamo>0){
			Dialogs.alert(constants.alerta(), constants.clienteConPrestamos(), null);
			return false;
		}
		}
		return true;		
	}
	
}

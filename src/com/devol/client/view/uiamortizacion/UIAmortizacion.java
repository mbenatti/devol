package com.devol.client.view.uiamortizacion;

import java.math.BigDecimal;

import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.grid.GridAmortizacion;
import com.devol.client.model.HeaderGrid;
import com.devol.client.model.HeaderPanelM;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarButtonBase;

public class UIAmortizacion extends Composite implements InterUIAmortizacion,
		TouchEndHandler{
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private LayoutPanel main;
	private HeaderPanelM header;
	protected HeaderGrid headerGrid;
	protected Label headerGridFecha;
	protected Label headerGridMonto;
	protected FlowPanel container;
	protected ScrollPanel scrollPanel;
	protected ButtonBarButtonBase btnBack;
	protected HorizontalPanel pnlRight;
	protected ButtonBarButtonBase btnNuevo;
	protected ButtonBarButtonBase btnEliminar;
	private FlowPanel contenido;
	private HorizontalPanel pnlEstadoPrestamo;
	protected Label lblADevolver;
	protected Label lblADevuelto;
	protected int heightHeader = 55;
	protected GridAmortizacion grid;
	protected PrestamoProxy beanPrestamo;
	protected Label lblCliente;

	public UIAmortizacion() {
		init();
		initWidgetListener();
		style();
		reCalcularWindows();
	}

	private void init() {		
		main = new LayoutPanel();
		initWidget(main);
		//Window.addResizeHandler(this);

		header = new HeaderPanelM();
		main.add(header);
		lblCliente=new Label("Cliente");
		header.setCenterWidget(lblCliente);

		btnBack = new ButtonBarButtonBase(MyResource.INSTANCE.getImgBack32());
		header.setLeftWidget(btnBack);

		pnlRight = new HorizontalPanel();		
		header.setRightWidget(pnlRight);
		
		//pnlRight.add();

		btnNuevo = new ButtonBarButtonBase(MyResource.INSTANCE.getImgNew32());
		pnlRight.add(btnNuevo);

		btnEliminar = new ButtonBarButtonBase(
				MyResource.INSTANCE.getImgRemove32());
		pnlRight.add(btnEliminar);

		headerGrid = new HeaderGrid();
		main.add(headerGrid);

		headerGridFecha = new Label(constants.fecha());
		headerGrid.add(headerGridFecha);

		headerGridMonto = new Label(constants.monto());
		headerGrid.add(headerGridMonto);

		container = new FlowPanel();
		main.add(container);

		scrollPanel = new ScrollPanel();
		/*scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setAutoHandleResize(true);*/
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setAutoHandleResize(true);
		scrollPanel.setShowScrollBarX(false);
		scrollPanel.setShowScrollBarY(true);
		// scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());
		//container.add(scrollPanel);
		

		contenido = new FlowPanel();
		scrollPanel.setWidget(contenido);

		grid = new GridAmortizacion();
		container.add(grid);
		scrollPanel.setWidget(container);
		main.add(scrollPanel);
		pnlEstadoPrestamo = new HorizontalPanel();
		main.add(pnlEstadoPrestamo);

		lblADevolver = new Label();
		pnlEstadoPrestamo.add(lblADevolver);

		lblADevuelto = new Label();
		pnlEstadoPrestamo.add(lblADevuelto);
		
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				reCalcularWindows();
			}
		});

	}
	
	private void reCalcularWindows() {		
		setHeightContainer();
	}
	
	public void setBean(PrestamoProxy beanPrestamo) {		
		this.beanPrestamo=beanPrestamo;
		llenarCampos();
	}
	
	public void llenarCampos() {
		BigDecimal vAdevolver=BigDecimal.valueOf(beanPrestamo.getaDevolver());
		BigDecimal vDevuelto=BigDecimal.valueOf(beanPrestamo.getDevuelto());
		vAdevolver=vAdevolver.subtract(vDevuelto);
		lblADevolver.setText(vAdevolver.toString());
		lblADevuelto.setText(vDevuelto.toString());
		lblCliente.setText(beanPrestamo.getNombre()+" "+beanPrestamo.getApellido());
	}

	private void initWidgetListener() {
		btnBack.addTouchEndHandler(this);
		btnNuevo.addTouchEndHandler(this);
		btnEliminar.addTouchEndHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlUIAmortizacion().ensureInjected();

		main.setWidth("100%");
		headerGridFecha.setWidth("60%");
		headerGridMonto.setWidth("40%");
		setHeightContainer();
		container.setWidth("100%");		
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("100%");
		contenido.setWidth("100%");
		pnlEstadoPrestamo.setWidth("100%");
		pnlEstadoPrestamo.setHeight("40px");
		headerGridFecha.getElement().getStyle().setFloat(Style.Float.LEFT);
		headerGridMonto.getElement().getStyle().setFloat(Style.Float.LEFT);

		pnlEstadoPrestamo.addStyleName(MyResource.INSTANCE
				.getStlUIAmortizacion().pnlEstadoPrestamoUIAmortizacion());
		lblADevolver.addStyleName(MyResource.INSTANCE.getStlUIAmortizacion()
				.lblADevolverUIAmortizacion());
		lblADevuelto.addStyleName(MyResource.INSTANCE.getStlUIAmortizacion()
				.lblADevueltoUIAmortizacion());
		grid.addStyleName(MyResource.INSTANCE.getStlUIAmortizacion()
				.gridUIAmortizacion());
	}

	protected void setHeightContainer() {
		int height = Window.getClientHeight();
		//container.setHeight((height - 164) + "px");
		main.setHeight((height - heightHeader) + "px");
	}
	

	@Override
	public void goToUIPrestamo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource().equals(btnBack)) {
			goToUIPrestamo();
		}else if (event.getSource().equals(btnNuevo)) {
			// Window.alert("ok");
			goToUIMantAmortizacion(constants.modoNuevo());
		}else if (event.getSource().equals(btnEliminar)) {
			goToUIMantAmortizacion(constants.modoEliminar());
		}
	}

	@Override
	public void goToUIMantAmortizacion(String modo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cargarTabla() {
		// TODO Auto-generated method stub
		
	}

	public Label getLblADevolver() {
		return lblADevolver;
	}

	public Label getLblADevuelto() {
		return lblADevuelto;
	}

	@Override
	public void activarModoPrestamo() {
		// TODO Auto-generated method stub
		
	}

	
	
}

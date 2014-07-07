package com.devol.client.view.uiprestamo;

import com.devol.client.beanproxy.PrestamoProxy;
import com.devol.client.grid.GridPrestamo;
import com.devol.client.model.HeaderGrid;
import com.devol.client.model.ToolBar;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MSearchBox;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarButtonBase;

public class UIPrestamo extends Composite implements InterUIPrestamo,
		ValueChangeHandler, KeyUpHandler, TouchEndHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private LayoutPanel main;
	private MSearchBox txtBuscar;
	protected ToolBar toolBar;
	protected ScrollPanel scrollPanel;
	protected GridPrestamo grid;
	private HeaderGrid headerGrid;
	private Label headerGridFecha;
	private Label headerGridCliente;
	private Label headerGridMonto;
	protected FlowPanel container;
	private ButtonBarButtonBase btnAmortizacion;

	public UIPrestamo() {
		init();
		initWidgetListener();
		style();
		reCalcularWindows();
	}

	private void init() {
		main = new LayoutPanel();
		// Window.addResizeHandler(this);

		toolBar = new ToolBar();
		main.add(toolBar);
		
		btnAmortizacion = new ButtonBarButtonBase(
				MyResource.INSTANCE.getImgCash32());
		btnAmortizacion.setTitle(constants.amortizar());
		toolBar.add(btnAmortizacion);

		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder(constants.buscarPrestamo());
		main.add(txtBuscar);

		headerGrid = new HeaderGrid();
		main.add(headerGrid);

		headerGridFecha = new Label(constants.fecha());
		headerGrid.add(headerGridFecha);

		headerGridCliente = new Label(constants.clientes());
		headerGrid.add(headerGridCliente);

		headerGridMonto = new Label(constants.monto());
		headerGrid.add(headerGridMonto);

		container = new FlowPanel();

		scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setAutoHandleResize(true);
		scrollPanel.setShowScrollBarX(false);
		scrollPanel.setShowScrollBarY(true);
		/*
		 * scrollPanel.setScrollingEnabledX(false);
		 * scrollPanel.setScrollingEnabledY(true);
		 * scrollPanel.setAutoHandleResize(true);
		 */
		// scrollPanel.setUsePos(MGWT.getOsDetection().isAndroid());
		// container.add(scrollPanel);

		grid = new GridPrestamo();
		container.add(grid);
		scrollPanel.setWidget(container);
		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				reCalcularWindows();
			}
		});
		// cargarTabla();
		main.add(scrollPanel);
		initWidget(main);
	}

	private void reCalcularWindows() {
		setWidthGrid();
		setHeightContainer(55);
	}

	@SuppressWarnings("unchecked")
	private void initWidgetListener() {
		txtBuscar.addValueChangeHandler(this);
		txtBuscar.addKeyUpHandler(this);

		toolBar.getBtnNuevo().addTouchEndHandler(this);
		toolBar.getBtnEditar().addTouchEndHandler(this);
		toolBar.getBtnEliminar().addTouchEndHandler(this);
		toolBar.getBtnActualizar().addTouchEndHandler(this);
		btnAmortizacion.addTouchEndHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlUIPrestamo().ensureInjected();
		
		btnAmortizacion.getElement().getStyle().setFloat(Style.Float.RIGHT);
		btnAmortizacion.getElement().getStyle().setRight(4, Unit.PX);

		// main.setWidth("100%");
		headerGridMonto.setWidth("24%");
		headerGridFecha.setWidth("30%");
		headerGridCliente.setWidth("46%");
		/*
		 * setHeightContainer(127); setWidthGrid(); container.setWidth("100%");
		 * scrollPanel.setWidth("100%"); scrollPanel.setHeight("100%");
		 */

		txtBuscar.addStyleName(MyResource.INSTANCE.getStlUIPrestamo()
				.txtBuscarUIPrestamo());
		grid.addStyleName(MyResource.INSTANCE.getStlUIPrestamo()
				.gridUIPrestamo());

		headerGridFecha.getElement().getStyle().setFloat(Style.Float.LEFT);
		headerGridCliente.getElement().getStyle().setFloat(Style.Float.LEFT);
		headerGridMonto.getElement().getStyle().setFloat(Style.Float.LEFT);
	}

	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		main.setHeight((height - heightHeader) + "px");
	}

	@Override
	public void cargarTabla() {
		/*
		 * List<Prestamo> lista = new ArrayList<Prestamo>(); for (int i = 0; i <
		 * 10; i++) { Prestamo bean = new Prestamo(); bean.setIdPrestamo("" + (i
		 * + 1)); bean.setFecha(new Date());
		 * 
		 * Cliente clie = new Cliente(); clie.setNombre("Cliente");
		 * clie.setApellido("" + i); bean.setBeanCliente(clie); lista.add(bean);
		 * }
		 * 
		 * grid.setData(lista); grid.getSelectionModel().clear();
		 * scrollPanel.refresh();+
		 */
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		filtrar();
	}

	@Override
	public void onValueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		filtrar();
	}

	private void filtrar() {
		grid.getDataProvider().setFilter(txtBuscar.getText());
		if (grid.getDataProvider().hasFilter()) {
			int alto = grid.getDataProvider().resulted.size() * 15;
			// grid.setHeight(alto + "mm");
		} else {
			int alto = grid.getData().size() * 15;
			// grid.setHeight(alto + "mm");
		}
		grid.getDataProvider().refresh();
	}

	/*
	 * @Override public void onResize(ResizeEvent event) { // TODO
	 * Auto-generated method stub setWidthGrid(); setHeightContainer(127); }
	 */

	private void setWidthGrid() {
		int width = Window.getClientWidth();
		width = width - 20;
		// grid.setWidth(width + "px");
	}

	@Override
	public void onTouchEnd(TouchEndEvent event) {
		// TODO Auto-generated method stub
		/*
		 * Prestamo bean = grid.getSelectionModel().getSelectedObject(); if
		 * (bean == null) return;
		 * 
		 * if (event.getSource().equals(toolBar.getBtnNuevo())) {
		 * Window.alert("" + bean.getIdPrestamo()); }
		 */
		if (event.getSource().equals(toolBar.getBtnNuevo())) {
			// Window.alert("ok");
			goToUIMantPrestamo(constants.modoNuevo());
		} else if (event.getSource().equals(toolBar.getBtnEditar())) {
			goToUIMantPrestamo(constants.modoEditar());
		} else if (event.getSource().equals(toolBar.getBtnEliminar())) {
			goToUIMantPrestamo(constants.modoEliminar());
		} else if (event.getSource().equals(toolBar.getBtnActualizar())) {
			cargarTabla();
		}else if (event.getSource().equals(this.btnAmortizacion)) {
			PrestamoProxy bean = grid.getSelectionModel().getSelectedObject();
			if (bean == null){
				Dialogs.alert(constants.alerta(), constants.seleccionPrestamo(), null);
				return;
			}
			goToUIAmortizacion();
		}
	}

	@Override
	public void goToUIMantPrestamo(String modo) {

	}
	
	@Override
	public void goToUIAmortizacion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void activarModoPrestamo() {
		// TODO Auto-generated method stub
		
	}

}

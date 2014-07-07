package com.devol.client.view.uirecaudado;

import java.util.Date;

import com.devol.client.grid.GridRecaudado;
import com.devol.client.model.HeaderGrid;
import com.devol.client.model.TextBoxCalendar;
import com.devol.client.model.ToolBar;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.client.GWT;
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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndHandler;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.MSearchBox;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarButtonBase;

public class UIRecaudado extends Composite implements InterUIRecaudado,ValueChangeHandler, KeyUpHandler, TouchEndHandler {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private LayoutPanel main;
	private MSearchBox txtBuscar;
	protected ToolBar toolBar;
	protected ScrollPanel scrollPanel;
	protected GridRecaudado grid;
	private HeaderGrid headerGrid;
	private Label headerGridFecha;
	private Label headerGridCliente;
	private Label headerGridMonto;
	protected FlowPanel container;
	private ButtonBarButtonBase btnFiltro;
	private HorizontalPanel pnlTotalRecaudado;
	private Label lblTotal;
	protected Label lblMontoTotal;
	protected TextBoxCalendar txtFecha;

	public UIRecaudado() {
		init();
		initWidgetListener();
		style();
		reCalcularWindows();
	}

	private void init() {
		txtFecha=new TextBoxCalendar();
		main = new LayoutPanel();
		// Window.addResizeHandler(this);

		toolBar = new ToolBar();
		toolBar.getBtnNuevo().setVisible(false);
		toolBar.getBtnEditar().setVisible(false);
		toolBar.getBtnEliminar().setVisible(false);
		toolBar.getBtnActualizar().setVisible(false);
		main.add(toolBar);
		
		btnFiltro = new ButtonBarButtonBase(
				MyResource.INSTANCE.getImgFiltro32());
		btnFiltro.setTitle("Amortizar");
		toolBar.add(btnFiltro);

		txtBuscar = new MSearchBox();
		txtBuscar.setPlaceHolder(constants.buscarAmortizacion());		
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

		grid = new GridRecaudado();
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
		pnlTotalRecaudado = new HorizontalPanel();
		main.add(pnlTotalRecaudado);
		lblTotal = new Label(constants.recaudado());
		pnlTotalRecaudado.add(lblTotal);

		lblMontoTotal = new Label("0.00");
		pnlTotalRecaudado.add(lblMontoTotal);
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
		btnFiltro.addTouchEndHandler(this);
		txtFecha.getCalendar().getBtnAceptar().addTouchEndHandler(this);
	}

	private void style() {
		MyResource.INSTANCE.getStlUIRecaudado().ensureInjected();
		
		btnFiltro.getElement().getStyle().setFloat(Style.Float.RIGHT);
		btnFiltro.getElement().getStyle().setRight(4, Unit.PX);

		// main.setWidth("100%");
		headerGridMonto.setWidth("24%");
		headerGridFecha.setWidth("30%");
		headerGridCliente.setWidth("46%");
		/*
		 * setHeightContainer(127); setWidthGrid(); container.setWidth("100%");
		 * scrollPanel.setWidth("100%"); scrollPanel.setHeight("100%");
		 */

		txtBuscar.addStyleName(MyResource.INSTANCE.getStlUIRecaudado()
				.txtBuscarUIRecaudado());
		grid.addStyleName(MyResource.INSTANCE.getStlUIRecaudado()
				.gridUIRecaudado());

		headerGridFecha.getElement().getStyle().setFloat(Style.Float.LEFT);
		headerGridCliente.getElement().getStyle().setFloat(Style.Float.LEFT);
		headerGridMonto.getElement().getStyle().setFloat(Style.Float.LEFT);
		pnlTotalRecaudado.setWidth("100%");
		pnlTotalRecaudado.setHeight("40px");
		pnlTotalRecaudado.addStyleName(MyResource.INSTANCE
				.getStlUIRecaudado().pnlTotalRecaudadoUIRecaudado());
		lblTotal.addStyleName(MyResource.INSTANCE.getStlUIRecaudado()
				.lblTotalUIRecaudado());
		lblMontoTotal.addStyleName(MyResource.INSTANCE.getStlUIRecaudado()
				.lblMontoTotalUIRecaudado());
	}

	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		main.setHeight((height - heightHeader) + "px");
	}

	@Override
	public void cargarTabla(Date fechaIni) {
		
	}

	@Override
	public void onKeyUp(KeyUpEvent event) {
		// TODO Auto-generated method stub
		filtrar();		
	}

	@Override
	public void onValueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource().equals(txtBuscar)){
			filtrar();
		}/*else if(event.getSource().equals(txtFecha.getTxtFecha())){		
			Window.alert("Hola mundo");
			cargarTabla(txtFecha.getDate());
		}*/
		
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
		if (event.getSource().equals(btnFiltro)) {
			// Window.alert("ok");
			txtFecha.showCalendar();
		}else if(event.getSource().equals(txtFecha.getCalendar().getBtnAceptar())){
			//Window.alert(txtFecha.getText());
			cargarTabla(txtFecha.getDate());
		}
	}



}

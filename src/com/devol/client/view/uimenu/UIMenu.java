package com.devol.client.view.uimenu;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedHandler;
import com.devol.client.cell.MenuCell;
import com.devol.client.resource.MyResource;
import com.devol.client.model.UIHomeHeader;
import com.devol.client.model.UIMenuHeader;
import com.devol.i18n.DevolConstants;

public class UIMenu extends Composite implements InterUIMenu {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private LayoutPanel main;
	private UIHomeHeader headerHome;
	private UIMenuHeader header;
	private FlowPanel container;
	private CellList<String> cellList;
	protected ScrollPanel scrollPanel;

	public UIMenu() {
		inti();
		style();
	}

	private void inti() {
		// TODO Auto-generated method stub
		main = new LayoutPanel();
		initWidget(main);

		headerHome = new UIHomeHeader();
		headerHome.setVisibleBtnMenu(false);
		main.add(headerHome);

		header = new UIMenuHeader();
		header.setCenter(constants.menu());
		main.add(header);

		container = new FlowPanel();
		main.add(container);

		scrollPanel = new ScrollPanel();
		scrollPanel.setScrollingEnabledY(true);
		scrollPanel.setScrollingEnabledX(false);
		scrollPanel.setAutoHandleResize(true);
		scrollPanel.setShowScrollBarX(false);
		scrollPanel.setShowScrollBarY(true);
		//scrollPanel.setScrollingEnabledY(true);
		//container.add(scrollPanel);		
		cellList = new CellList<String>(new MenuCell());
		cellList.setRound(true);

		cellList.addCellSelectedHandler(cellSelectedHandler);		
		//scrollPanel.setWidget(cellList);
		container.add(cellList);
		scrollPanel.setWidget(container);
		main.add(scrollPanel);
		render();		
		/*Window.addResizeHandler(new ResizeHandler(){

			@Override
			public void onResize(ResizeEvent event) {
				// TODO Auto-generated method stub
				reCalcularWindows();
			}});*/
	}
	
	private void reCalcularWindows(){
		//setWidthGrid();
		setHeightContainer(55);				
	}
	
	/*private void setWidthGrid() {
		int width = Window.getClientWidth();
		width = width - 20;
		// grid.setWidth(width + "px");
	}*/
	
	protected void setHeightContainer(int heightHeader) {
		int height = Window.getClientHeight();
		main.setHeight((height - heightHeader) + "px");
	}

	private void render() {
		List<String> lista = new ArrayList<String>();
		lista.add(constants.clientes());
		lista.add(constants.prestamos());
		lista.add(constants.historial());
		lista.add(constants.reportes());
		//lista.add("Mi Cuenta");
		lista.add(constants.salir());
		cellList.render(lista);
	}

	private CellSelectedHandler cellSelectedHandler = new CellSelectedHandler() {

		@Override
		public void onCellSelected(CellSelectedEvent event) {
			// TODO Auto-generated method stub
			viewMenuItem(event.getIndex());
		}

	};

	private void style() {
		// TODO Auto-generated method stub
		MyResource.INSTANCE.getStlUIMenu().ensureInjected();
		Window.setMargin("0px");
		int height = Window.getClientHeight();
		//container.setHeight((height - 84) + "px");
		//scrollPanel.setWidth("100%");
		//scrollPanel.setHeight("100%");
		cellList.addStyleName(MyResource.INSTANCE.getStlUIMenu().cellList());
	}

	@Override
	public void viewMenuItem(int item) {
		// TODO Auto-generated method stub

	}
}

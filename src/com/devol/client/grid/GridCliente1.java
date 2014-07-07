package com.devol.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.devol.client.util.FilteredListDataProvider;
import com.devol.client.util.IFilter;
import com.devol.server.model.bean.Cliente;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.view.client.SingleSelectionModel;

public class GridCliente1 extends CellTable<Cliente>{

	private List<Cliente> data=new ArrayList<Cliente>();				
	private final SingleSelectionModel<Cliente> selectionModel= new SingleSelectionModel<Cliente>();
	private FilteredListDataProvider<Cliente> dataProvider= new FilteredListDataProvider<Cliente>(new IFilter<Cliente>(){

		@Override
		public boolean isValid(Cliente value, String filter) {
			// TODO Auto-generated method stub
			if(filter==null || value==null)
                return true;
            String values= value.getNombre() + " "+ value.getApellido();
			return values.toLowerCase().contains(filter.toLowerCase());
		}});
	
	public GridCliente1(){		
		initComponents();
	}

	
	private void initComponents(){			
		this.setWidth("100%");									
		initColumns();			
		this.setRowCount(data.size(), true);		
		this.setRowData(0,data);
		this.setPageSize(data.size());		
		dataProvider.setList(data);
		dataProvider.addDataDisplay(this);
		this.setVisible(true);			 
        this.setSelectionModel(selectionModel);                 
	};
	
	private void initColumns(){
		this.addColumn(dniCliente,"DNI");	
		this.addColumn(nombreCliente,"CLIENTE");	
	}
	
	private	Column<Cliente, String> dniCliente =
            new Column<Cliente, String>(new TextCell()) {
              @Override
              public String getValue(Cliente object) {
                return object.getDni();
              }
            };
		
	private	Column<Cliente, String> nombreCliente =
	            new Column<Cliente, String>(new TextCell()) {
	              @Override
	              public String getValue(Cliente object) {
	                return object.getNombre()+" "+object.getApellido();
	              }
	            };
	            
	
	public List<Cliente> getData() {
		return data;
	}


	public void setData(List<Cliente> data) {	
		dataProvider.setFilter(null);
		this.data = data;
		this.setRowCount(data.size(), true);
		this.setRowData(0,data);
		this.setPageSize(data.size());
		dataProvider.setList(data);
        dataProvider.refresh();
        int alto=data.size()*15;
        this.setHeight(alto+"mm");   
        this.redraw();
	}


	public SingleSelectionModel<Cliente> getSelectionModel() {
		return selectionModel;
	}


	public FilteredListDataProvider<Cliente> getDataProvider() {
		return dataProvider;
	}
}

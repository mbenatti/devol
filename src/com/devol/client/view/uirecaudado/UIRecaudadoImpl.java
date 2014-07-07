package com.devol.client.view.uirecaudado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devol.client.beanproxy.AmortizacionProxy;
import com.devol.client.requestfactory.ContextGestionPrestamo;
import com.devol.client.requestfactory.FactoryGestion;
import com.devol.client.view.uihomereport.UIHomeReport;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.google.gwt.core.shared.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;

public class UIRecaudadoImpl extends UIRecaudado {
	private final FactoryGestion FACTORY = GWT.create(FactoryGestion.class);
	private final EventBus EVENTBUS = new SimpleEventBus();
	private List<AmortizacionProxy> lista;
	private UIHomeReport uiHomeReport;

	public UIRecaudadoImpl(UIHomeReport uiHomeReport) {
		this.uiHomeReport = uiHomeReport;
		// super.setHeightContainer(128);
		//try{
		//cargarTabla();
		//}catch(Exception ex){}
		//this.scrollPanel.refresh();
	}


	@Override
	public void cargarTabla(Date fecha) {		
		lista = new ArrayList<AmortizacionProxy>();
		FACTORY.initialize(EVENTBUS);
		ContextGestionPrestamo context = FACTORY.contextGestionPrestamo();		
		Request<List<AmortizacionProxy>> request = context.listarAmortizacionByDate(UIHomeSesion.usuario.getIdUsuario(),fecha).with("beanPrestamo");
		//Request<List<PrestamoProxy>> request = context.listarPrestamo();
		request.fire(new Receiver<List<AmortizacionProxy>>() {
			@Override
			public void onSuccess(List<AmortizacionProxy> response) {
				// TODO Auto-generated method stub
				/*Iterator<PrestamoProxy> i=response.iterator(); 
				  while(i.hasNext()){
					  PrestamoProxy c=i.next(); 
				  com.google.gwt.user.client.Window.alert(c.getBeanCliente().getNombre()+c.getBeanCliente().getApellido()); }*/
				lista = response;
				grid.getSelectionModel().clear();
				grid.setData(lista);				
				double alto=lista.size()*45;
				container.setHeight(alto+"px");
				scrollPanel.refresh();
				if(lista.size()>0){
				lblMontoTotal.setText(lista.get(0).getTotalAmortizado().toString());
				}else{
					lblMontoTotal.setText("0.00");
				}
			}
		});
	}			
	
}

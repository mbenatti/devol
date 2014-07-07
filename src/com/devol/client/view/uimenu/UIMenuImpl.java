package com.devol.client.view.uimenu;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.devol.client.service.ServiceGestionUsuario;
import com.devol.client.service.ServiceGestionUsuarioAsync;
import com.devol.client.view.uihome.UIHome;
import com.devol.client.view.uihomecliente.UIHomeCliente;
import com.devol.client.view.uihomeprestamo.UIHomePrestamo;
import com.devol.client.view.uihomereport.UIHomeReport;
import com.devol.client.view.uihomesesion.UIHomeSesion;

public class UIMenuImpl extends UIMenu {
	private final ServiceGestionUsuarioAsync SERVICE = GWT
			.create(ServiceGestionUsuario.class);

	public UIMenuImpl() {
		init();				
	}

	private void init() {
		// TODO Auto-generated method stub
	}

	@Override
	public void viewMenuItem(int item) {
		// TODO Auto-generated method stub
		if (item == 0) {
			UIHomeCliente uiHomeCliente=new UIHomeCliente();
			//uiHomeCliente.getUIClienteImpl().refreshScroll();
			//uiHomeCliente.getUIClienteImpl().cargarTabla();
			//uiHomeCliente.getUIClienteImpl().refreshScroll();
			UIHomeSesion.animationHelper.goTo(uiHomeCliente, Animation.SLIDE);			
			//uiHomeCliente.getUIClienteImpl().cargarTabla();			
		} else if (item == 1) {
			UIHomePrestamo uiHomePrestamo=new UIHomePrestamo("PRESTAMO");
			UIHomeSesion.animationHelper.goTo(uiHomePrestamo, Animation.SLIDE);			
		}else if (item == 2) {
			UIHomePrestamo uiHomePrestamo=new UIHomePrestamo("HISTORIAL");
			UIHomeSesion.animationHelper.goTo(uiHomePrestamo, Animation.SLIDE);			
		}else if (item == 3) {
			UIHomeReport uiHomeReport=new UIHomeReport();
			UIHomeSesion.animationHelper.goTo(uiHomeReport, Animation.SLIDE);			
		}else if (item == 4) {
			SERVICE.logout(new AsyncCallback<Boolean>(){

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					Dialogs.alert("Alerta", caught.getMessage(), null);
				}

				@Override
				public void onSuccess(Boolean result) {
					// TODO Auto-generated method stub
					if(result){
					UIHomeSesion.usuario=null;
					UIHome uiHome=new UIHome();
					RootPanel.get().clear();
					RootPanel.get().add(uiHome);
					}
				}});						
		}
	}
	
	public void refreshScroll(){
		this.scrollPanel.refresh();
	}
}

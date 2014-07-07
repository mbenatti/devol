package com.devol.client.view.uiiniciarsesion;

import com.devol.client.service.ServiceGestionUsuario;
import com.devol.client.service.ServiceGestionUsuarioAsync;
import com.devol.client.view.uihome.UIHome;
import com.devol.client.view.uihomesesion.UIHomeSesion;
import com.devol.client.view.uimenu.UIMenuImpl;
import com.devol.i18n.DevolConstants;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.devol.server.model.bean.UsuarioRPC;

public class UIIniciarSesionImpl extends UIIniciarSesion {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	private final ServiceGestionUsuarioAsync SERVICE = GWT
			.create(ServiceGestionUsuario.class);
	private UIHome uiHome;
	
	public UIIniciarSesionImpl(UIHome uiHome) {
		this.uiHome = uiHome;
		this.scrollPanel.refresh();			
	}
	

	@Override
	public void login() {
		// TODO Auto-generated method stub
		SERVICE.logearUsuario(this.txtCorreo.getText(),
				this.txtClave.getText(), new AsyncCallback<UsuarioRPC>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						//Window.alert(caught.getMessage());
						Dialogs.alert("Login",constants.usuarioClaveIncorrecto(), null);
					}

					@Override
					public void onSuccess(UsuarioRPC result) {
						// TODO Auto-generated method stub
						if(result != null){
							UIHomeSesion.usuario=result;
							Dialogs.alert("Login",constants.bienvenido()+" "+UIHomeSesion.usuario.getNombres(), null);
							irCuenta();
						}else{
							Dialogs.alert("Login",constants.usuarioClaveIncorrecto(), null);
						}
					}
				});
	}
	
	@Override
	public void irCuenta() {
		// TODO Auto-generated method stub		
		/*uiHome.getContainer().showWidget(1);
		uiHome.getUiMenuImpl().refreshScroll();*/
		uiHome.getContainer().showWidget(2);
		UIHomeSesion.animationHelper.goTo(new UIMenuImpl(),
				Animation.SLIDE_REVERSE);					
	}	
	
	@Override
	public void irCrearCuenta() {
		// TODO Auto-generated method stub
		uiHome.getContainer().showWidget(1);		
	}
}

package com.devol.client;

import java.util.logging.Level;
import java.util.logging.Logger;


import com.devol.client.view.uihome.UIHome;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.UmbrellaException;

public class Devol implements EntryPoint {
	private Logger logger = Logger.getLogger(Devol.class.getName());

	@Override
	public void onModuleLoad() {
		MGWT.applySettings(MGWTSettings.getAppSetting());	
		GWT.setUncaughtExceptionHandler(new   
			      GWT.UncaughtExceptionHandler() {  
			      @SuppressWarnings("unused")
				public void onUncaughtException(Throwable e) { 
			    	  Throwable unwrapped = unwrap(e); 
			    	  logger.log(Level.SEVERE,"Causa: "+e.getCause().toString()+"\n"+"Mensaje: "+e.getMessage(), e);			    	  
			        // do exception handling stuf 
			    	  //Window.alert(e.getCause().toString()+"\n"+e.getMessage());
			    	  //Window.alert(e.getMessage());
			    	  //Dialogs.alert("Cliente", e.getCause().toString()+"\n"+e.getMessage(), null);
			    	  Dialogs.alert("Alerta", e.getMessage(), null);
			    }  
		});
		//MGWTStyle.setTheme(new MGWTColorTheme());
		Window.setMargin("0px");

		 //UIIniciarSesion ui = new UIIniciarSesion();
		// UICliente ui = new UICliente();
		// UIPrestamo ui = new UIPrestamo();
		// UIMantAmortizar ui = new UIMantAmortizar();
		//UIRegistrarUsuario ui = new UIRegistrarUsuario();
		// UIMantCliente ui = new UIMantCliente();
		// UIAmortizacion ui = new UIAmortizacion();
		// UIMantPrestamo ui = new UIMantPrestamo();
		UIHome ui = new UIHome();
		//UIIniciarSesionImpl ui = new UIIniciarSesionImpl();
		RootPanel.get().add(ui);
		History.newItem("X");
		History.addValueChangeHandler(valueChangeHandler);
	}
	
	ValueChangeHandler<String> valueChangeHandler=new ValueChangeHandler<String>(){

		@Override
		public void onValueChange(ValueChangeEvent<String> event) {
			// TODO Auto-generated method stub
			String historyToken=event.getValue();
			if (!historyToken.equals("x"))
				  History.newItem("x");
		}

		
		
	};
	
	public Throwable unwrap(Throwable e) {   
	    if(e instanceof UmbrellaException) {   
	      UmbrellaException ue = (UmbrellaException) e;  
	      if(ue.getCauses().size() == 1) {   
	        return unwrap(ue.getCauses().iterator().next());  
	      }  
	    }  
	    return e;  
	  }

}

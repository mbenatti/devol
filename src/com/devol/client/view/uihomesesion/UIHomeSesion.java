package com.devol.client.view.uihomesesion;

import com.devol.client.resource.MyResource;
import com.devol.server.model.bean.UsuarioRPC;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;

public class UIHomeSesion extends Composite {

	private FlowPanel main;
	public static AnimationHelper animationHelper;
	public static UsuarioRPC usuario =null;

	public UIHomeSesion() {
		init();
		style();
		History.addValueChangeHandler(valueChangeHandler);
	}
	 ValueChangeHandler<String> valueChangeHandler=new ValueChangeHandler<String>(){

		@Override
		public void onValueChange(ValueChangeEvent<String> event) {
			// TODO Auto-generated method stub
			String historyToken=event.getValue();
			if(historyToken.equalsIgnoreCase("login")){
				if(usuario!=null){
				History.newItem("sesion");
				}
			}else if(historyToken.equalsIgnoreCase("register")){
				if(usuario!=null){
				History.newItem("sesion");
				}
			}else if(historyToken.equalsIgnoreCase("sesion")){
				History.newItem("sesion");
			}
		}

		
		 
	 };

	private void init() {
		main = new FlowPanel();
		initWidget(main);

		animationHelper = new AnimationHelper();
		animationHelper.getElement().setId("animationHelper");
		// animationHelper.setAnimationDuration(1000);
		main.add(animationHelper);
	}

	private void style() {
		MyResource.INSTANCE.getStlUIHome().ensureInjected();
		main.setWidth("100%");
		animationHelper.setWidth("100%");
		animationHelper.addStyleName(MyResource.INSTANCE.getStlUIHome()
				.animationHelper());
	}

}

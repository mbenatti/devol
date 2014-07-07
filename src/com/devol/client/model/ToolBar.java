package com.devol.client.model;

import com.devol.client.resource.MyResource;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.FlowPanel;
import com.googlecode.mgwt.ui.client.widget.buttonbar.ButtonBarButtonBase;

public class ToolBar extends FlowPanel {

	private ButtonBarButtonBase btnNuevo;
	private ButtonBarButtonBase btnEditar;
	private ButtonBarButtonBase btnEliminar;
	private ButtonBarButtonBase btnActualizar;

	public ToolBar() {
		init();
		style();
	}

	private void init() {
		btnNuevo = new ButtonBarButtonBase(MyResource.INSTANCE.getImgNew32());
		btnNuevo.setTitle("Nuevo");
		add(btnNuevo);

		btnEditar = new ButtonBarButtonBase(MyResource.INSTANCE.getImgEdit32());
		btnEditar.setTitle("Editar");
		add(btnEditar);

		btnEliminar = new ButtonBarButtonBase(
				MyResource.INSTANCE.getImgRemove32());
		btnEliminar.setTitle("Eliminar");
		add(btnEliminar);

		btnActualizar = new ButtonBarButtonBase(
				MyResource.INSTANCE.getImgRefresh32());
		btnActualizar.setTitle("Actualizar");
		add(btnActualizar);
	}

	private void style() {
		MyResource.INSTANCE.getStlModel().ensureInjected();
		addStyleName(MyResource.INSTANCE.getStlModel().mainToolBar());
		setWidth("100%");
		getElement().getStyle().setDisplay(Style.Display.INLINE_BLOCK);

		btnNuevo.getElement().getStyle().setFloat(Style.Float.LEFT);
		btnEditar.getElement().getStyle().setFloat(Style.Float.LEFT);
		btnEliminar.getElement().getStyle().setFloat(Style.Float.LEFT);
		btnActualizar.getElement().getStyle().setFloat(Style.Float.LEFT);
	}

	public ButtonBarButtonBase getBtnNuevo() {
		return btnNuevo;
	}

	public ButtonBarButtonBase getBtnEditar() {
		return btnEditar;
	}

	public ButtonBarButtonBase getBtnEliminar() {
		return btnEliminar;
	}

	public ButtonBarButtonBase getBtnActualizar() {
		return btnActualizar;
	}

}

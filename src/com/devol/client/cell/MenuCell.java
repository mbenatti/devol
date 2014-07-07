package com.devol.client.cell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.googlecode.mgwt.ui.client.widget.celllist.Cell;
import com.devol.client.resource.MyResource;
import com.devol.i18n.DevolConstants;

public class MenuCell implements Cell<String> {
	private DevolConstants constants = GWT.create(DevolConstants.class);
	
	@Override
	public void render(SafeHtmlBuilder safeHtmlBuilder, String model) {
		// TODO Auto-generated method stub
		String imageHtml = "";

		if (model.equalsIgnoreCase(constants.clientes())) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgClientes32()).getHTML();
		} else if (model.equalsIgnoreCase(constants.prestamos())) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgPago32()).getHTML();
		} else if (model.equalsIgnoreCase(constants.historial())) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgCheckPago32()).getHTML();
		} else if (model.equalsIgnoreCase(constants.reportes())) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgRepote32()).getHTML();
		}/*else if (model.equalsIgnoreCase("Mi Cuenta")) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgUserSettings32()).getHTML();
		} */else if (model.equalsIgnoreCase(constants.salir())) {
			imageHtml = AbstractImagePrototype.create(
					MyResource.INSTANCE.getImgOff32()).getHTML();
		}

		safeHtmlBuilder
				.appendHtmlConstant("<div style='width: 45px; float: left; height: 36px;'>");
		safeHtmlBuilder.appendHtmlConstant(imageHtml);
		safeHtmlBuilder.appendHtmlConstant("</div>");
		safeHtmlBuilder
				.appendHtmlConstant("<div style='float: left; height: 36px; line-height:35px;'>");
		safeHtmlBuilder.appendEscaped(model);
		safeHtmlBuilder.appendHtmlConstant("</div>");		

	}

	@Override
	public boolean canBeSelected(String model) {
		// TODO Auto-generated method stub
		return false;
	}

}

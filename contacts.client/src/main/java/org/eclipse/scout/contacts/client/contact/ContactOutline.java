package org.eclipse.scout.contacts.client.contact;

import java.util.List;

import org.eclipse.scout.apps.contacts.shared.Icons;
import org.eclipse.scout.contacts.client.person.OrganizationTablePage;
import org.eclipse.scout.contacts.client.person.PersonTablePage;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

/**
 * @author amdy
 */
@Order(1000)
public class ContactOutline extends AbstractOutline {

	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		// super.execCreateChildPages(pageList);
		// pageList.add(new HelloWorldPage());

		pageList.add(new PersonTablePage());
		pageList.add(new OrganizationTablePage());
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Contacts");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.CategoryBold;
	}

}

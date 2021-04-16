package org.eclipse.scout.apps.contacts.client.search;

import org.eclipse.scout.apps.contacts.shared.Icons;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractSearchOutline;
import org.eclipse.scout.rt.platform.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author amdy
 */
@Order(2000)
public class SearchOutline extends AbstractSearchOutline {

	private static final Logger LOG = LoggerFactory.getLogger(SearchOutline.class);

	@Override
	protected void execSearch(final String query) {
		LOG.info("Search started");
		// TODO [amdy]: Implement search
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Search;
	}
}

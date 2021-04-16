package org.eclipse.scout.contacts.server.person;

import org.eclipse.scout.contacts.server.sql.SQLs;
import org.eclipse.scout.contacts.shared.person.IOrganizationService;
import org.eclipse.scout.contacts.shared.person.OrganizationTablePageData;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class OrganizationService implements IOrganizationService {
	@Override
	public OrganizationTablePageData getOrganizationTableData(SearchFilter filter) {
		OrganizationTablePageData pageData = new OrganizationTablePageData();

		String sql = SQLs.ORGANIZATION_PAGE_SELECT + SQLs.ORGANIZATION_PAGE_DATA_SELECT_INTO;
		SQL.selectInto(sql, new NVPair("page", pageData));
		return pageData;
	}
}

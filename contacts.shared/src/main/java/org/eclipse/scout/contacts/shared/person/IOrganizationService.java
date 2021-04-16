package org.eclipse.scout.contacts.shared.person;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IOrganizationService extends IService {
	OrganizationTablePageData getOrganizationTableData(SearchFilter filter);
}

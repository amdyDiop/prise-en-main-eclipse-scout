package org.eclipse.scout.contacts.client.person;

import org.eclipse.scout.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.contacts.client.person.OrganizationTablePage.Table;
import org.eclipse.scout.contacts.client.person.OrganizationTablePage.Table.OrganizationIdColumn;
import org.eclipse.scout.contacts.shared.person.IOrganizationService;
import org.eclipse.scout.contacts.shared.person.OrganizationTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@Data(OrganizationTablePageData.class)
public class OrganizationTablePage extends AbstractPageWithTable<Table> {
	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Organizations");
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IOrganizationService.class).getOrganizationTableData(filter));
	}

	public class Table extends AbstractTable {

		public OrganizationIdColumn getOrganizationIdColumn() {
			return getColumnSet().getColumnByClass(OrganizationIdColumn.class);
		}

		@Order(1)
		public class OrganizationIdColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("MyNlsKey");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}

			@Override
			protected boolean getConfiguredPrimaryKey() {
				return true;
			}

		}

		@Order(2)
		public class NameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Name");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}

		}

		@Order(3)
		public class CityColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("City");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}

		}

		@Order(4)
		public class ContryColumn extends AbstractSmartColumn<String> {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Country");
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}

			@Override
			protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
				return CountryLookupCall.class;
			}

		}

		@Order(5)
		public class Homepage extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Home");
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}

			@Override
			protected int getConfiguredWidth() {
				return 120;
			}
		}

	}
}

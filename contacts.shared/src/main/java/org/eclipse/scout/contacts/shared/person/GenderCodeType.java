package org.eclipse.scout.contacts.shared.person;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class GenderCodeType extends AbstractCodeType<String, String> {
	private static final long serialVersionUID = 1L;
	// TODO [amdy] set id value
	public static final String ID = "Gender";

	@Override
	public String getId() {
		return ID;
	}

	@Order(1000)
	public static class MaleCode extends AbstractCode<String> {
		private static final long serialVersionUID = 1L;
		public static final String ID = "M";

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Male");
		}

		@Override
		public String getId() {
			return ID;
		}
	}

	@Order(2000)
	public static class FemaleCode extends AbstractCode<String> {
		private static final long serialVersionUID = 1L;
		public static final String ID = "F";

		@Override
		protected String getConfiguredText() {
			return TEXTS.get("Female");
		}

		@Override
		public String getId() {
			return ID;
		}
	}

}

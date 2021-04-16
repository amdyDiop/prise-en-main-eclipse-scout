package org.eclipse.scout.contacts.client.person;

import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.CancelButton;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.OkButton;
import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.basic.table.organizer.OrganizeColumnsForm.MainBox.GroupBox;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

@FormData(value = PersonFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class PersonForm extends AbstractForm {

	private String personId;

	@FormData
	public String getPersonId() {
		return personId;
	}

	@FormData
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	@Override
	public Object computeExclusiveKey() {
		return getPersonId();
	}

	@Override
	protected int getConfiguredDisplayHint() {
		return IForm.DISPLAY_HINT_VIEW;
	}

	@Override
	protected String getConfiguredTitle() {
		// TODO [amdy] verify translation
		return TEXTS.get("Person");
	}

	@Order(10)
	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public GroupBox getGroupBox() {
		return getFieldByClass(GroupBox.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	@Order(1000)
	public class MainBox extends AbstractGroupBox {
//		@Order(1000)
//		public class GroupBox extends AbstractGroupBox {
//
//		}
//
//		@Order(2000)
//		public class OkButton extends AbstractOkButton {
//
//		}
//
//		@Order(3000)
//		public class CancelButton extends AbstractCancelButton {
//
//		}

		@Order(10)
		public class GeneralBox extends AbstractGroupBox {
		}

		@Order(20)
		public class DetailsBox extends AbstractTabBox {
			@Order(10)
			public class ContactInfoBox extends AbstractGroupBox {
				@Order(10)
				public class AddressBox extends AbstractGroupBox {
				}
			}

			@Order(20)
			public class WorkBox extends AbstractGroupBox {
			}

			@Order(30)
			public class NotesBox extends AbstractGroupBox {
			}
		}

		@Order(30)
		public class OkButton extends AbstractOkButton {
		}

		@Order(40)
		public class CancelButton extends AbstractCancelButton {
		}
	}

	public void startModify() {
		startInternalExclusive(new ModifyHandler());
	}

	public void startNew() {
		startInternal(new NewHandler());
	}

	public class NewHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = service.prepareCreate(formData);
			importFormData(formData);

			// setEnabledPermission(new CreatePersonPermission());
		}

		@Override
		protected void execStore() {
			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			service.create(formData);
		}
	}

	public class ModifyHandler extends AbstractFormHandler {
		@Override
		protected void execLoad() {
			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			// setEnabledPermission(new UpdatePersonPermission());
		}

		@Override
		protected void execStore() {
			IPersonService service = BEANS.get(IPersonService.class);
			PersonFormData formData = new PersonFormData();
			exportFormData(formData);
			service.store(formData);
		}
	}
}

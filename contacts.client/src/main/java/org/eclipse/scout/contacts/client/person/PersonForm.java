package org.eclipse.scout.contacts.client.person;

import org.eclipse.scout.apps.contacts.shared.Icons;
import org.eclipse.scout.contacts.client.common.CountryLookupCall;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.EmailField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.ContactInfoBox.MobileField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.EmailWorkField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.MyStringField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.OrganizationField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.DetailsBox.WorkBox.PhoneField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.FirstNameField;
import org.eclipse.scout.contacts.client.person.PersonForm.MainBox.GeneralBox.LastNameField;
import org.eclipse.scout.contacts.shared.person.GenderCodeType;
import org.eclipse.scout.contacts.shared.person.IPersonService;
import org.eclipse.scout.contacts.shared.person.PersonFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.basic.table.organizer.OrganizeColumnsForm.MainBox.GroupBox;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

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

	public FirstNameField getFirstNameField() {
		return getFieldByClass(FirstNameField.class);
	}

	public LastNameField getLastNameField() {
		return getFieldByClass(LastNameField.class);
	}

	public MyStringField getMyStringField() {
		return getFieldByClass(MyStringField.class);
	}

	public OrganizationField getOrganizationField() {
		return getFieldByClass(OrganizationField.class);
	}

	public EmailWorkField getEmailWorkField() {
		return getFieldByClass(EmailWorkField.class);
	}

	public PhoneField getPhoneField() {
		return getFieldByClass(PhoneField.class);
	}

	public MobileField getMobileField() {
		return getFieldByClass(MobileField.class);
	}

	public EmailField getEmailField() {
		return getFieldByClass(EmailField.class);
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
			@Order(10)
			public class PictureUrlField extends AbstractStringField {
				@Override
				protected boolean getConfiguredVisible() {
					return false;
				}
			}

			@Order(20)
			public class PictureField extends AbstractImageField {
				@Override
				protected Class<PictureUrlField> getConfiguredMasterField() {
					return PictureUrlField.class;
				}

				@Override
				protected void execChangedMasterValue(Object newMasterValue) {
					updateImage((String) newMasterValue);
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Override
				protected int getConfiguredGridH() {
					return 5;
				}

				@Override
				protected boolean getConfiguredAutoFit() {
					return true;
				}

				@Override
				protected String getConfiguredImageId() {
					return Icons.User;
				}

				protected void updateImage(String url) {
					setImageUrl(url);
				}
			}

			@Order(30)
			public class FirstNameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("FirstName");
				}

			}

			@Order(40)
			public class LastNameField extends AbstractStringField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("LastName");
				}
			}

			@Order(50)
			public class DateOfBirthField extends AbstractDateField {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("DateOfBirth");
				}
			}

			@Order(60)
			public class GenderGroup extends AbstractRadioButtonGroup<String> {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Gender");
				}

				@Override
				protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
					return GenderCodeType.class;
				}
			}

		}

		@Order(20)
		public class DetailsBox extends AbstractTabBox {
			@Order(10)
			public class ContactInfoBox extends AbstractGroupBox {
				@Order(10)
				public class AddressBox extends AbstractGroupBox {

					@Override
					protected boolean getConfiguredBorderVisible() {
						return false;
					}

					@Override
					protected int getConfiguredGridH() {
						return 3;
					}

					@Override
					protected int getConfiguredGridW() {
						return 1;
					}

					@Override
					protected int getConfiguredGridColumnCount() {
						return 1;
					}

					@Order(10)
					public class StreetField extends AbstractStringField {
						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Street");
						}
					}// use a sequence box for horizontal layout￼

					@Order(20)
					public class LocationBox extends AbstractSequenceBox {
						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Location");
						}

						@Override
						protected boolean getConfiguredAutoCheckFromTo() {
							return false;
						}

						@Order(10)
						public class CityField extends AbstractStringField {
							@Override
							protected String getConfiguredLabel() {
								return TEXTS.get("City");
							}

							@Override
							protected byte getConfiguredLabelPosition() {
								return LABEL_POSITION_ON_FIELD;
							}
						}

						@Order(20)
						public class CountryField extends AbstractSmartField<String> {
							@Override
							protected String getConfiguredLabel() {
								return TEXTS.get("Country");
							}

							@Override
							protected byte getConfiguredLabelPosition() {
								return LABEL_POSITION_ON_FIELD;
							}

							@Override
							protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
								return CountryLookupCall.class;
							}
						}
					}
				}

				@Order(20)
				public class PhoneField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Phone");
					}

				}

				@Order(30)
				public class MobileField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Mobile");
					}
				}

				@Order(2000)
				public class EmailField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Email");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

			}

			@Order(20)
			public class WorkBox extends AbstractGroupBox {

				@Order(1000)
				public class MyStringField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Position");
					}
				}

				@Order(2000)
				public class OrganizationField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Organization");
					}

				}

				@Order(3000)
				public class PhoneField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Phone");
					}

				}

				@Order(4000)
				public class EmailWorkField extends AbstractStringField {
					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("E-Mail");
					}

					@Override
					protected int getConfiguredMaxLength() {
						return 128;
					}
				}

			}

			@Order(30)
			public class NotesBox extends AbstractGroupBox {
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Notes");
				}

				@Order(10)
				public class NotesField extends AbstractStringField {
					@Override
					protected int getConfiguredGridH() {
						return 4;
					}

					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}

					@Override
					protected boolean getConfiguredMultilineText() {
						return true;
					}
				}
			}
		}
	}

	@Order(30)
	public class OkButton extends AbstractOkButton {
	}

	@Order(40)
	public class CancelButton extends AbstractCancelButton {
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

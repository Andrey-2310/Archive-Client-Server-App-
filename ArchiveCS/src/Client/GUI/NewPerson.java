package Client.GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

import Client.ClientConnector;
import Person.Person;
import Server.Communication.Request;
import Server.Communication.Request.Requests;
import Server.Communication.Response;

public class NewPerson extends Shell {
	Request request;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void NewPersonStarter(Person person, ClientConnector connector) {
		try {
			//NewPerson.connector=connector;
			Display display = Display.getDefault();
			final NewPerson shell = new NewPerson(display, person, connector);
			shell.open();
			shell.layout();

			    shell.pack();
			    shell.open();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			System.gc();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public NewPerson(final Display display, final Person person, final ClientConnector connector) {
		super(display, SWT.SHELL_TRIM);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		composite.setBounds(0, 0, 284, 361);
		
		final Text[] textFields=new Text[8]; 
		for(int i=0; i<textFields.length; i++){	
		textFields[i] = new Text(composite, SWT.BORDER);
		textFields[i].setBounds(137, 22+i*28, 106, 21);
		}
		
		final CCombo combo = new CCombo(composite, SWT.BORDER);
		combo.setItems(new String[] {"Man", "Woman"});
		combo.setBounds(137, 250, 106, 21);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel.setBounds(43, 21, 69, 18);
		lblNewLabel.setText("Surname");
		
		Label lblSurname = new Label(composite, SWT.NONE);
		lblSurname.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblSurname.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblSurname.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblSurname.setBounds(43, 49, 69, 19);
		lblSurname.setText("Name");
		
		Label lblFathername = new Label(composite, SWT.NONE);
		lblFathername.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblFathername.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblFathername.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblFathername.setBounds(43, 77, 88, 18);
		lblFathername.setText("Fathername");
		
		Label lblEmail = new Label(composite, SWT.NONE);
		lblEmail.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblEmail.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblEmail.setBounds(43, 103, 69, 21);
		lblEmail.setText("Telephone");
		
		Label lblTelephone = new Label(composite, SWT.NONE);
		lblTelephone.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblTelephone.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblTelephone.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblTelephone.setBounds(43, 133, 69, 21);
		lblTelephone.setText("Email");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblNewLabel_1.setBounds(43, 160, 96, 21);
		lblNewLabel_1.setText("WorkingPlace");
		
		
		Label lblExperience = new Label(composite, SWT.NONE);
		lblExperience.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblExperience.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblExperience.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblExperience.setText("Experience");
		lblExperience.setBounds(43, 190, 80, 21);
		
		
		Label lblPosition = new Label(composite, SWT.NONE);
		lblPosition.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblPosition.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblPosition.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblPosition.setText("Position");
		lblPosition.setBounds(43, 221, 69, 21);
		
		
		Label lblSex = new Label(composite, SWT.NONE);
		lblSex.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblSex.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblSex.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblSex.setBounds(43, 250, 55, 21);
		lblSex.setText("Sex");
		
		Button btnOk = new Button(composite, SWT.NONE);
		btnOk.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnOk.setBounds(43, 309, 75, 25);
		btnOk.setText("Ok");
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (person!=null) {
					request = new Request(Requests.EDIT, null, person, getPersonFromFields(textFields, combo));
				} else {
					request = new Request();
					request.setRequest(Request.Requests.ADD);
					request.setPersonalData( getPersonFromFields(textFields, combo));
				}
				connector.pushRequest(request);
				Response responce = connector.setNewRequest(request);
				if (responce == null) {
					System.exit(0);
				}
				getShell().close();
				//display.dispose();
			}
		});
		
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnCancel.setBounds(156, 309, 75, 25);
		btnCancel.setText("Cancel");
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				getShell().close();
			}
		});
		
		createContents();
		settingUpFields(textFields, combo, person);
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Processing person");
		setSize(300, 400);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public static void settingUpFields(Text[] textfields, CCombo combo, Person person){
		if(person==null) return;
		textfields[0].setText(person.getSurname());
		textfields[1].setText(person.getName());
		textfields[2].setText(person.getFathername());
		textfields[3].setText(person.getPhone());
		textfields[4].setText(person.getEmail());
		textfields[5].setText(person.getEmployer());
		textfields[6].setText(person.getJobExperience());
		textfields[7].setText(person.getJobPosition());
		combo.setText(person.getSex());
	}
	
	public static Person  getPersonFromFields(Text[] textfields, CCombo combo){
		return new Person(textfields[0].getText(), textfields[1].getText(), textfields[2].getText(), textfields[3].getText(), 
				textfields[4].getText(), textfields[5].getText(), textfields[6].getText(), textfields[7].getText(), combo.getText());
	}
}



package Client.GUI;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;

import Client.ClientConnector;
import Person.Person;
import Server.Communication.Request;
import Server.Communication.Request.Requests;
import Server.Communication.Response;
import Server.User.UserDescription;

import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

public class ClientWorkingWindow extends Shell {

	Vector<UserDescription> users;
	private Text textSearch;
	Composite composite = new Composite(this, SWT.NONE);

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void workingWindowStart(String userRole, ClientConnector connector) {
		Display display = Display.getDefault();
		ClientWorkingWindow shell = new ClientWorkingWindow(display, userRole, connector);
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}	
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public ClientWorkingWindow(Display display, String userRole, final ClientConnector connector) {

		super(display, SWT.SHELL_TRIM);
		//setSize(1021, 449);
		setMinimumSize(new Point(170, 39));
		setBackground(SWTResourceManager.getColor(204, 204, 204));

		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		final Table table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		final Table table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setVisible(false);

		/*  if (!connector.isAdmin()) composite.setBounds(0, 0, 745, 410); 
		  else {*/
		table_1.setVisible(true);
		final Button btnNewButton_3 = new Button(composite, SWT.NONE);
		btnNewButton_3.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setBounds(806, 316, 134, 25);
		btnNewButton_3.setText("Change Role");

		composite.setBounds(0, 0, 1021, 410);
		table_1.setBounds(745, 66, 250, 236);
		table_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selCount = table_1.getSelectionCount();
				if (selCount == 0)
					btnNewButton_3.setEnabled(false);
			}
		});
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		table_1.addMouseListener(new MouseListener() {
			public void mouseUp(MouseEvent e) {
			}

			public void mouseDown(MouseEvent e) {
			}

			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				btnNewButton_3.setEnabled(true);
			}
		});
		btnNewButton_3.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(table_1.getItem(table_1.getSelectionIndex()).getText());
				ChangeUser.setConnector(connector);
				ChangeUser.setUserLogin(table_1.getItem(table_1.getSelectionIndex()).getText());
				ChangeUser.changeUserStart(table_1, table);

			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}

		});

		TableColumn tblclmnLogin = new TableColumn(table_1, SWT.CENTER);
		tblclmnLogin.setWidth(55);
		tblclmnLogin.setText("  Login");

		TableColumn tblclmnPassword = new TableColumn(table_1, SWT.CENTER);
		tblclmnPassword.setWidth(68);
		tblclmnPassword.setText("Password");

		TableColumn tblclmnName = new TableColumn(table_1, SWT.CENTER);
		tblclmnName.setWidth(66);
		tblclmnName.setText("Name");

		TableColumn tblclmnNewColumn = new TableColumn(table_1, SWT.CENTER);
		tblclmnNewColumn.setWidth(57);
		tblclmnNewColumn.setText("Role");

		// }
		table.setBounds(27, 66, 706, 236);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnName_1 = new TableColumn(table, SWT.LEFT);
		tblclmnName_1.setWidth(77);
		tblclmnName_1.setText("  Surname");

		TableColumn tblclmnSurname = new TableColumn(table, SWT.CENTER);
		tblclmnSurname.setWidth(71);
		tblclmnSurname.setText("Name");

		TableColumn tblclmnFathername = new TableColumn(table, SWT.CENTER);
		tblclmnFathername.setWidth(82);
		tblclmnFathername.setText("Fathername");

		TableColumn tblclmnEmail = new TableColumn(table, SWT.CENTER);
		tblclmnEmail.setWidth(94);
		tblclmnEmail.setText("Telephone");

		TableColumn tblclmnTelephone = new TableColumn(table, SWT.CENTER);
		tblclmnTelephone.setWidth(100);
		tblclmnTelephone.setText("Email");

		TableColumn tblclmnEmployer = new TableColumn(table, SWT.CENTER);
		tblclmnEmployer.setWidth(70);
		tblclmnEmployer.setText("Employer");

		TableColumn tblclmnExperience = new TableColumn(table, SWT.CENTER);
		tblclmnExperience.setWidth(74);
		tblclmnExperience.setText("Experience");

		TableColumn tblclmnPosition = new TableColumn(table, SWT.CENTER);
		tblclmnPosition.setWidth(62);
		tblclmnPosition.setText("Position");

		TableColumn tblclmnSex = new TableColumn(table, SWT.CENTER);
		tblclmnSex.setWidth(71);
		tblclmnSex.setText("Sex");

		textSearch = new Text(composite, SWT.CENTER);
		textSearch.setBounds(159, 29, 289, 25);

		final Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnNewButton.setEnabled(false);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int selected = table.getSelectionIndex();
				NewPerson.NewPersonStarter(getPersonFromTable(table), connector);

			}
		});
		btnNewButton.setBounds(104, 316, 82, 25);
		btnNewButton.setText("Change");
		if(connector.isGuest())
			btnNewButton.setVisible(false);

		final Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Request request = new Request(Requests.DELETE, null, getPersonFromTable(table), null);
				connector.pushRequest(request);
				Response responce = connector.setNewRequest(request);
				if (responce == null) {
					System.exit(0);
				}
				refreshing(connector, table_1, table);
				btnNewButton_1.setEnabled(false);
				btnNewButton.setEnabled(false);
			}
		});
		btnNewButton_1.setBounds(223, 316, 82, 25);
		btnNewButton_1.setText("Delete");
		if(!connector.isAdmin())
			btnNewButton_1.setVisible(false);

		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnNewButton_1.setEnabled(true);
				btnNewButton.setEnabled(true);
			}
		});

		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				NewPerson.NewPersonStarter(null, connector);
			}
		});
		btnNewButton_2.setBounds(345, 316, 82, 25);
		btnNewButton_2.setText("Add");
		if(!connector.isAdmin())
			btnNewButton_2.setVisible(false);

		Button btnSearch = new Button(composite, SWT.NONE);
		btnSearch.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnSearch.setBounds(27, 29, 111, 25);
		btnSearch.setText("Search");
		btnSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setUpPersons(connector.setNewRequest(new Request(Requests.FIND, textSearch.getText(), null, null))
						.getPersons(), table);
			}
		});

		Image image = new Image(display, "refresh.png");
		Button btnNewButton_5 = new Button(composite, SWT.NONE);
		btnNewButton_5.setBounds(27, 317, 38, 25);
		btnNewButton_5.setImage(image);
		btnNewButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				refreshing(connector, table_1, table);
			}
		});

		Image image1 = new Image(display, "left.png");
		Button btnNewButton_6 = new Button(composite, SWT.NONE);
		btnNewButton_6.setImage(image1);
		btnNewButton_6.setBounds(463, 317, 53, 25);
		btnNewButton_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Request lastRequest = connector.popLastRequest();
				if (lastRequest != null)
					if (connector.setNewRequest(Request.reformulateRequest(lastRequest)).isSuccess())
						refreshing(connector, table_1, table);
			}
		});
		if(!connector.isAdmin())
			btnNewButton_6.setVisible(false);

		
		Button btnNewButton_4 = new Button(composite, SWT.NONE);
		btnNewButton_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		btnNewButton_4.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnNewButton_4.setBounds(556, 29, 177, 25);
		btnNewButton_4.setText("Exit from client");
		btnNewButton_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				connector.setNewRequest(new Request(Requests.EXIT, null, null, null));
				getShell().close();
			}
		});
		
		Button btnChangeParser = new Button(composite, SWT.NONE);
		btnChangeParser.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnChangeParser.setBounds(556, 316, 177, 25);
		btnChangeParser.setText("Change Parser");
		btnChangeParser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ChangingParser.changingParserStart(connector);
			}
		});
		
		if(!connector.isAdmin())
			btnChangeParser.setVisible(false);
		createContents(connector);

		refreshing(connector, table_1, table);
		

	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents(ClientConnector connector) {
		setText("SWT Application");
		if (connector.isAdmin())
			setSize(1025, 450);
		else
			setSize(755, 410);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	static void setUpUsers(Vector<UserDescription> users, Table table) {
		Vector<TableItem> items = new Vector<TableItem>(users.size());
		table.removeAll();
		for (int i = 0; i < users.size(); i++) {
			items.add(new TableItem(table, SWT.NONE));
			items.get(i).setText(0, users.get(i).getLogin());
			items.get(i).setText(1, String.valueOf(users.get(i).getPassword()));
			items.get(i).setText(2, users.get(i).getName());
			items.get(i).setText(3, String.valueOf(users.get(i).getRole()));
			System.out.println(users.get(i).getLogin());
		}
	}

	static void setUpPersons(Vector<Person> persons, Table table) {
		Vector<TableItem> items = new Vector<TableItem>(persons.size());
		table.removeAll();
		for (int i = 0; i < persons.size(); i++) {
			items.add(new TableItem(table, SWT.NONE));
			items.get(i).setText(0, persons.get(i).getSurname());
			items.get(i).setText(1, persons.get(i).getName());
			items.get(i).setText(2, persons.get(i).getFathername());
			items.get(i).setText(3, persons.get(i).getPhone());
			items.get(i).setText(4, persons.get(i).getEmail());
			items.get(i).setText(5, persons.get(i).getEmployer());
			items.get(i).setText(6, persons.get(i).getJobExperience());
			items.get(i).setText(7, persons.get(i).getJobPosition());
			items.get(i).setText(8, persons.get(i).getSex());
		}
	}

	static void refreshing(final ClientConnector connector, Table userTable, Table personTable) {
		if (connector.isAdmin()) {
			Response response = connector.setNewRequest(new Request(Request.Requests.SHOW_USERS, null, null, null));
			if (response == null)
				System.exit(0);
			if (response.isSuccess())
				setUpUsers(response.getUsers(), userTable);
		}
		Response response = connector.setNewRequest(new Request(Request.Requests.SHOW_ALL, null, null, null));
		if (response == null)
			System.exit(0);
		if (response.isSuccess())
			setUpPersons(response.getPersons(), personTable);
	}

	public static Person getPersonFromTable(Table table) {
		int selected = table.getSelectionIndex();
		return new Person(table.getItem(selected).getText(0), table.getItem(selected).getText(1),
				table.getItem(selected).getText(2), table.getItem(selected).getText(3),
				table.getItem(selected).getText(4), table.getItem(selected).getText(5),
				table.getItem(selected).getText(6), table.getItem(selected).getText(7),
				table.getItem(selected).getText(8));
	}
}

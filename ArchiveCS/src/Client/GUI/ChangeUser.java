package client.gui;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.wb.swt.SWTResourceManager;

import client.ClientConnector;
import general.user.UserDescription;
import general.Request;
import general.Response;
import general.Request.Requests;

public class ChangeUser extends Shell {

	
	static ClientConnector connector;
	static String userLogin;
	
	
	static void setConnector(ClientConnector connector){
		ChangeUser.connector=connector;
	}
	
	static void setUserLogin(String userLogin){
		ChangeUser.userLogin=userLogin;
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void changeUserStart(Table table,Table table_1) {
		try {
			Display display = Display.getDefault();
		   Shell  shell = new ChangeUser(display, table, table_1);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	private ChangeUser(final Display display,final  Table table,final Table table_1) {
		super(display, SWT.SHELL_TRIM);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
		Label lblChooseUserRole = new Label(this, SWT.NONE);
		lblChooseUserRole.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblChooseUserRole.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblChooseUserRole.setForeground(SWTResourceManager.getColor(255, 255, 255));
		lblChooseUserRole.setBounds(158, 10, 124, 25);
		lblChooseUserRole.setText("Choose User Role");
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnNewButton.setBounds(38, 55, 75, 25);
		btnNewButton.setText("Admin");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e) {
			setNewRequestToChangeRole(UserDescription.Roles.ADMIN);	
			getShell().close();
			ClientWorkingWindow.refreshing(connector, table, table_1);
			}
		});
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setNewRequestToChangeRole(UserDescription.Roles.USER);
				getShell().close();
				ClientWorkingWindow.refreshing(connector, table,  table_1);
			}
		});
		btnNewButton_1.setBounds(177, 55, 75, 25);
		btnNewButton_1.setText("User");
		
		Button btnNewButton_2 = new Button(this, SWT.NONE);
		btnNewButton_2.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnNewButton_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setNewRequestToChangeRole(UserDescription.Roles.GUEST);
				getShell().close();
				ClientWorkingWindow.refreshing(connector, table, table_1);
			}
		});
		btnNewButton_2.setBounds(320, 55, 75, 25);
		btnNewButton_2.setText("Guest");
		
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 150);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	void setNewRequestToChangeRole(UserDescription.Roles role){
		Request request = new Request();
		request.setRequest(Requests.CHANGE_USERS);
		request.setUser(new UserDescription(role, userLogin, "null", "null"));
		Response responce = connector.setNewRequest(request);
		if (!responce.isSuccess()) {
			System.out.println("Ќе удалось изменить данные пользователей");
		}
	}
	
}

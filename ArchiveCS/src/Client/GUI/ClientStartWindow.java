package Client.GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import Client.ClientConnector;
import Server.User.UserRepository;

public class ClientStartWindow extends Shell {
	private Text text;
	private Text text_1;
	private ClientConnector connector;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			
			Display display = Display.getDefault();
			ClientStartWindow shell = new ClientStartWindow(display);
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
	public ClientStartWindow(final Display display) {
		super(display, SWT.SHELL_TRIM);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(226, 73, 94, 21);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(226, 116, 95, 21);
		
		Button btnNewButton_1 = new Button(this, SWT.NONE);
		btnNewButton_1.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnNewButton_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_1.setText("Enter");
		btnNewButton_1.setToolTipText("Click to Check Login and Password");
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// connecting to the server
				connector = new ClientConnector(text.getText(), text_1.getText());
				String userRole=connector.setConnection();
				if (userRole==null) {
					//Platform.exit();
					System.exit(0);
				}
				display.close();
				ClientWorkingWindow.workingWindowStart(userRole, connector);
				
			}
		});
		btnNewButton_1.setBounds(117, 155, 94, 25);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnNewButton.setToolTipText("Click to Exit");
		
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Exit button pressed");
			}
		});
		btnNewButton.setBounds(227, 155, 94, 25);
		btnNewButton.setText("Exit");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblNewLabel.setBounds(117, 73, 94, 21);
		lblNewLabel.setText("       Login");
		
		Label lblPassword = new Label(this, SWT.NONE);
		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL));
		lblPassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		lblPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		lblPassword.setBounds(117, 116, 94, 21);
		lblPassword.setText("    Password");
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Client");
		setSize(450, 300);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public static void showMessageBox(String string) {
		MessageBox alert = new MessageBox(null);
		/*alert.setTitle("Уведомление");
		alert.setHeaderText(null);
		alert.setContentText(string);
		// alert.initOwner((Stage) scene.getWindow());
		alert.showAndWait();*/
	}
}

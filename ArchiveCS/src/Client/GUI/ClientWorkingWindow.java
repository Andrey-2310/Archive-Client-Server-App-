package Client.GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;


import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;

public class ClientWorkingWindow extends Shell {
	private Table table;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		try {
			Display display = Display.getDefault();
			
			ClientWorkingWindow shell = new ClientWorkingWindow(display, null);
			//ClientWorkingWindow shell = new ClientWorkingWindow(display, args[0]);
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
	 * 
	 * @param display
	 */
	public ClientWorkingWindow(Display display, String userName) {
		super(display, SWT.SHELL_TRIM);
		setMinimumSize(new Point(170, 39));
		setBackground(SWTResourceManager.getColor(204, 204, 204));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		composite.setBounds(10, 10, 864, 214);

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 66, 673, 95);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnName_1 = new TableColumn(table, SWT.NONE);
		tblclmnName_1.setWidth(77);
		tblclmnName_1.setText("Name");

		TableColumn tblclmnSurname = new TableColumn(table, SWT.NONE);
		tblclmnSurname.setWidth(71);
		tblclmnSurname.setText("Surname");

		TableColumn tblclmnFathername = new TableColumn(table, SWT.NONE);
		tblclmnFathername.setWidth(82);
		tblclmnFathername.setText("Fathername");

		TableColumn tblclmnEmail = new TableColumn(table, SWT.NONE);
		tblclmnEmail.setWidth(94);
		tblclmnEmail.setText("Email");

		TableColumn tblclmnTelephone = new TableColumn(table, SWT.NONE);
		tblclmnTelephone.setWidth(100);
		tblclmnTelephone.setText("Telephone");

		TableColumn tblclmnEmployer = new TableColumn(table, SWT.NONE);
		tblclmnEmployer.setWidth(70);
		tblclmnEmployer.setText("Employer");

		TableColumn tblclmnExperience = new TableColumn(table, SWT.NONE);
		tblclmnExperience.setWidth(74);
		tblclmnExperience.setText("Experience");

		TableColumn tblclmnPosition = new TableColumn(table, SWT.NONE);
		tblclmnPosition.setWidth(62);
		tblclmnPosition.setText("Position");

		TableColumn tblclmnSex = new TableColumn(table, SWT.NONE);
		tblclmnSex.setWidth(39);
		tblclmnSex.setText("Sex");

		text = new Text(composite, SWT.BORDER);
		text.setBounds(289, 20, 315, 21);

		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(735, 66, 119, 25);
		btnNewButton.setText("New Button");

		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(735, 105, 119, 25);
		btnNewButton_1.setText("New Button");

		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setBounds(735, 145, 119, 25);
		btnNewButton_2.setText("New Button");

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setBounds(40, 20, 95, 21);
		if (userName != null) {
			lblNewLabel.setText("Hello " +userName);
			

		}
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(900, 449);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

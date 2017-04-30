package Client.GUI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import Client.ClientConnector;
import Server.ServerStart;
import Server.Communication.Request;
import Server.Communication.Request.Requests;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class ChangingParser extends Shell {

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void changingParserStart(ClientConnector connector) {
		try {
			Display display = Display.getDefault();
			ChangingParser shell = new ChangingParser(display, connector);
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
	public ChangingParser(Display display, final ClientConnector connector) {
		super(display, SWT.SHELL_TRIM);
		setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
		Label lblChangeParser = new Label(this, SWT.NONE);
		lblChangeParser.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		lblChangeParser.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		lblChangeParser.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
		lblChangeParser.setBounds(162, 20, 111, 20);
		lblChangeParser.setText("Change Parser");
		
		Button btnSax = new Button(this, SWT.NONE);
		btnSax.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnSax.setBounds(27, 61, 75, 25);
		btnSax.setText("SAX ");
		btnSax.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(connector.setNewRequest(new Request(Requests.CHANGE_PARSER, "SAX", null, null)).isSuccess())
					ServerStart.loggerServer.info(new String("Changing Parser  to SAX succeeded"));
				else 
					ServerStart.loggerServer.info(new String("Changing Parser to SAX didn't succeed"));
				getShell().close();
			}
		});
		
		Button btnStax = new Button(this, SWT.NONE);
		btnStax.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnStax.setBounds(121, 61, 75, 25);
		btnStax.setText("StAX");
		btnStax.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(connector.setNewRequest(new Request(Requests.CHANGE_PARSER, "StAX", null, null)).isSuccess())
					ServerStart.loggerServer.info(new String("Changing Parser  to StAX succeeded"));
				else 
					ServerStart.loggerServer.info(new String("Changing Parser to StAX didn't succeed"));
				getShell().close();
			}
		});
		
		Button btnDom = new Button(this, SWT.NONE);
		btnDom.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnDom.setBounds(215, 61, 75, 25);
		btnDom.setText("DOM");
		btnDom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(connector.setNewRequest(new Request(Requests.CHANGE_PARSER, "DOM", null, null)).isSuccess())
					ServerStart.loggerServer.info(new String("Changing Parser  to DOM succeeded"));
				else 
					ServerStart.loggerServer.info(new String("Changing Parser to DOM didn't succeed"));
				getShell().close();
			}
		});
		
		Button btnJdom = new Button(this, SWT.NONE);
		btnJdom.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnJdom.setBounds(314, 61, 75, 25);
		btnJdom.setText("JDOM");
		btnJdom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(connector.setNewRequest(new Request(Requests.CHANGE_PARSER, "JDOM", null, null)).isSuccess())
					ServerStart.loggerServer.info(new String("Changing Parser  to JDOM succeeded"));
				else 
					ServerStart.loggerServer.info(new String("Changing Parser to JDOM didn't succeed"));
				getShell().close();
			}
		});
		
		
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
}

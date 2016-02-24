package kr.or.kpew.kieas.issuer.view;

import java.awt.Component;
import java.awt.Container;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import kr.or.kpew.kieas.issuer.controller.AleterViewActionListener;
import kr.or.kpew.kieas.issuer.controller._AlerterController;
import kr.or.kpew.kieas.issuer.view.resource.AlertLogTableModel;


public class _AlerterTopView
{
	private _AlerterController controller;
	private AleterViewActionListener alerterActionListener;

	private AlertGeneratorPanel alerterCapGeneratePanel;
	private AlerterLogPanel alerterLogPanel;
//	private AlerterDataBasePanel alerterDatabasePanel;
//	private AlerterAlertGeneratePanel alerterAlertGeneratePanel;

	private JFrame mainFrame;
	private JTabbedPane mainTabbedPane;

	/**
	 * Main Frame과 각 포함되는 View Component 초기화.
	 * @param alerterActionListener 이벤트 리스너
	 */
	public _AlerterTopView(_AlerterController controller, AleterViewActionListener alerterActionListener)
	{
		initLookAndFeel();
		this.controller = controller;
		this.alerterActionListener = alerterActionListener;
		this.alerterCapGeneratePanel = new AlertGeneratorPanel(alerterActionListener);
		this.alerterLogPanel = new AlerterLogPanel(this, alerterActionListener);
//		this.alerterDatabasePanel = new AlerterDataBasePanel(alerterActionListener);
//		this.alerterAlertGeneratePanel = new AlerterAlertGeneratePanel(alerterActionListener);

		initFrame();
	}




	private void initFrame()
	{
		this.mainFrame = new JFrame();
		mainFrame.setSize(1024, 768);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.addWindowListener(alerterActionListener);

		this.mainTabbedPane = new JTabbedPane();
		Container container = mainFrame.getContentPane();
		container.add(mainTabbedPane);

		mainTabbedPane.addTab("CAP Generator Panel", alerterCapGeneratePanel.getPanel());
		mainTabbedPane.addTab("Alert Log Panel", alerterLogPanel.getPanel());			
//		mainTabbedPane.addTab("Database", alerterDatabasePanel.getPanel());	
//		mainTabbedPane.addTab("AlertGenerate", alerterAlertGeneratePanel.getPanel());

		mainFrame.setVisible(true);
	}

	private void initLookAndFeel()
	{
		try 
		{
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} 
		catch (UnsupportedLookAndFeelException e) 
		{
			e.printStackTrace();
		}
	}


	public void addInfoIndexPanel()
	{
		alerterCapGeneratePanel.addInfoIndexPanel();
	}

	public void addResourceIndexPanel()
	{
		alerterCapGeneratePanel.addResourceIndexPanel();
	}
	
	public void addAreaIndexPanel()
	{
		alerterCapGeneratePanel.addAreaIndexPanel();
	}	
	
	/**
	 * Model의 데이터 값이 바뀌었을 경우 View 갱신을 위해 Model에 의해 호출된다.
	 * 
	 * @param view 갱신되어야 하는 View 클래스 이름
	 * @param target 값이 표시되는 Component의 이름
	 * @param value 표시되는 값
	 */
	public void updateView(String view, String target, String value)
	{
		switch (view)
		{
		case "AlertGenerator":
			alerterCapGeneratePanel.updateView(target, value);
			break;
		case "AlertLogManager":
			alerterLogPanel.addAlertTableRow(value);
			break;
		default:
			System.out.println("there is no such a view " + view);
			break;
		}
	}
	
	public void updateView(String view, String target, List<String> value)
	{
		switch (view)
		{
		case "AlerterDataBasePanel":
//			alerterDatabasePanel.getQueryResult(value);
			break;
		default:
			System.out.println("there is no such a view " + view);
			break;
		}
	}

	public String getLoadTextField()
	{
		return alerterCapGeneratePanel.getLoadTextField();
	}

	public String getSaveTextField()
	{
		return alerterCapGeneratePanel.getSaveTextField();
	}

	public void setId(String name) 
	{
		mainFrame.setTitle(name + "발령대");
	}

	public Component getFrame() 
	{
		return this.mainFrame;
	}

	public void setTextArea(String message)
	{
		alerterLogPanel.setTextArea(message);
	}
	
	public AlertLogTableModel getAlertLogTableModel()
	{
		return controller.getAlertTableModel();
	}

	public String getAlertMessage(String identifier)
	{
		return controller.getAlertMessage(identifier);
	}

	public HashMap<String, String> getAlertElement()
	{
		return alerterCapGeneratePanel.getAlertElement();
	}

	public String getTextArea()
	{
		return alerterCapGeneratePanel.getTextArea();
	}
	
	public void addAlertTableRow(String message)
	{			
		alerterLogPanel.addAlertTableRow(message);
	}
	
//	public void applyAlertElement()
//	{
//		alerterCapGeneratePanel.applyAlertElement();
//	}

//	public void selectTableEvent()
//	{
//		alerterDatabasePanel.selectTableEvent();
//	}

//	public void getQueryResult(List<String> results)
//	{
//		System.out.println("topView getQueryResult");
//		alerterDatabasePanel.getQueryResult(results);
//	}

//	public String getQuery()
//	{
//		return alerterDatabasePanel.getQuery();
//	}
}
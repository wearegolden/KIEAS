package kr.or.kpew.kieas.issuer.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class AleterViewActionListener implements ActionListener, ListSelectionListener, WindowListener
{	
	private _AlerterController controller;
	
	
	public AleterViewActionListener(_AlerterController alerterController)
	{
		this.controller = alerterController;
	}
	
	/**
	 * event.getActionCommand()로 버튼 액션을 식별하여 처리한다.
	 * "Load Cap" : "~/cap/" 위치에 있는 지정된 이름의 Cap Draft를 로드한다.
	 * "Save Cap" : AlerterCapGeneratePanel.TextArea의 내용을 "/cap/" 위치에 지정된 이름으로 저장한다.
	 * "Apply" : AlerterCapGeneratePanel의 AlertPanel과 InfoPanel의 내용을 CapFormat으로 변환하여 TextArea에 적용한다.
	 * "Add Info" : AlerterCapGeneratePanel.InfoPanel에서 InfoIndexPanel을 추가한다.
	 * 
	 */
	public void actionPerformed(ActionEvent event)
	{
		String actionCommand = event.getActionCommand();
		System.out.println("action triggered : " + actionCommand);
		switch (actionCommand)
		{		
		case "Send":
			controller.sendMessage();
			return;
		case "Load Cap":
			controller.loadCap();
			return;
		case "Save Cap":
			controller.saveCap();
			return;
		case "Apply":
			controller.applyAlertElement();
			return;
		case "Add Info":
			controller.addInfoIndexPanel();
			return;
		case "Add Resource":
			controller.addResourceIndexPanel();
			return;
		case "Add Area":
			controller.addAreaIndexPanel();
			return;
		case "Register":
			controller.registerToGateway();
			return;
		case "Set Id":
			controller.setID();
			return;
		case "Clear":
			controller.setClear();
			return;
		default:
			System.out.println("There is no such a actionCommand : " + actionCommand);
			return;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
//		controller.selectTableEvent();
	}


	@Override
	public void windowActivated(WindowEvent e) {}


	@Override
	public void windowClosed(WindowEvent e) {}


	@Override
	public void windowClosing(WindowEvent e)
	{
		controller.systemExit();
	}


	@Override
	public void windowDeactivated(WindowEvent e) {}


	@Override
	public void windowDeiconified(WindowEvent e) {}


	@Override
	public void windowIconified(WindowEvent e) {}


	@Override
	public void windowOpened(WindowEvent e) {}
}
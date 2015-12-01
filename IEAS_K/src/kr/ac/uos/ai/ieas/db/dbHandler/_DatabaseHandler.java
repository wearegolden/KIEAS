package kr.ac.uos.ai.ieas.db.dbHandler;

import java.util.ArrayList;

import kr.ac.uos.ai.ieas.db.dbModel.CAPAlert;
import kr.ac.uos.ai.ieas.db.dbModel.DisasterEventType;
import kr.ac.uos.ai.ieas.alerter.alerterModel._AlerterModelManager;

public class _DatabaseHandler {

	private CAPDBUtils capDbUtils;
	private ArrayList<CAPAlert> searchResult;
	private CAPDBInsertUtils capDbInsertUtils;
	/**
	 * Database에 직접 접근하는 주체.
	 * Cap 포맷과 Database 접근을 관리하는 CapDbUtils 초기화.
	 * 
	 */
	public _DatabaseHandler()
	{
		this.capDbUtils = new CAPDBUtils();
		this.searchResult = new ArrayList<CAPAlert>();
		this.capDbInsertUtils = new CAPDBInsertUtils();
	}
	
	public void insertCap(CAPAlert alert)
	{
		capDbInsertUtils.insertCAP(alert);
	}	
	
	public ArrayList<CAPAlert> getQueryResult(String target, String value)
	{
		switch (target)
		{
		case _AlerterModelManager.EVENT_CODE:
			for (DisasterEventType disasterEventType : DisasterEventType.values()) 
			{
				if(disasterEventType.toString().equals(value))
				{
					searchResult = capDbUtils.searchCAPsByEventType(disasterEventType);					
				}
			}
			for (CAPAlert capAlert : searchResult)
			{
				System.out.println(capAlert.getStatus());
			}
			break;
		case _AlerterModelManager.STATUS:
			searchResult = capDbUtils.searchCAPsByStatus(value);
			break;
		default:
			break;
		}		

		return searchResult;
	}
	
}

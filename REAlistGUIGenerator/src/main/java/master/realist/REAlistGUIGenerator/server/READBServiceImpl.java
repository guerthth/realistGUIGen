package master.realist.REAlistGUIGenerator.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import master.realist.REAlistGUIGenerator.client.READBService;
import master.realist.REAlistGUIGenerator.server.daos.DualitytypeDAO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.util.SpringUtil;

public class READBServiceImpl extends RemoteServiceServlet implements READBService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4123094723186655175L;
	
	/**
	 * Service method returning the existing Dualitytypes in the REA DB
	 */
	public List<DualitytypeDTO> getDualitytypes() {

		DualitytypeDAO dualitytypehandler = (DualitytypeDAO) SpringUtil.context.getBean("dualitytypedao");
		return dualitytypehandler.getDualitytypeList();
	}


}

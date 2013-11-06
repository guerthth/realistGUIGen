package master.realist.REAlistGUIGenerator.server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import master.realist.REAlistGUIGenerator.client.READBService;
import master.realist.REAlistGUIGenerator.server.util.HibernateUtil;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.model.Dualitytype;

public class READBServiceImpl extends RemoteServiceServlet implements READBService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4123094723186655175L;
	
	@SuppressWarnings("unchecked")
	public List<DualitytypeDTO> getDualitytypes() {
		// TODO Auto-generated method stub
		Session session = null;
		List<Dualitytype> existingDualitytypes = null;
		List<DualitytypeDTO> dualitytypeDTOlist = null;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			existingDualitytypes = new ArrayList<Dualitytype>(session.createQuery("from Dualitytype").list());
			session.beginTransaction().commit();
		}catch(Exception e){
			
			if(session != null) {session.close();}	
		}
		
		if(existingDualitytypes != null){
			dualitytypeDTOlist = new ArrayList<DualitytypeDTO>();
			for(Dualitytype dt : existingDualitytypes){
				dualitytypeDTOlist.add(new DualitytypeDTO(dt.getId(),dt.getName(),dt.isIsConversion()));
			}
		}
		
		/**
		DualitytypeDTO ddto = new DualitytypeDTO("1","1",true);
		DualitytypeDTO ddto2 = new DualitytypeDTO("2","2",true);
		
		dualitytypeDTOlist.add(ddto);
		dualitytypeDTOlist.add(ddto2);
		**/
		
		return dualitytypeDTOlist;
	}


}

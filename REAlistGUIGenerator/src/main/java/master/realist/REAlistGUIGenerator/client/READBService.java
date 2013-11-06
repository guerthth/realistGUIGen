package master.realist.REAlistGUIGenerator.client;

import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reaDB")
public interface READBService extends RemoteService{
	
	public List<DualitytypeDTO> getDualitytypes();
}

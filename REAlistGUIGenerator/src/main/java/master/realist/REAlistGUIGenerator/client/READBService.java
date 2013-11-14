package master.realist.REAlistGUIGenerator.client;

import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("reaDB")
public interface READBService extends RemoteService{
	
	public List<DualitytypeDTO> getDualitytypes();
	
	public DualityDTO saveDuality(DualityDTO dualityDTO);
	
	public EventDTO saveEvent(EventDTO eventDTO);
	
	public EventHasAdditionalattributevalueDTO saveEventHasAdditionalattributevalue(EventHasAdditionalattributevalueDTO addattrvalDTO);
	
}

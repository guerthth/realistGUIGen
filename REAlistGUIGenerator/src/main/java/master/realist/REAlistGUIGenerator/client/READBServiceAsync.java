package master.realist.REAlistGUIGenerator.client;

import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.DualityDTO;
import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventDTO;
import master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface READBServiceAsync {

	void getDualitytypes(AsyncCallback<List<DualitytypeDTO>> callback);

	void saveDuality(DualityDTO dualityDTO,AsyncCallback<DualityDTO> callback);

	void saveEvent(EventDTO eventDTO, AsyncCallback<EventDTO> callback);
	
	void saveEventHasAdditionalattributevalue(EventHasAdditionalattributevalueDTO addattrvalDTO, AsyncCallback<EventHasAdditionalattributevalueDTO> callback);
}

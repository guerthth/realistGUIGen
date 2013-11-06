package master.realist.REAlistGUIGenerator.client;

import java.util.List;

import master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface READBServiceAsync {

	void getDualitytypes(AsyncCallback<List<DualitytypeDTO>> callback);

}

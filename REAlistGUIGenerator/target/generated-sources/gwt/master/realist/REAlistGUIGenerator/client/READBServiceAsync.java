package master.realist.REAlistGUIGenerator.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface READBServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void getDualitytypes( AsyncCallback<java.util.List<master.realist.REAlistGUIGenerator.shared.dto.DualitytypeDTO>> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void getDualityStatus( AsyncCallback<java.util.List<master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO>> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void saveDualityStatus( master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO dualityStatusDTO, AsyncCallback<master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void deleteDualityStatus( java.lang.Integer deleteId, AsyncCallback<java.lang.Integer> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void updateDualityStatus( master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO dualityStatusDTO, AsyncCallback<master.realist.REAlistGUIGenerator.shared.dto.DualityStatusDTO> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void getAgenttypes( AsyncCallback<java.util.List<master.realist.REAlistGUIGenerator.shared.dto.AgenttypeDTO>> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void getAgents( AsyncCallback<java.util.List<master.realist.REAlistGUIGenerator.shared.dto.AgentDTO>> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void saveAgent( master.realist.REAlistGUIGenerator.shared.dto.AgentDTO agentDTO, AsyncCallback<master.realist.REAlistGUIGenerator.shared.dto.AgentDTO> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void deleteAgent( java.lang.Integer agentId, AsyncCallback<java.lang.Integer> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void saveDuality( master.realist.REAlistGUIGenerator.shared.dto.DualityDTO dualityDTO, AsyncCallback<master.realist.REAlistGUIGenerator.shared.dto.DualityDTO> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void saveEvent( master.realist.REAlistGUIGenerator.shared.dto.EventDTO eventDTO, AsyncCallback<master.realist.REAlistGUIGenerator.shared.dto.EventDTO> callback );


    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see master.realist.REAlistGUIGenerator.client.READBService
     */
    void saveEventHasAdditionalattributevalue( master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO addattrvalDTO, AsyncCallback<master.realist.REAlistGUIGenerator.shared.dto.EventHasAdditionalattributevalueDTO> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static READBServiceAsync instance;

        public static final READBServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (READBServiceAsync) GWT.create( READBService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}

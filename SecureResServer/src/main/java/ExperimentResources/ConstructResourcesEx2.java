package ExperimentResources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.Response;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class ConstructResourcesEx2 
{
	public CoapResource[] construct(int machineNumber)
	{
		CoapResource[] res = new CoapResource[machineNumber];
		for(int i = 0; i < machineNumber; i++)
		{
			CoapResource inDemoResource  = new CoapResource("demoResource" + (i+1))
			{
				@Override
				public void handleGET(CoapExchange exchange)
				{
					System.out.println("Got something in GET.");
					exchange.respond(ResponseCode.CONTENT, "This is authorization server, currently it doesn't support GET.");
				}
						
				@Override
				public void handlePOST(CoapExchange exchange)
				{
					//System.out.println("response time1: " + System.currentTimeMillis());
					byte[] requestPayload = exchange.getRequestPayload();
					
					Response response = new Response(ResponseCode.CONTENT);
					response.setPayload(requestPayload);
					response.getOptions().setSize2(requestPayload.length);
					
					//System.out.println("response time2: " + System.currentTimeMillis());
					exchange.respond(response);
				}
			};
			
			res[i] = inDemoResource;
		}
		return res;
	}
}

package jmetermeasure.resources;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.security.PermitAll;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("jmeter")
@Api(value = "jmeter", description = "JMeter Test Resource")
@Singleton
public class TestResource {

	public List<PartialRequest> partialRequests;
	public static AtomicInteger globalCounter;
	
	public static class PartialRequest {
		
		public PartialRequest(int _currentCounter) {
			this.ts = new Date();
			this.currentCounter = _currentCounter;
		}
		
		public Date ts;
		public int currentCounter;
	}
	
	public static class InitializeTestResponse {
		public InitializeTestResponse() {};
		public String result;
	}

	public static class PartialResponse {
		public PartialResponse() {};
		public String result;
	}

	public static class EndTestResponse {
		public EndTestResponse() {};
		public List<PartialRequest> partialRequests;
		public double rpm;
		public String result;
	}
	
	
	
	@GET
	@Path("/init")
	@PermitAll
	@ApiOperation(value = "GET", notes = "Initialize variables", response = InitializeTestResponse.class)
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 500, message = "Internal Server Error") 
	})
	public InitializeTestResponse initializeTest() throws Exception {

		partialRequests = new ArrayList<PartialRequest>();
		globalCounter = new AtomicInteger(0);

		InitializeTestResponse resp = new InitializeTestResponse();
		resp.result = "reseted";
		return resp;
	}
	


	@GET
	@Path("/partial")
	@PermitAll
	@ApiOperation(value = "GET", notes = "Partial requests", response = PartialResponse.class)
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 500, message = "Internal Server Error") 
	})
	public PartialResponse partialRequest() throws Exception {
		
		int partialInt = globalCounter.incrementAndGet();
		partialRequests.add(new PartialRequest(partialInt));

		PartialResponse resp = new PartialResponse();
		resp.result = ("incremented: " + partialInt);
		return resp;
	}

	@GET
	@Path("/endResults")
	@PermitAll
	@ApiOperation(value = "GET", notes = "Get End Test Results", response = EndTestResponse.class)
	@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 500, message = "Internal Server Error") 
	})
	public EndTestResponse endTest() throws Exception {
		
		if (partialRequests.size() == 0) {
			EndTestResponse resp = new EndTestResponse();
			resp.result = "empty";
			return resp;
		}
		
		long startTime = partialRequests.get(0).ts.getTime();
		long endTime = partialRequests.get(partialRequests.size() - 1).ts.getTime();
		
		double rpm = (double) partialRequests.size() / ((double)(endTime - startTime)/(double)1000/(double)60);
		
		EndTestResponse resp = new EndTestResponse();
		resp.partialRequests = partialRequests;
		resp.result = "ok";
		resp.rpm = rpm;
		return resp;
	}
}
package jmetermeasure.resources;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;


@Path("jmeter")
@Api(value = "jmeter", description = "JMeter Test Resource")
public class TestResource {

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
		InitializeTestResponse resp = new InitializeTestResponse();
		resp.result = "ok";
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
		PartialResponse resp = new PartialResponse();
		resp.result = "ok";
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
		EndTestResponse resp = new EndTestResponse();
		resp.result = "ok";
		return resp;
	}
}
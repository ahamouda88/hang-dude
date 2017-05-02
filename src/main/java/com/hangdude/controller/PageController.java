package com.hangdude.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hangdude.model.board.HangdudeBoard;

@Path("/")
public class PageController {

//	@GET
//	public Response getMainPage() {
//		return Response.status(Response.Status.OK).build();
//	}

	@GET
	@Path("homepage")
	@Produces(MediaType.APPLICATION_JSON)
	public HangdudeBoard getPage() {
		return new HangdudeBoard();
	}
}

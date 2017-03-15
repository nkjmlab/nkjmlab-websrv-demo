package org.nkjmlab.webui.demo.jaxrs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.glassfish.jersey.server.mvc.Viewable;
import org.nkjmlab.util.json.JsonUtils;
import org.nkjmlab.webui.jaxrs.JaxrsView;

@Path("/")
public class DemoView extends JaxrsView {

	@Override
	public Viewable getView(String pathInfo, Map<String, String[]> params) {
		return getDefaultView(pathInfo, params);
	}

	@GET
	@Produces({ "application/x-msgpack" })
	@Path("add/msgpack")
	public Map<String, Integer> addAndGetMsgPack(@QueryParam("left") int left,
			@QueryParam("right") int right) {
		Map<String, Integer> result = new HashMap<>();
		result.put(left + "+" + right, left + right);
		return result;
	}

	@GET
	@Produces({ "application/json" })
	@Path("add/json")
	public String addAndGetJson(@QueryParam("left") int left, @QueryParam("right") int right) {
		Map<String, Integer> result = new HashMap<>();
		result.put(left + "+" + right, left + right);
		return JsonUtils.encode(result);
	}

}
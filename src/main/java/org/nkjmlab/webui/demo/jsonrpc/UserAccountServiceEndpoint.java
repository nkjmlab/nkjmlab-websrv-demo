package org.nkjmlab.webui.demo.jsonrpc;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.nkjmlab.webui.service.user.service.UserAccountService;

import jp.go.nict.langrid.servicecontainer.handler.annotation.Service;
import jp.go.nict.langrid.servicecontainer.handler.annotation.Services;

@WebServlet(urlPatterns = "/json/*", initParams = {
		@WebInitParam(name = "dumpRequests", value = "false"),
		@WebInitParam(name = "additionalResponseHeaders", value = "Access-Control-Allow-Origin: *") })

@Services({ @Service(name = "UserAccountService", impl = UserAccountService.class) })
public class UserAccountServiceEndpoint extends
		jp.go.nict.langrid.servicecontainer.handler.jsonrpc.servlet.JsonRpcServlet {

}

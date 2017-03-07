package org.nkjmlab.webui.demo.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;
import org.msgpack.unpacker.BufferUnpacker;
import org.nkjmlab.util.http.HttpClient;

public class MessagePackClientDemo {

	/**
	 * 	 curl -v -H "Accept: application/x-msgpack" \
	 * 		"http://localhost:8080/nkjmlab-websrv-demo/app/add/msgpack?left=100&right=300"
	 * 	 curl -v -H "Accept: application/json" \
	 * 		"http://localhost:8080/nkjmlab-websrv-demo/app/add/json?left=100&right=300"
	 */

	static String resourseRootPath = "http://localhost:8080/nkjmlab-websrv-demo/app";

	public static void main(String[] args) throws Exception {
		getVia("x-msgpack", "/add/msgpack?left=100&right=300");
		getVia("json", "/add/json?left=100&right=300");
	}

	private static void getVia(String type, String path) throws MalformedURLException {
		HttpClient client = new HttpClient();
		client.addHeader("Accept", "application/" + type);
		URL url = new URL(resourseRootPath + path);
		if (type.equals("x-msgpack")) {
			client.get(url,
					(response) -> {
						MessagePack msgpack = new MessagePack();
						try {
							BufferUnpacker unpacker = msgpack
									.createBufferUnpacker(
											EntityUtils.toByteArray(response.getEntity()));
							Map<String, Integer> deserialized = unpacker
									.read(Templates.tMap(Templates.TString, Templates.TInteger));
							System.out.println(deserialized);
						} catch (UnsupportedOperationException | IOException e) {
							e.printStackTrace();
						}
					});
		} else {
			client.get(
					url,
					(response) -> {
						try {
							String deserialized = EntityUtils.toString(response.getEntity());
							System.out.println(deserialized);
						} catch (ParseException | IOException e) {
							e.printStackTrace();
						}
					});
		}

	}
}
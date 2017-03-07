package org.nkjmlab.webui.demo.jaxrs;

import javax.ws.rs.ApplicationPath;

import org.nkjmlab.webui.jaxrs.JaxrsConfig;

/**
 * Application config class. add {@code @ApplicationPath} Annotation.
 *
 * @author nkjm
 *
 */
@ApplicationPath("/app")
public final class DemoJaxrsConfig extends JaxrsConfig {

}

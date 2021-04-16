package org.eclipse.scout.apps.contacts.shared.helloworld;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

/**
 * @author amdy
 */
@TunnelToServer
public interface IHelloWorldService extends IService {
	HelloWorldFormData load(HelloWorldFormData input);
}

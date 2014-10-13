package com.letv.app.sample.module;

import com.letv.app.sample.ILetvDemoAppContext;
import com.letv.app.sample.SampleConstants;
import com.letv.app.sample.service.IClientConfigService;
import com.letv.mobile.core.log.api.Trace;
import com.letv.mobile.core.microkernel.api.AbstractModule;

public class ClientConfigModule extends AbstractModule<ILetvDemoAppContext>
		implements IClientConfigService {
	private final static Trace log = Trace.register(ClientConfigModule.class);

	@Override
	public String getPCode() {
		return (String) context.getAttribute(SampleConstants.KEY_LETV_PCODE);
	}

	@Override
	public String getAppKey() {
		return (String) context.getAttribute(SampleConstants.KEY_LETV_APPKEY);
	}

	@Override
	public String getUmengChannel() {
		return (String) context.getAttribute(SampleConstants.KEY_UMENG_CHANNEL);
	}

	@Override
	protected void initServiceDependency() {

	}

	@Override
	protected void startService() {
		init();
		context.registerService(IClientConfigService.class, this);

	}

	@Override
	protected void stopService() {
		context.unregisterService(IClientConfigService.class, this);
	}

	private void init() {
		log.info("Loading config file...");
	}

	@Override
	public String getUrl(String urlKey) {
		// TODO Auto-generated method stub
		return null;
	}
}

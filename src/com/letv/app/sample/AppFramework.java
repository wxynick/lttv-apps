package com.letv.app.sample;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.Application;

import com.letv.app.sample.module.ClientConfigModule;
import com.letv.mobile.android.app.AndroidFramework;
import com.letv.mobile.android.app.IAndroidAppContext;
import com.letv.mobile.android.app.IAndroidFramework;
import com.letv.mobile.android.http.HttpRpcServiceModule;
import com.letv.mobile.android.preference.PreferenceManagerModule;
import com.letv.mobile.core.command.impl.CommandExecutorModule;
import com.letv.mobile.core.microkernel.api.AbstractModule;
import com.letv.mobile.core.rest.provider.GSONProvider;
import com.letv.mobile.core.rpc.rest.RestEasyClientModule;
import com.letv.mobile.core.util.StringUtils;

public class AppFramework extends AndroidFramework<ILetvDemoAppContext, AbstractModule<ILetvDemoAppContext>> {
	private final LetvDemoApplication app;
	IAndroidAppContext a;

	private class LetvDemoAppContextImpl extends AbstractContext implements ILetvDemoAppContext {
		private ExecutorService executor = Executors.newSingleThreadExecutor();

		@SuppressWarnings("rawtypes")
		@Override
		public IAndroidFramework getApplication() {
			return AppFramework.this;
		}

		@Override
		public void invokeLater(Runnable arg0) {
			runOnUIThread(arg0, 0, null);
		}

	};

	private LetvDemoAppContextImpl context;

	public AppFramework(LetvDemoApplication demoApplication) {
		this.app = demoApplication;
	}

	@Override
	public Application getAndroidApplication() {
		return app;
	}

	@Override
	public String getApplicationName() {
		return "letv demo";
	}

	protected ILetvDemoAppContext getContext() {
		if (context == null)
			context = new LetvDemoAppContextImpl();
		return context;
	}

	@Override
	protected void extractChannelInfo() {
		super.extractChannelInfo();
		String ch = (String) getContext().getAttribute(SampleConstants.KEY_PUBLISH_CHANNEL);
		if (!StringUtils.isBlank(ch)) {
			getContext().setAttribute(SampleConstants.KEY_UMENG_CHANNEL, ch);
			try {
				InputStream in = getAndroidApplication().getAssets().open("channel-maps.properties");
				Properties p = new Properties();
				p.load(in);
				String value = p.getProperty(ch);
				if (StringUtils.isNotBlank(value)) {
					if (value.contains("#")) {
						String[] chs = value.split("#");
						if (chs != null && chs.length == 2) {
							getContext().setAttribute(SampleConstants.KEY_LETV_APPKEY, chs[0]);
							getContext().setAttribute(SampleConstants.KEY_LETV_PCODE, chs[1]);
						}
					} else {
						getContext().setAttribute(SampleConstants.KEY_UMENG_CHANNEL, value);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public String getPChannel() {
		return super.getPChannel();
	}

	@Override
	public String getPChannel(String channelType) {
		return super.getPChannel(channelType);
	}

	@Override
	protected void initModules() {
		registerKernelModule(new PreferenceManagerModule<ILetvDemoAppContext>());

		RestEasyClientModule<ILetvDemoAppContext> rest = new RestEasyClientModule<ILetvDemoAppContext>();
		rest.getClient().register(GSONProvider.class);
		registerKernelModule(rest);

		CommandExecutorModule<ILetvDemoAppContext> cmdExecutor = new CommandExecutorModule<ILetvDemoAppContext>();
		registerKernelModule(cmdExecutor);

		HttpRpcServiceModule<ILetvDemoAppContext> m = new HttpRpcServiceModule<ILetvDemoAppContext>();
		m.setEnablegzip(false);
		m.setConnectionPoolSize(10);
		registerKernelModule(m);

		registerKernelModule(new ClientConfigModule());
	}

}

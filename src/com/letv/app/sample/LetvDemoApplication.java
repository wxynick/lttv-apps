/**
 * 
 */
package com.letv.app.sample;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;

import android.app.Application;
import android.os.Environment;

import com.letv.mobile.android.log.Log4jConfigurator;
import com.letv.mobile.core.log.spi.ILoggerFactory;
import com.letv.mobile.core.log.spi.Log4jLoggerFactory;

/**
 * @author wangxuyang1
 * 
 */
public class LetvDemoApplication extends Application {
	private Log4jConfigurator logConfig;
	private AppFramework framework;

	@Override
	public void onCreate() {
		ILoggerFactory.DefaultFactory.setLoggerFactory(new Log4jLoggerFactory());
		if (logConfig==null) {
			String log_file = Environment.getExternalStorageDirectory()
		            + File.separator + getPackageName() + File.separator + "logs"
		            + File.separator + "client.log";
			logConfig = new Log4jConfigurator();
			System.out.println("======log file location ===>"+log_file);
		}
		logConfig.configure();
		logConfig.setFilePattern("%d %-5p [%c{2}]-[%L] %m%n");
		super.onCreate();
		this.framework = new AppFramework(this);
		if (this.framework.isInDebugMode()) {
			logConfig.configureLogCatAppender("com.letv.mobile", Level.DEBUG);
			logConfig.configureFileAppender("com.letv.mobile", Level.DEBUG);			
		} else {
			logConfig.configureFileAppender("/", Level.WARN);
			logConfig.configureLogCatAppender("/", Level.WARN);
		}
		this.framework.startLater(1, TimeUnit.SECONDS);
	}


	@Override
	public void onTerminate() {
		if (this.framework != null) {
			this.framework.stop();
			this.framework = null;
		}
		super.onTerminate();
	}

	public AppFramework getFramework() {
		return this.framework;
	}

}

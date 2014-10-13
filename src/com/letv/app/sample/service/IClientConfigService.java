/**
 * 
 */
package com.letv.app.sample.service;

/**
 * @author wangxuyang1
 *
 */
public interface IClientConfigService {
	String getPCode();
	String getAppKey();
	String getUmengChannel();
	String getUrl(String urlKey);
}

package com.letv.app.sample.view;

import com.letv.app.sample.R;
import com.letv.app.sample.service.IClientConfigService;
import com.letv.mobile.android.app.AppUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PersonalMainFragment extends Fragment {
	private TextView umengView;
	private TextView appKeyView;
	private TextView pcodeView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.person_fragment, container, false);
		initView(view);
		return view;
	}

	private void initView(View parent) {
		appKeyView = (TextView) parent.findViewById(R.id.appkey_tv);
		pcodeView = (TextView) parent.findViewById(R.id.pcode_tv);
		umengView = (TextView) parent.findViewById(R.id.umeng_tv);
		appKeyView.setText(AppUtils.getService(IClientConfigService.class).getAppKey());
		pcodeView.setText(AppUtils.getService(IClientConfigService.class).getPCode());
		umengView.setText(AppUtils.getService(IClientConfigService.class).getUmengChannel());
	}

}

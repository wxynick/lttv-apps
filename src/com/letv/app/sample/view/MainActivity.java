package com.letv.app.sample.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.letv.app.sample.R;

public class MainActivity extends FragmentActivity implements OnCheckedChangeListener,OnPageChangeListener {
	private RadioGroup radioGroup;
	private ViewPager viewPager;
	private FragmentPagerAdapter fragAdapter;
	private List<Fragment> fragments;
	Random rand = new Random(4);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(this);
		viewPager = (ViewPager)findViewById(R.id.viewPager);
		fragments = new ArrayList<Fragment>();
		ChatMainFragment tab01 = new ChatMainFragment();
		ContactsMainFragment tab02 = new ContactsMainFragment();
		FindMainFragment tab03 = new FindMainFragment();
		PersonalMainFragment tab04 = new PersonalMainFragment();
		fragments.add(tab01);
		fragments.add(tab02);
		fragments.add(tab03);
		fragments.add(tab04);
		
		fragAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				return fragments.size();
			}
			
			@Override
			public Fragment getItem(int pos) {
				return fragments.get(pos);
			}
		};
		
		viewPager.setAdapter(fragAdapter);
		
		viewPager.setOnPageChangeListener(this);
		
		/*int pos = rand.nextInt(4);
		if (pos<0) {
			pos=0;
		}
		pos = pos%4;
		onPageSelected(pos);*/
	}

	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
		viewPager.setCurrentItem(getCheckedPosition(checkedId));
		Log.w("MENU",radioGroup.getCheckedRadioButtonId()+ "===="+checkedId+",view pagerId:"+viewPager.getCurrentItem());
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int pos) {
		resetRadioGroup(pos);
	}
	
	private int getCheckedPosition(int checkedId){
		int ret = 0;
		for (int i = 0; i < radioGroup.getChildCount(); i++) {
			if (radioGroup.getChildAt(i).getId()==checkedId) {
				ret= i;
				break;
			}
		}
		return ret;
	}

	private void resetRadioGroup(int pos) {
		RadioButton btn =  null;
		for (int i = 0; i < radioGroup.getChildCount(); i++) {
			 btn =  (RadioButton)radioGroup.getChildAt(i);
			 btn.setTextColor(Color.GRAY);
			 btn.setChecked(false);
		}
		btn =  (RadioButton)radioGroup.getChildAt(pos);
		if (btn!=null) {
			btn.setTextColor(Color.parseColor("#008000"));
			btn.setChecked(true);
		}
		
	}
	

}

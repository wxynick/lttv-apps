package com.letv.app.sample.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.letv.app.sample.R;

public class ChatMainFragment extends Fragment {
	private ListView listView;
	private ListAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.chat_list, container, false);
		listView = (ListView)view.findViewById(R.id.chatlist);
		adapter = new SimpleAdapter(this.getActivity(), getData(), R.layout.chat_list_item, new String[]{"image","title","content"}, 
				new int[] {R.id.chat_iv,R.id.chat_tv_title,R.id.chat_tv_content});
		listView.setAdapter(adapter);
		return view;
	}
	
	List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();

	private List<Map<String, Object>> getData(){
		data.clear();
		for (int i = 0; i < 10; i++) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("image", R.drawable.ic_launcher);
			map.put("title", "title"+i);
			map.put("content", "content"+i);
			data.add(map);
		}
		return data;
	}
}

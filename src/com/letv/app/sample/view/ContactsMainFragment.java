package com.letv.app.sample.view;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.Contacts.PeopleColumns;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.letv.app.sample.R;

public class ContactsMainFragment extends Fragment {
	private ListView listView;
	private ListAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.contacts_list, container, false);
		listView = (ListView)view.findViewById(R.id.contact_list);
		Cursor c = getActivity().getContentResolver().query(People.CONTENT_URI,
				null,
				null,
				null, null);
		adapter = new SimpleCursorAdapter(this.getActivity(), R.layout.contact_list_item, c,new String[]{People.NAME,People.PRIMARY_PHONE_ID}, 
				new int[] {R.id.contact_tv_name,R.id.contact_tv_memo});
		listView.setAdapter(adapter);
		return view;
	}

	

}

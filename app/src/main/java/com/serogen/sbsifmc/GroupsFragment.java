package com.serogen.sbsifmc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class GroupsFragment extends Fragment {

        public static GroupsFragment newInstance() {
            return new GroupsFragment();
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_groups,container,false);
            ((MainActivity)getActivity()).setWindowTitle(getString(R.string.menu_categories));

            GroupsListAdapter adapter = new GroupsListAdapter(getActivity(),initList());

            final GridView gridView = view.findViewById(R.id.gridView);
            gridView.setAdapter(adapter);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GroupItem item = (GroupItem) gridView.getItemAtPosition(position);

                    ((MainActivity)getActivity()).onGroupClicked(item.getId());
                }
            });

            return view;
        }

    private ArrayList<GroupItem> initList() {
        ArrayList<GroupItem> list = new ArrayList<>();

        list.add(new GroupItem(R.drawable.z36,getString(R.string.group_houses),1));
        list.add(new GroupItem(R.drawable.g32,getString(R.string.group_medieval_houses),2));
        list.add(new GroupItem(R.drawable.e21,getString(R.string.group_fountains),3));
        list.add(new GroupItem(R.drawable.f11,getString(R.string.group_outdoor_decorations),4));
        list.add(new GroupItem(R.drawable.l7,getString(R.string.group_house_decorations),5));
        list.add(new GroupItem(R.drawable.o23,getString(R.string.group_other_buildings),6));

        return list;
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }
        ((MainActivity)getActivity()).showCountAd();
    }
}
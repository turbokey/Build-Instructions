package com.serogen.sbsifmc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class InstructionsListFragment extends ListFragment {
//((MainActivity)getActivity())
    EditText searchEditText;

    public static InstructionsListFragment newInstance(int ID) {
        Bundle b = new Bundle();
        b.putInt("group_id",ID);
        InstructionsListFragment f = new InstructionsListFragment();
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_instructionslist,container,false);

        searchEditText = view.findViewById(R.id.search_editText);

        Bundle bundle = getArguments();
        int ID = bundle.getInt("group_id");
        setWindowTitle(ID);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int ID = bundle.getInt("group_id");
        final InstructionListAdapter adapter = new InstructionListAdapter(getActivity(),R.layout.instruction_list_item,initList(ID));
        setListAdapter(adapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s.toString());
            }
        });
    }
    private ArrayList<InstructionItemFromInstructionsList> initList(int id) {
        ArrayList<InstructionItemFromInstructionsList> list = new ArrayList<>();
        switch (id) {
            case MainActivity.ID_GROUP_ALL:
                list.add(new InstructionItemFromInstructionsList(R.drawable.a21,getString(R.string.A_instruction_label),false,0));
                list.add(new InstructionItemFromInstructionsList(R.drawable.b23,getString(R.string.B_instruction_label),false,1));
                list.add(new InstructionItemFromInstructionsList(R.drawable.c21,getString(R.string.C_instruction_label),false,2));
                list.add(new InstructionItemFromInstructionsList(R.drawable.d18,getString(R.string.D_instruction_label),false,3));
                list.add(new InstructionItemFromInstructionsList(R.drawable.e21,getString(R.string.E_instruction_label),false,4));
                list.add(new InstructionItemFromInstructionsList(R.drawable.f11,getString(R.string.F_instruction_label),true,5));
                list.add(new InstructionItemFromInstructionsList(R.drawable.g32,getString(R.string.G_instruction_label),false,6));
                list.add(new InstructionItemFromInstructionsList(R.drawable.h10,getString(R.string.H_instruction_label),false,7));
                list.add(new InstructionItemFromInstructionsList(R.drawable.i4,getString(R.string.I_instruction_label),true,8));
                list.add(new InstructionItemFromInstructionsList(R.drawable.j3,getString(R.string.J_instruction_label),true,9));
                list.add(new InstructionItemFromInstructionsList(R.drawable.k4,getString(R.string.K_instruction_label),true,10));
                list.add(new InstructionItemFromInstructionsList(R.drawable.l7,getString(R.string.L_instruction_label),true,11));
                list.add(new InstructionItemFromInstructionsList(R.drawable.m2,getString(R.string.M_instruction_label),true,12));
                list.add(new InstructionItemFromInstructionsList(R.drawable.n3,getString(R.string.N_instruction_label),true,13));
                list.add(new InstructionItemFromInstructionsList(R.drawable.o23,getString(R.string.O_instruction_label),true,14));
                list.add(new InstructionItemFromInstructionsList(R.drawable.p28,getString(R.string.P_instruction_label),false,15));
                list.add(new InstructionItemFromInstructionsList(R.drawable.q14,getString(R.string.Q_instruction_label),true,16));
                list.add(new InstructionItemFromInstructionsList(R.drawable.z36,getString(R.string.Z_instruction_label),false,25));
                break;
            case MainActivity.ID_GROUP_HOUSES:
                list.add(new InstructionItemFromInstructionsList(R.drawable.a21,getString(R.string.A_instruction_label),false,0));
                list.add(new InstructionItemFromInstructionsList(R.drawable.b23,getString(R.string.B_instruction_label),false,1));
                list.add(new InstructionItemFromInstructionsList(R.drawable.q14,getString(R.string.Q_instruction_label),true,16));
                list.add(new InstructionItemFromInstructionsList(R.drawable.z36,getString(R.string.Z_instruction_label),false,25));
                break;
            case MainActivity.ID_GROUP_MEDIEVAL_HOUSES:
                list.add(new InstructionItemFromInstructionsList(R.drawable.d18,getString(R.string.D_instruction_label),false,3));
                list.add(new InstructionItemFromInstructionsList(R.drawable.g32,getString(R.string.G_instruction_label),false,6));
                list.add(new InstructionItemFromInstructionsList(R.drawable.p28,getString(R.string.P_instruction_label),false,15));
                break;
            case MainActivity.ID_GROUP_FOUNTAINS:
                list.add(new InstructionItemFromInstructionsList(R.drawable.e21,getString(R.string.E_instruction_label),false,4));
                list.add(new InstructionItemFromInstructionsList(R.drawable.h10,getString(R.string.H_instruction_label),false,7));
                break;
            case MainActivity.ID_GROUP_OUTDOOR_DECORATIONS:
                list.add(new InstructionItemFromInstructionsList(R.drawable.f11,getString(R.string.F_instruction_label),true,5));
                list.add(new InstructionItemFromInstructionsList(R.drawable.m2,getString(R.string.M_instruction_label),true,12));
                break;
            case MainActivity.ID_GROUP_HOUSE_DECORATIONS:
                list.add(new InstructionItemFromInstructionsList(R.drawable.i4,getString(R.string.I_instruction_label),true,8));
                list.add(new InstructionItemFromInstructionsList(R.drawable.j3,getString(R.string.J_instruction_label),true,9));
                list.add(new InstructionItemFromInstructionsList(R.drawable.k4,getString(R.string.K_instruction_label),true,10));
                list.add(new InstructionItemFromInstructionsList(R.drawable.l7,getString(R.string.L_instruction_label),true,11));
                list.add(new InstructionItemFromInstructionsList(R.drawable.n3,getString(R.string.N_instruction_label),true,13));
                break;
            case MainActivity.ID_GROUP_OTHER:
                list.add(new InstructionItemFromInstructionsList(R.drawable.c21,getString(R.string.C_instruction_label),false,2));
                list.add(new InstructionItemFromInstructionsList(R.drawable.o23,getString(R.string.O_instruction_label),true,14));
                break;
        }
        return list;
    }
    private void setWindowTitle(int ID) {
        switch (ID) {
            case MainActivity.ID_GROUP_ALL:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.group_all));
                break;
            case MainActivity.ID_GROUP_HOUSES:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.group_houses));
                break;
            case MainActivity.ID_GROUP_MEDIEVAL_HOUSES:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.group_medieval_houses));
                break;
            case MainActivity.ID_GROUP_FOUNTAINS:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.group_fountains));
                break;
            case MainActivity.ID_GROUP_OUTDOOR_DECORATIONS:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.group_outdoor_decorations));
                break;
            case MainActivity.ID_GROUP_HOUSE_DECORATIONS:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.group_house_decorations));
                break;
            case MainActivity.ID_GROUP_OTHER:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.group_other_buildings));
                break;
        }
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
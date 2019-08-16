package com.serogen.sbsifmc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class InstructionFragment extends ListFragment {

    public static InstructionFragment newInstance(int ID) {
        Bundle b = new Bundle();
        b.putInt("instruction_id",ID);
        InstructionFragment f = new InstructionFragment();
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_instruction,container,false);

        Bundle bundle = getArguments();
        int ID = bundle.getInt("instruction_id");
        setWindowTitle(ID);



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int ID = bundle.getInt("instruction_id");
        InstructionArrayAdapter adapter = new InstructionArrayAdapter(getActivity(),R.layout.instruction_item,initInstruction(ID));
        setListAdapter(adapter);
    }

    private ArrayList<InstructionItem> initInstruction(int id) {
        ArrayList<InstructionItem> instruction = new ArrayList<>();
        String letter = "";
        switch (id) {
            case 0:
                letter = "A";
                break;
            case 1:
                letter = "B";
                break;
            case 2:
                letter = "C";
                break;
            case 3:
                letter = "D";
                break;
            case 4:
                letter = "E";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.E_instruction_label));
                break;
            case 5:
                letter = "F";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.F_instruction_label));
                break;
            case 6:
                letter = "G";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.G_instruction_label));
                break;
            case 7:
                letter = "H";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.H_instruction_label));
                break;
            case 8:
                letter = "I";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.I_instruction_label));
                break;
            case 9:
                letter = "J";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.J_instruction_label));
                break;
            case 10:
                letter = "K";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.K_instruction_label));
                break;
            case 11:
                letter = "L";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.L_instruction_label));
                break;
            case 12:
                letter = "M";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.M_instruction_label));
                break;
            case 13:
                letter = "N";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.N_instruction_label));
                break;
            case 14:
                letter = "O";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.O_instruction_label));
                break;
            case 15:
                break;
            case 16:
                letter = "Q";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.Q_instruction_label));
                break;
            case 17:
                break;
            case 18:
                break;
            case 19:
                break;
            case 20:
                break;
            case 21:
                break;
            case 22:
                break;
            case 23:
                break;
            case 24:
                break;
            case 25:
                letter = "Z";
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.Z_instruction_label));
                break;
            default:
                letter = "";
                break;
        }
        String[] resArrray = getResources().getStringArray(getResources().getIdentifier(letter+"_instruction","array",getActivity().getPackageName()));
        for (int i = 0; i < resArrray.length; i++) {
            instruction.add(new InstructionItem(getResources().getIdentifier(letter.toLowerCase()+""+i,"drawable",getActivity().getPackageName()),resArrray[i]));
        }
        return instruction;
    }

    private void setWindowTitle(int ID) {
        switch (ID) {
            case 0:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.A_instruction_label));
                break;
            case 1:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.B_instruction_label));
                break;
            case 2:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.C_instruction_label));
                break;
            case 3:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.D_instruction_label));
                break;
            case 4:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.E_instruction_label));
                break;
            case 5:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.F_instruction_label));
                break;
            case 6:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.G_instruction_label));
                break;
            case 7:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.H_instruction_label));
                break;
            case 8:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.I_instruction_label));
                break;
            case 9:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.J_instruction_label));
                break;
            case 10:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.K_instruction_label));
                break;
            case 11:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.L_instruction_label));
                break;
            case 12:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.M_instruction_label));
                break;
            case 13:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.N_instruction_label));
                break;
            case 14:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.O_instruction_label));
                break;
            case 15:
                break;
            case 16:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.Q_instruction_label));
                break;
            case 17:
                break;
            case 18:
                break;
            case 19:
                break;
            case 20:
                break;
            case 21:
                break;
            case 22:
                break;
            case 23:
                break;
            case 24:
                break;
            case 25:
                ((MainActivity)getActivity()).setWindowTitle(getString(R.string.Z_instruction_label));
                break;
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        InstructionItem item = (InstructionItem) getListAdapter().getItem(position);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), item.getImageResourceId());

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);

        File f = new File(getActivity().getCacheDir().getAbsolutePath()
                + File.separator + "image.png");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri path = FileProvider.getUriForFile(getActivity(), getActivity().getPackageName() + ".com.serogen.sbsifmc.provider", f);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(path, "image/*");
        startActivity(intent);
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
package com.example.cmpt276project.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.cmpt276project.R;
import com.example.cmpt276project.model.Children;
import com.example.cmpt276project.model.ChildrenAdapter;

public class EditChildPopup extends AppCompatDialogFragment {
    private Children children;
    private int position;
    private ChildrenAdapter childrenAdapter;
    private EditText editChildName;

    public EditChildPopup(Children children, int position, ChildrenAdapter childrenAdapter) {
        this.children = children;
        this.position = position;
        this.childrenAdapter = childrenAdapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.edit_child_popup, null);

        editChildName = v.findViewById(R.id.editchildname);
        editChildName.append(children.getChild(position));

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editChildName(children, position);
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .setNegativeButton(android.R.string.cancel, null)
                .setTitle(R.string.EditChildName)
                .create();
    }

    public void editChildName(Children children, int position) {
        children.editChild(position, editChildName.getText().toString());
        childrenAdapter.notifyDataSetChanged();
    }
}

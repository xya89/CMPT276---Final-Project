package com.example.cmpt276project.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.cmpt276project.R;
import com.example.cmpt276project.model.TaskManager;
import com.example.cmpt276project.model.WhoseTurnAdapter;

public class TaskPopUpWindow extends AppCompatDialogFragment {
    // Initialize variables
    private TaskManager taskManager;
    private int position;
    private WhoseTurnAdapter whoseTurnAdapter;

    // Constructor
    public TaskPopUpWindow(TaskManager taskManager, int position, WhoseTurnAdapter whoseTurnAdapter) {
        this.taskManager = taskManager;
        this.position = position;
        this.whoseTurnAdapter = whoseTurnAdapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Inflate the layout for the dialog
        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_window, null);
        final PopupWindow popupWindow = new PopupWindow(popupView,400,400,true);
        Button btnClose = popupView.findViewById(R.id.pop_btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View popupView) {
                //Toast.makeText(popupView.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        setValues(popupView);

        return new AlertDialog.Builder(getActivity())
                .setView(popupView)
                .create();
    }

    public void setValues(final View v) {
        TextView taskName = v.findViewById(R.id.pop_tv_task_name);
        TextView childName = v.findViewById(R.id.pop_tv_child_name);
        TextView taskDesc = v.findViewById(R.id.pop_tv_task_desc_title);
        ImageView childImage = v.findViewById(R.id.pop_tv_child_image);



        taskName.setText(taskManager.getTask(position).getTaskName());
        childName.setText(taskManager.getTask(position).getChild());
        taskDesc.setText(taskManager.getTask(position).getTaskDescription());
        if (taskDesc.getText().equals("")) {
            taskDesc.setText(R.string.NoDesc);
        }

    }

}

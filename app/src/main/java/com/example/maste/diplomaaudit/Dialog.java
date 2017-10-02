package com.example.maste.diplomaaudit;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class Dialog extends DialogFragment implements View.OnClickListener {

    /*
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState, final Context context){
        getDialog().setTitle("Enterprise name");
        View dialogView = inflater.inflate(R.layout.dialog, null);
        dialogView.findViewById(R.id.btnYes).setOnClickListener(this);
        dialogView.findViewById(R.id.btnNo).setOnClickListener(this);
        dialogView.findViewById(R.id.btnMaybe).setOnClickListener(this);

        dialogView.findViewById(R.id.dialog_next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "NEXT BUTTON pressed", Toast.LENGTH_SHORT).show();
            }
        });
        dialogView.findViewById(R.id.dialog_delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "DELETE BUTTON pressed", Toast.LENGTH_SHORT).show();
            }
        });
        return dialogView;
    }
    */




    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}

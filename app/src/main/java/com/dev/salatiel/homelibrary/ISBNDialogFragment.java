package com.dev.salatiel.homelibrary;


import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class ISBNDialogFragment extends DialogFragment {

    public final static String TAG = "ISBN_DIALOG";

    public ISBNDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.fragment_isbndialog);
        builder.setMessage(R.string.isbn_sigla);
        builder.setPositiveButton(getString(R.string.enviar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }

}

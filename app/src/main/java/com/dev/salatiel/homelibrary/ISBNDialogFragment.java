package com.dev.salatiel.homelibrary;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.dev.salatiel.interfaces.ICustomDataListener;

public class ISBNDialogFragment extends DialogFragment {

    public final static String TAG = "ISBN_DIALOG";
    EditText txtISBN;

    public ISBNDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater i = getActivity().getLayoutInflater();
        View v = i.inflate(R.layout.fragment_isbndialog, null);
        txtISBN =   v.findViewById(R.id.edtISBNFragment);

        AlertDialog.Builder b =  new  AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.isbn_sigla))
                .setPositiveButton(getString(R.string.enviar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ICustomDataListener listener = (ICustomDataListener)getActivity();
                                listener.onDataLoaded(txtISBN.getText().toString());
                            }
                        }
                )
                .setNegativeButton(getString(R.string.cancelar),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );
        b.setView(v);
        return b.create();
    }
}

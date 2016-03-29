package com.example.pepo.proyecto1;

/**
 * Created by Pepo on 17/11/2015.
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertDialogFragment extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle("GAME OVER");
        alertDialogBuilder.setMessage("Desea jugar otra partida?");
        alertDialogBuilder.setPositiveButton("Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        main.restartActivity(getActivity());
                        main.dibujarTablero();
                        dialog.dismiss();
                    }
                });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        main.finish();
                        dialog.dismiss();
                    }
                });
        return alertDialogBuilder.create();

    }//fin del metodo onCreateDialog
}//fin de la clase
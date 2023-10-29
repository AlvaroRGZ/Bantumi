package es.upm.miw.bantumi.dialog;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.DialogFragment;

import es.upm.miw.bantumi.JuegoBantumi;
import es.upm.miw.bantumi.MainActivity;
import es.upm.miw.bantumi.R;

public class RestartDialog extends DialogFragment {

    @NonNull
    @Override
	public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
		final MainActivity main = (MainActivity) requireActivity();

        assert main != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(main);
        builder
                .setTitle(R.string.txtDialogoReiniciarTitulo)
                .setMessage(R.string.txtDialogoReiniciarPregunta)
                .setPositiveButton(
                        getString(android.R.string.ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                main.juegoBantumi.inicializar(JuegoBantumi.Turno.turnoJ1);
                            }
                        }
                )
                .setNegativeButton(
                        getString(android.R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Does nothing, keeps playing
                            }
                        }
                );

		return builder.create();
	}
}

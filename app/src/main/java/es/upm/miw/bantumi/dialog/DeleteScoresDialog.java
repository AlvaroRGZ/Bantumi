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
import es.upm.miw.bantumi.ScoreActivity;

public class DeleteScoresDialog extends DialogFragment {

    @NonNull
    @Override
	public AppCompatDialog onCreateDialog(Bundle savedInstanceState) {
        final ScoreActivity scoreActivity = (ScoreActivity) requireActivity();

        assert scoreActivity != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(scoreActivity);
        builder
                .setTitle(R.string.txtDialogoReiniciarTitulo)
                .setMessage(R.string.txtDialogoReiniciarPregunta)
                .setPositiveButton(
                        getString(android.R.string.ok),
                        (dialog, which) -> scoreActivity.deleteAllScores()
                )
                .setNegativeButton(
                        getString(android.R.string.cancel),
                        (dialog, which) -> {
                            // Does nothing, keeps playing
                        }
                );

		return builder.create();
	}
}

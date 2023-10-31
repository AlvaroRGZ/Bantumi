package es.upm.miw.bantumi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.upm.miw.bantumi.dialog.DeleteScoresDialog;
import es.upm.miw.bantumi.view.ScoreListAdapter;
import es.upm.miw.bantumi.view.ScoreViewModel;

public class ScoreActivity extends AppCompatActivity {
    private ScoreViewModel mScoreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_scores);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ScoreListAdapter adapter = new ScoreListAdapter(new ScoreListAdapter.ScoreDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mScoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);
        mScoreViewModel.getAllScores().observe(this, scores -> {
            adapter.submitList(scores);
        });

        Button borrar = findViewById(R.id.btnBorrar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteScoresDialog().show(getSupportFragmentManager(), "ALERT_DIALOG");
            }
        });

        Button atras = findViewById(R.id.btnAtras);
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void deleteAllScores() {
        new ViewModelProvider(this).get(ScoreViewModel.class).deleteAll();
    }
}

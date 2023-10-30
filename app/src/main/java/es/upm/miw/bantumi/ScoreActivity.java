package es.upm.miw.bantumi;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        // Use ViewModelProvider to associate your ViewModel with your Activity
        mScoreViewModel = new ViewModelProvider(this).get(ScoreViewModel.class);

        // add an observer for the LiveDatas
        mScoreViewModel.getAllScores().observe(this, scores -> {
            // Update the cached copy of the scores in the adapter.
            adapter.submitList(scores);
        });
    }

}

package es.upm.miw.bantumi.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.upm.miw.bantumi.R;
import es.upm.miw.bantumi.ScoreActivity;
import es.upm.miw.bantumi.model.Score;

public class ScoreViewHolder extends RecyclerView.ViewHolder {
    //private final TextView date;
    private  TextView namePlayer1;
    private TextView scorePlayer1;
    //private final TextView namePlayer2;
    //private final TextView scorePlayer2;

    private ScoreViewHolder(View itemView) {
        super(itemView);
        //date = itemView.findViewById(R.id.score_date);
        namePlayer1 = itemView.findViewById(R.id.score_player1Name);
        //scorePlayer1 = itemView.findViewById(R.id.score_player1Score);
        //namePlayer2 = itemView.findViewById(R.id.score_player2Name);
        //scorePlayer2 = itemView.findViewById(R.id.score_player2Score);
    }

    public void bind(Score score) {
        // Log.i("Datos", score.getMPlayer1() + score.getMScore1() + score.getMPlayer2() + score.getMScore2());
        //date.setText(score.getMDate());
        namePlayer1.setText(score.getMPlayer1() + "   " + score.getMScore1() + "  -  " + score.getMScore2() + "   "+ score.getMPlayer2());
        // scorePlayer1.setText(score.getMScore1());
        //namePlayer2.setText(score.getMPlayer2());
        //scorePlayer2.setText(score.getMScore2());
    }

    static ScoreViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_score, parent, false);
        return new ScoreViewHolder(view);
    }
}
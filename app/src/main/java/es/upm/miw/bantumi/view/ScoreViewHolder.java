package es.upm.miw.bantumi.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import es.upm.miw.bantumi.R;

public class ScoreViewHolder extends RecyclerView.ViewHolder {
    private final TextView scoreItemView;

    private ScoreViewHolder(View itemView) {
        super(itemView);
        scoreItemView = itemView.findViewById(R.id.score_date);
    }

    public void bind(String text) {
        scoreItemView.setText(text);
    }

    static ScoreViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_score, parent, false);
        return new ScoreViewHolder(view);
    }
}
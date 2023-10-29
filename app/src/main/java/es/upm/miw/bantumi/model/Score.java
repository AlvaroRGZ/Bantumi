package es.upm.miw.bantumi.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Entity(tableName = "score_table")
public class Score {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    private String mDate;

    @NonNull
    @ColumnInfo(name = "player1")
    private String mPlayer1;

    @NonNull
    @ColumnInfo(name = "score1")
    private Integer mScore1;

    @NonNull
    @ColumnInfo(name = "player2")
    private String mPlayer2;

    @NonNull
    @ColumnInfo(name = "score2")
    private Integer mScore2;

    @NonNull
    @ColumnInfo(name = "win")
    private Boolean mWin;

    public Score(@NonNull String player1, @NonNull Integer score1,
                 @NonNull String player2, @NonNull Integer score2,
                 @NonNull Boolean win) {
        this.mDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).toString();
        this.mPlayer1 = player1;
        this.mScore1 = score1;
        this.mPlayer2 = player2;
        this.mScore2 = score2;
        this.mWin = win;
    }
}

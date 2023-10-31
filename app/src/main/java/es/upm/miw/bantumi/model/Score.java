package es.upm.miw.bantumi.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

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

    public Score(@NonNull String date,
                @NonNull String player1, @NonNull Integer score1,
                 @NonNull String player2, @NonNull Integer score2,
                 @NonNull Boolean win) {
        this.mDate = date;
        this.mPlayer1 = player1;
        this.mScore1 = score1;
        this.mPlayer2 = player2;
        this.mScore2 = score2;
        this.mWin = win;
    }

    @NonNull
    public String getMDate() {
        return mDate;
    }

    public void setMDate(@NonNull String mDate) {
        this.mDate = mDate;
    }

    @NonNull
    public String getMPlayer1() {
        return mPlayer1;
    }

    public void setMPlayer1(@NonNull String mPlayer1) {
        this.mPlayer1 = mPlayer1;
    }

    @NonNull
    public Integer getMScore1() {
        return mScore1;
    }

    public void setMScore1(@NonNull Integer mScore1) {
        this.mScore1 = mScore1;
    }

    @NonNull
    public String getMPlayer2() {
        return mPlayer2;
    }

    public void setMPlayer2(@NonNull String mPlayer2) {
        this.mPlayer2 = mPlayer2;
    }

    @NonNull
    public Integer getMScore2() {
        return mScore2;
    }

    public void setMScore2(@NonNull Integer mScore2) {
        this.mScore2 = mScore2;
    }

    @NonNull
    public Boolean getMWin() {
        return mWin;
    }

    public void setMWin(@NonNull Boolean mWin) {
        this.mWin = mWin;
    }

    @Override
    public String toString() {
        return "Score{" +
                "mDate='" + mDate + '\'' +
                ", mPlayer1='" + mPlayer1 + '\'' +
                ", mScore1=" + mScore1 +
                ", mPlayer2='" + mPlayer2 + '\'' +
                ", mScore2=" + mScore2 +
                ", mWin=" + mWin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return mDate.equals(score.mDate) && mPlayer1.equals(score.mPlayer1) && mScore1.equals(score.mScore1) && mPlayer2.equals(score.mPlayer2) && mScore2.equals(score.mScore2) && mWin.equals(score.mWin);
    }
}

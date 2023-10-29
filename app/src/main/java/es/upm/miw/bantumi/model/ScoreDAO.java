package es.upm.miw.bantumi.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ScoreDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Score word);

    @Query("DELETE FROM score_table")
    void deleteAll();

    @Query("SELECT * FROM score_table ORDER BY date ASC")
    LiveData<List<Score>> getAllScores();
}
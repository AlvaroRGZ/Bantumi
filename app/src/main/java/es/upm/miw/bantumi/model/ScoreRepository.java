package es.upm.miw.bantumi.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ScoreRepository {

    private ScoreDAO mScoreDao;
    private LiveData<List<Score>> mAllScores;

    public ScoreRepository(Application application) {
        ScoreRoomDatabase db = ScoreRoomDatabase.getDatabase(application);
        mScoreDao = db.ScoreDao();
        mAllScores = mScoreDao.getAllScores();
    }

    public LiveData<List<Score>> getAllScores() {
        return mAllScores;
    }

    public void insert(Score score) {
        ScoreRoomDatabase.databaseWriteExecutor.execute(() -> {
            mScoreDao.insert(score);
        });
    }

    public void deleteAll() {
        ScoreRoomDatabase.databaseWriteExecutor.execute(() -> {
            mScoreDao.deleteAll();
        });
    }
}
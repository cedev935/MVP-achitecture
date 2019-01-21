package com.aliesaassadi.androidmvp.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.aliesaassadi.androidmvp.data.db.entity.LogClass;
import java.util.List;

/**
 * Created by Ali Esa Assadi on 07/03/2018.
 */

@Dao
public interface LogDAO {

    @Query("SELECT * FROM LogClass")
    List<LogClass> getLogs();

    @Insert
    void insertLog(LogClass log);
}

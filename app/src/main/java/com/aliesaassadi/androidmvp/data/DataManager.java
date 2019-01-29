package com.aliesaassadi.androidmvp.data;

import com.aliesaassadi.androidmvp.data.db.LogRepository;
import com.aliesaassadi.androidmvp.data.network.services.MovieService;
import com.preference.PowerPreference;
import com.preference.Preference;

/**
 * Created by Ali Esa Assadi on 26/03/2018.
 */

public class DataManager {

    private static DataManager sInstance;

    private DataManager() {
        // This class is not publicly instantiable
    }

    public static synchronized DataManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataManager();
        }
        return sInstance;
    }

    public Preference getDefaultPreference() {
        return PowerPreference.getDefaultFile();
    }

    public Preference getUserPreference() { return PowerPreference.getFileByName("UserPreference"); }

    public LogRepository getLogRepository() {
        return LogRepository.getInstance();
    }

    public MovieService getMovieService() {
        return MovieService.getInstance();
    }

}

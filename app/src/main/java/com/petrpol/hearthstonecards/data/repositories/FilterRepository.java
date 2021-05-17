package com.petrpol.hearthstonecards.data.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.room.CardsDatabase;
import com.petrpol.hearthstonecards.room.dao.FilterDao;
import com.petrpol.hearthstonecards.webApi.RetrofitController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/** Data repository for Filter info
 *  Gets data from database and updates them from server
 *  Singleton pattern */
public class FilterRepository {

    public static FilterRepository instance;

    private RetrofitController retrofitController;
    private FilterDao filterDao;

    /**
     * Default constructor
     *
     * @param application - application to access database
     */
    public FilterRepository(Application application) {
        this.filterDao = CardsDatabase.getInstance(application).getFilterDao();
        retrofitController = RetrofitController.getInstance();
    }

    /** Updates filter by call from server */
    public void updateFilter(FilterRepositoryInterface callback){
        getFilter(callback);
    }

    /** Gets filter object from DB
     *  Calls get filter from server
     *  Calls callback when success or fail */
    public LiveData<List<Filter>> getFilter(FilterRepositoryInterface callback) {

        LiveData<List<Filter>> data = filterDao.getFilters();

        //Call callback if there is some stored filter
        if (data.getValue()!=null && data.getValue().size()>0)
            callback.onFilterDataGetSuccess();

        //Create call to server
        retrofitController.getFilter(new Callback<Filter>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<Filter> call, Response<Filter> response) {

                // When server error
                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    callback.onFilterDataGetFail(response.message());
                    return;
                }

                // Store filter to Database
                new Thread(() -> {
                    filterDao.addFilter(response.body());
                    callback.onFilterDataGetSuccess();
                }).start();
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<Filter> call, Throwable t) {
                //Print error and call callback with error message
                if (t.getMessage() != null) {
                    Log.e("RetroErrorFail", t.getMessage());
                    callback.onFilterDataGetFail(t.getMessage());
                }
            }
        });

        return data;
    }

}

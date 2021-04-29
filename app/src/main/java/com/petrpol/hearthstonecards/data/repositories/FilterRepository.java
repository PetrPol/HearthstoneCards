package com.petrpol.hearthstonecards.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.petrpol.hearthstonecards.data.model.Filter;
import com.petrpol.hearthstonecards.room.FilterDatabase;
import com.petrpol.hearthstonecards.room.dao.FilterDao;
import com.petrpol.hearthstonecards.webApi.RetrofitController;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** Repository for Filer objects */
public class FilterRepository {

    public static FilterRepository instance;

    private RetrofitController retrofitController;
    private FilterDao filterDao;

    public FilterRepository(FilterDatabase database) {
        this.filterDao = database.getFilterDao();
        retrofitController = RetrofitController.getInstance();

    }

    /** Gets instance (creates if is null) */
    public static synchronized FilterRepository getInstance(FilterDatabase database){
        if (instance == null)
            instance = new FilterRepository(database);

        return instance;
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
            public void onResponse(Call<Filter> call, Response<Filter> response) {
                if (!response.isSuccessful()) {
                    Log.e("RetroError",response.message());
                    callback.onFilterDataGetFail(response.message());
                    return;
                }

                // Store filter to DB TODO
                filterDao.addFilter(response.body());

                callback.onFilterDataGetSuccess();
            }

            @Override
            public void onFailure(Call<Filter> call, Throwable t) {
                if (t.getMessage() != null) {
                    Log.e("RetroErrorFail", t.getMessage());
                    callback.onFilterDataGetFail(t.getMessage());
                }
            }
        });

        return data;
    }

}

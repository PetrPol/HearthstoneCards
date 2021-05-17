package com.petrpol.hearthstonecards.ui.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

/**
 * Base view model with error message and connection problems indicators (used in all models)
 */
public class ABaseViewModel extends AndroidViewModel {

    protected MutableLiveData<String> mErrorMessage = new MutableLiveData<>();
    protected MutableLiveData<Boolean> isConnectionProblems = new MutableLiveData<>();

    public ABaseViewModel(@NonNull Application application) {
        super(application);
        isConnectionProblems.setValue(false);
        mErrorMessage.setValue(null);
    }

    /**
     * Cleans error data before inflate to new view
     */
    public void clean() {
        mErrorMessage.setValue(null);
    }

    //Getters
    public LiveData<String> getErrorMessage() {
        return mErrorMessage;
    }

    public LiveData<Boolean> getIsConnectionProblems() {
        return isConnectionProblems;
    }
}

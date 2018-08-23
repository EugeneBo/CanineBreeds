package eugenebo.com.github.caninebreeds;


import android.view.View;

import java.util.List;

import eugenebo.com.github.caninebreeds.model.Breed;

public interface Contract {

    interface IView {    //todo придумать правильные методы

        void showDataAfterDataLoaded();
    }

//    interface IPresenter {   //todo придумать правильные методы
//
//        void onBreedListItemClicked();
//
//
//        void onLoadBreedFragment();
//
//    }
//
//    interface IModel {  //todo придумать правильные методы
//
//
//    }








    interface OnDataLoadedListener {
        void onDataLoaded();
    }

}
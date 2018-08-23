package eugenebo.com.github.caninebreeds.presenter;

import eugenebo.com.github.caninebreeds.Contract;
import eugenebo.com.github.caninebreeds.model.LoadBreedsTask;

public class BreedListPresenter implements Contract.OnDataLoadedListener {

    private LoadBreedsTask loadBreedsTask;
    private Contract.IView BreedListFragmentView;

    public BreedListPresenter(Contract.IView BreedListFragmentView) {
        this.loadBreedsTask = new LoadBreedsTask(this);
        this.BreedListFragmentView = BreedListFragmentView;
    }

    public void onLoadFragment() {
        loadBreedsTask.execute();
    }


    @Override
    public void onDataLoaded() {
        BreedListFragmentView.showDataAfterDataLoaded();
    }

}


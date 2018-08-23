package eugenebo.com.github.caninebreeds.presenter;

import eugenebo.com.github.caninebreeds.Contract;
import eugenebo.com.github.caninebreeds.model.LoadGalleryItemsTask;

public class ImagesListPresenter implements Contract.OnDataLoadedListener {

    private LoadGalleryItemsTask loadGalleryItemsTask;
    private Contract.IView GalleryItemFragmentView;

    public ImagesListPresenter(Contract.IView GalleryItemFragmentView, String breed, String subBreed) {
        this.loadGalleryItemsTask = new LoadGalleryItemsTask(breed, subBreed, this);
        this.GalleryItemFragmentView = GalleryItemFragmentView;
    }

    public void onLoadFragment() {
        loadGalleryItemsTask.execute();
    }

    @Override
    public void onDataLoaded() {
        GalleryItemFragmentView.showDataAfterDataLoaded();
    }


}

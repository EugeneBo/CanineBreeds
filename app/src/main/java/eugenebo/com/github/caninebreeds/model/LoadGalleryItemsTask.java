package eugenebo.com.github.caninebreeds.model;

import android.os.AsyncTask;
import java.util.List;
import eugenebo.com.github.caninebreeds.Contract;

public class LoadGalleryItemsTask extends AsyncTask<Void, Void, List<GalleryItem>> {

    private String breed;
    private String subBreed;
    private Contract.OnDataLoadedListener onDataLoadedListener;

    public LoadGalleryItemsTask(final String breed, final String subBreed, final Contract.OnDataLoadedListener onDataLoadedListener) {
        this.breed = breed;
        this.subBreed = subBreed;
        this.onDataLoadedListener = onDataLoadedListener;
    }

    @Override
    protected List<GalleryItem> doInBackground(Void... voids) {
        SubBreedGalleryFetcher subBreedGalleryFetcher = new SubBreedGalleryFetcher();
        subBreedGalleryFetcher.setUrl(breed, subBreed);
        return subBreedGalleryFetcher.parseSubBreedGalleryItems();
    }

    @Override
    protected void onPostExecute(final List<GalleryItem> galleryItems) {
        super.onPostExecute(galleryItems);
        BreedListHandler.getInstance().setGalleryItems(galleryItems);
        onDataLoadedListener.onDataLoaded();
    }
}

package eugenebo.com.github.caninebreeds.model;

import android.os.AsyncTask;
import java.util.List;
import eugenebo.com.github.caninebreeds.Contract;

public class LoadBreedsTask extends AsyncTask<Void, Void, List<Breed>> {

    private Contract.OnDataLoadedListener onDataLoadedListener;

    public LoadBreedsTask(final Contract.OnDataLoadedListener onDataLoadedListener) {
        this.onDataLoadedListener = onDataLoadedListener;
    }

    @Override
    protected List<Breed> doInBackground(final Void... voids) {
        return new BreedsListFetcher().parseBreedItems();
    }

    @Override
    protected void onPostExecute(final List<Breed> breeds) {
        super.onPostExecute(breeds);
        BreedListHandler.getInstance().setBreedList(breeds);
        onDataLoadedListener.onDataLoaded();
    }

}

package eugenebo.com.github.caninebreeds.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import eugenebo.com.github.caninebreeds.Contract;
import eugenebo.com.github.caninebreeds.MainActivity;
import eugenebo.com.github.caninebreeds.R;
import eugenebo.com.github.caninebreeds.model.Breed;
import eugenebo.com.github.caninebreeds.model.BreedListHandler;
import eugenebo.com.github.caninebreeds.model.GalleryItem;
import eugenebo.com.github.caninebreeds.model.SubBreed;
import eugenebo.com.github.caninebreeds.presenter.ImagesListPresenter;

public class SubBreedGalleryFragment extends Fragment implements Contract.IView {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private View view;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        String breed = ((Breed) getArguments().getParcelable(BreedFragment.TAG_BREED)).getBreedName();
        String subBreed;
        if (getArguments().getParcelable(BreedFragment.TAG_SUBBREED) != null) {
            subBreed = ((SubBreed) getArguments().getParcelable(BreedFragment.TAG_SUBBREED)).getSubBreedName();
        } else subBreed = null;

        ImagesListPresenter presenter = new ImagesListPresenter(this, breed, subBreed);

        presenter.onLoadFragment();

        view = inflater.inflate(R.layout.fragment_gallery_list, container, false);

        progressBar = view.findViewById(R.id.imagesProgressBar);
        recyclerView = view.findViewById(R.id.imagesRecycleList);

        String actionBarTitle;

        if (subBreed == null) {
            actionBarTitle = breed;
        } else actionBarTitle = breed + " " + subBreed;

        //actionBarTitle = breed + " " + BreedFragment.capitalizeFirstLetter(subBreed);

        ((MainActivity) getActivity()).setActionBarTitle(actionBarTitle);

        return view;
    }


    @Override
    public void showDataAfterDataLoaded() {
        progressBar.setVisibility(View.INVISIBLE);

        List<GalleryItem> galleryItems = BreedListHandler.getInstance().getGalleryItems();

        if (galleryItems != null) {
            final GalleryAdapter galleryAdapter = new GalleryAdapter(galleryItems);
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
            recyclerView.setAdapter(galleryAdapter);

        }
    }


    public class GalleryAdapter extends RecyclerView.Adapter {

        private List<GalleryItem> galleryItems;


        public GalleryAdapter(final List<GalleryItem> galleryItems) {
            this.galleryItems = galleryItems;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.gallery_list_item, parent, false);
            return new GalleryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
            ((GalleryViewHolder) holder).bindData(galleryItems.get(position));
        }

        @Override
        public int getItemCount() {
            return galleryItems.size();
        }
    }


    public class GalleryViewHolder extends RecyclerView.ViewHolder {

        private ImageView galleryItemView;

        public GalleryViewHolder(final View itemView) {
            super(itemView);
            galleryItemView = itemView.findViewById(R.id.galleryItem);
        }

        public void bindData(final GalleryItem galleryItem) {
//            Picasso.get().load(galleryItem.getUrl()).into(galleryItemView);
            Glide.with(view)
                    .load(galleryItem.getUrl())
                    .into(galleryItemView);
        }

    }


}






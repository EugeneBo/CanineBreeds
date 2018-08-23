package eugenebo.com.github.caninebreeds.view;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import eugenebo.com.github.caninebreeds.Contract;
import eugenebo.com.github.caninebreeds.MainActivity;
import eugenebo.com.github.caninebreeds.R;
import eugenebo.com.github.caninebreeds.model.Breed;
import eugenebo.com.github.caninebreeds.model.BreedListHandler;
import eugenebo.com.github.caninebreeds.model.SubBreed;
import eugenebo.com.github.caninebreeds.presenter.BreedListPresenter;


public class BreedFragment extends Fragment implements Contract.IView {

    public static final String TAG_BREED = "eugenebo.com.github.caninebreeds.view BREED";
    public static final String TAG_SUBBREED = "eugenebo.com.github.caninebreeds.view SUB_BREED";

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private View view;

    private Bundle bundle;

    public static String capitalizeFirstLetter(final String name) {
        if (name != null) {
            return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        } else return "";
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        bundle = new Bundle();

        view = inflater.inflate(R.layout.fragment_breed_list, container, false);

        BreedListPresenter presenter = new BreedListPresenter(this);
        presenter.onLoadFragment();

        progressBar = view.findViewById(R.id.breedsProgressBar);
        recyclerView = view.findViewById(R.id.breedsRecycleList);

        ((MainActivity) getActivity()).setActionBarTitle("Dog Breeds");

        return view;
    }


    @Override
    public void showDataAfterDataLoaded() {

        progressBar.setVisibility(View.INVISIBLE);

        List<Breed> breedList = BreedListHandler.getInstance().getBreedList();

        if (breedList != null) {
            final BreedAdapter breedAdapter = new BreedAdapter(breedList);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(breedAdapter);

            RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
            if (animator instanceof DefaultItemAnimator) {
                ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
            }
        }

    }


    public class BreedAdapter extends ExpandableRecyclerViewAdapter<BreedViewHolder, SubBreedViewHolder> {

        public BreedAdapter(final List<? extends ExpandableGroup> groups) {
            super(groups);
        }

        @Override
        public BreedViewHolder onCreateGroupViewHolder(final ViewGroup parent, final int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.breed_list_item, parent, false);
            return new BreedViewHolder(view);
        }

        @Override
        public SubBreedViewHolder onCreateChildViewHolder(final ViewGroup parent, final int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sub_breed_list_item, parent, false);
            return new SubBreedViewHolder(view);
        }

        @Override
        public void onBindGroupViewHolder(final BreedViewHolder holder, final int flatPosition,
                                          final ExpandableGroup group) {
            holder.bindData(group);
        }

        @Override
        public void onBindChildViewHolder(final SubBreedViewHolder holder, final int flatPosition,
                                          ExpandableGroup group, final int childIndex) {
            final SubBreed subBreed = ((Breed) group).getItems().get(childIndex);
            holder.bindData(subBreed);
        }


    }


    public class BreedViewHolder extends GroupViewHolder {
        private static final String TAG = "BreedViewHolder";

        private TextView breedItemTextView;
        private TextView numberOfSubBreedTextView;
        private int numberOfSubBreeds;
        private ExpandableGroup breed;

        public BreedViewHolder(final View itemView) {
            super(itemView);
            breedItemTextView = itemView.findViewById(R.id.breedName);
            numberOfSubBreedTextView = itemView.findViewById(R.id.numberOfSubBreedItems);
        }

        public void bindData(final ExpandableGroup breed) {
            this.breed = breed;
            numberOfSubBreeds = breed.getItems().size();
            breedItemTextView.setText(capitalizeFirstLetter(breed.getTitle()));

            if (breed.getItemCount() != 0) {
                numberOfSubBreedTextView.setVisibility(View.VISIBLE);
                numberOfSubBreedTextView.setText(String.valueOf(numberOfSubBreeds));

            } else numberOfSubBreedTextView.setVisibility(View.INVISIBLE);

            Log.d(TAG, breed.getTitle() + " " + String.valueOf(numberOfSubBreeds));
        }

        @Override
        public void onClick(final View v) {
            super.onClick(v);

            bundle.putParcelable(TAG_BREED, breed);

            if (numberOfSubBreeds == 0) {
                SubBreedGalleryFragment subBreedGalleryFragment = new SubBreedGalleryFragment();
                subBreedGalleryFragment.setArguments(bundle);
                if (getFragmentManager() != null) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentContainer, subBreedGalleryFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }

        }
    }


    class SubBreedViewHolder extends ChildViewHolder {

        private TextView subBreedTextView;
        private Parcelable subBreed;


        public SubBreedViewHolder(final View itemView) {
            super(itemView);

            subBreedTextView = itemView.findViewById(R.id.subBreedItem);

            subBreedTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bundle.putParcelable(TAG_SUBBREED, subBreed);

                    SubBreedGalleryFragment subBreedGalleryFragment = new SubBreedGalleryFragment();
                    subBreedGalleryFragment.setArguments(bundle);
                    if (getFragmentManager() != null) {
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentContainer, subBreedGalleryFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });
        }

        public void bindData(final SubBreed subBreed) {
            this.subBreed = subBreed;
            subBreedTextView.setText(capitalizeFirstLetter(subBreed.getSubBreedName()));
        }

    }

}

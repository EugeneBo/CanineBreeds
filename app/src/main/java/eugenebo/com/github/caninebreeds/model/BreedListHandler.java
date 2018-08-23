package eugenebo.com.github.caninebreeds.model;

import java.util.List;

public class BreedListHandler {

    private static final BreedListHandler INSTANCE = new BreedListHandler();

    private List<Breed> breeds;

    private List<GalleryItem> galleryItems;

    private BreedListHandler() {}

    public List<GalleryItem> getGalleryItems() {
        return galleryItems;
    }

    public void setGalleryItems(List<GalleryItem> galleryItems) {
        this.galleryItems = galleryItems;
    }

    public static BreedListHandler getInstance() {
        return INSTANCE;
    }

    public void setBreedList(final List<Breed> breeds) {
        this.breeds = breeds;
    }

    public List<Breed> getBreedList() {
        return breeds;
    }


}


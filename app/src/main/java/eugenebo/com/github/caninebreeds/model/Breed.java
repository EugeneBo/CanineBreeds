package eugenebo.com.github.caninebreeds.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Breed extends ExpandableGroup<SubBreed> {
    private String breedName;
    private String breedDescription;
    private List<SubBreed> subBreeds;

    public Breed(String breedName, List<SubBreed> subBreeds) {
        super(breedName, subBreeds);
        this.breedName = breedName;
        this.subBreeds = subBreeds;
        this.breedDescription = "(will be available in the future)";
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getBreedDescription() {
        return breedDescription;
    }

    public void setBreedDescription(String breedDescription) {
        this.breedDescription = breedDescription;
    }

    public List<SubBreed> getSubBreeds() {
        return subBreeds;
    }

    public void setSubBreeds(List<SubBreed> subBreeds) {
        this.subBreeds = subBreeds;
    }
}

package eugenebo.com.github.caninebreeds.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SubBreed implements Parcelable {
    private String subBreedName;
    private String subBreedDescription;

    public SubBreed(String subBreedName) {
        this.subBreedName = subBreedName;
        this.subBreedDescription = "(will be available in the future)";
    }

    public SubBreed(Parcel source) {
        subBreedName = source.readString();
    }

    public String getSubBreedName() {
        return subBreedName;
    }

    public void setSubBreedName(String subBreedName) {
        this.subBreedName = subBreedName;
    }

    public String getSubBreedDescription() {
        return subBreedDescription;
    }

    public void setSubBreedDescription(String subBreedDescription) {
        this.subBreedDescription = subBreedDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(subBreedName);
    }

    public static final Creator<SubBreed> CREATOR = new Creator<SubBreed>() {

        @Override
        public SubBreed createFromParcel(Parcel source) {
            return new SubBreed(source);
        }

        @Override
        public SubBreed[] newArray(int size) {
            return new SubBreed[0];
        }
    };


}

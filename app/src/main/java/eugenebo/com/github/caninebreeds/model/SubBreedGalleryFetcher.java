package eugenebo.com.github.caninebreeds.model;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SubBreedGalleryFetcher {

    private static final String IMAGES_BASE_URL = "https://dog.ceo/api/breed/";
    private static final String TAG = "SubBreedGalleryFetcher";

    private HttpURLConnection urlConnection;
    private BufferedReader reader;
    private String resultJson;
    private String url;

    public void setUrl(final String breed, final String subBreed) {
        this.url = URLBuilder(breed, subBreed);
    }

    public List<GalleryItem> parseSubBreedGalleryItems() {

        List<GalleryItem> galleryItems = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONObject(getSubBreedGalleryJSONString(url)).getJSONArray("message");

            for (int i = 0; i < jsonArray.length(); i++) {
                galleryItems.add(new GalleryItem(jsonArray.getString(i)));
                Log.d(TAG, "\t\t\t\tImageURL: " + jsonArray.getString(i));
            }

        } catch (JSONException e) {
            Log.e(TAG, "JSON exception");
            e.printStackTrace();
        }

        return galleryItems;
    }

    private String getSubBreedGalleryJSONString(final String stringURL) {

        try {
            URL url = new URL(stringURL);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            StringBuilder stringBuilder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            resultJson = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, resultJson);
        return resultJson;
    }

    private String URLBuilder(final String breed, final String subBreed) {
        StringBuilder imageURLStringBuilder = new StringBuilder();

        imageURLStringBuilder
                .append(IMAGES_BASE_URL)
                .append(breed);

        if (subBreed != null) {
            imageURLStringBuilder
                    .append("/")
                    .append(subBreed);
        }

        imageURLStringBuilder
                .append("/images");

        return imageURLStringBuilder.toString();
    }


}

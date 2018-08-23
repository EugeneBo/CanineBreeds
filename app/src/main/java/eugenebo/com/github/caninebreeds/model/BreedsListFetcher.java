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
import java.util.Iterator;
import java.util.List;

public class BreedsListFetcher {

    private static final String BREEDS_URL = "https://dog.ceo/api/breeds/list/all";
    private static final String TAG = "BreedsListFetcher";

    private String resultJson;

    public List<Breed> parseBreedItems() {

        List<Breed> breeds = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(getBreedsJSONString()).getJSONObject("message");
            Iterator<String> it = jsonObject.keys();

            while (it.hasNext()) {
                Object key = it.next();
                String keyStr = (String) key;
                Log.d(TAG, "BREED: " + keyStr);
                JSONArray keyValue = (JSONArray) jsonObject.get(keyStr);

                List<SubBreed> subBreeds = new ArrayList<>();

                if (keyValue != null) {
                    for (int i = 0; i < keyValue.length(); i++) {
                        subBreeds.add(new SubBreed((keyValue.getString(i))));
                        Log.d(TAG, "\t\t\t\tSubBREED: " + keyValue.getString(i));
                    }

                    breeds.add(new Breed(keyStr, subBreeds));
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSON exception");
            e.printStackTrace();
        }

        return breeds;
    }

    private String getBreedsJSONString() {
        try {
            URL url = new URL(BREEDS_URL);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            StringBuilder stringBuilder = new StringBuilder();

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

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

}

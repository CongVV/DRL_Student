package hcmue.congvu.drlstudent.Json;

import org.json.JSONArray;

/**
 * Created by CongVu on 28/08/2018.
 */
public class JsonArrayClass {
    public JSONArray mJsonArray;

    public JsonArrayClass() {
        this.mJsonArray = new JSONArray();
    }

    public JsonArrayClass(JSONArray mjsonArray) {
        this.mJsonArray = mjsonArray;
    }

    public JSONArray getMJsonArray() {
        return mJsonArray;
    }

    public void setMJsonArray(JSONArray mjsonArray) {
        this.mJsonArray = mjsonArray;
    }
}

package com.lambdaschool.veganmichianaguide.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

public class Product extends ApiObject {

    protected ArrayList<Store> stores;

    public Product(long productid, String productname, ArrayList<Store> stores) {
        super.id = productid;
        super.name = productname;
        this.stores = stores;
        super.category = "products";
        super.content = arrayListToString(this.stores);
    }

    public Product() {
        super.category = "products";

    }

    public Product(JSONObject jsonObject) {
        super.category = "products";

        try {
            this.id = jsonObject.getLong("productid");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.name = jsonObject.getString("productname");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray stores = jsonObject.getJSONArray("stores");
            this.stores = Product.fromJson(stores);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.content = arrayListToString(this.stores);
    }

    public long getProductid() {
        return id;
    }

    public void setProductid(long productid) {
        this.id = productid;
    }

    public String getProductname() {
        return name;
    }

    public void setProductname(String productname) {
        this.name = productname;
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void setStores(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public String getContent() { return content; }

    public static Store storeFromJson(JSONObject jsonObject) {
        Store b = new Store();
        // Deserialize json into object fields
        try {
            b.id = jsonObject.getLong("groceryid");
            b.name = jsonObject.getString("storename");
            b.location = jsonObject.getString("location");
            b.phone = jsonObject.getString("phone");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }

    public static ArrayList<Store> fromJson(JSONArray jsonArray) {
        JSONObject productJson;
        ArrayList<Store> stores = new ArrayList<Store>(jsonArray.length());
        for (int i =0; i < jsonArray.length(); i++) {
            try {
                productJson = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            Store store = Product.storeFromJson(productJson);
            if (store!= null) {
                stores.add(store);
            }
        }
        return stores;
    }

    public String arrayListToString(ArrayList<Store> array) {
        StringBuilder sb = new StringBuilder("Stores that carry this product: \n");
        for (int i = 0 ; i < array.size(); i++) {
            sb.append(array.get(i).getName() + "\n");
        }
        return sb.toString();
    }
}

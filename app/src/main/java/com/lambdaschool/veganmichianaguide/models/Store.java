package com.lambdaschool.veganmichianaguide.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

public class Store extends ApiObject {
    protected String location;
    protected String phone;
    protected ArrayList<Product> products;

    public Store(long storeid, String storename, String location, String phone, ArrayList<Product> products) {
        super.id = storeid;
        super.name = storename;
        this.location = location;
        this.phone = phone;
        this.products = products;
        super.category = "stores";
        super.content = this.location +  "\n" + this.phone + "\n";
        super.arrayList = this.products;
    }

    public Store() {
        super.category = "stores";
        super.content = this.location +  "\n" + this.phone + "\n" + this.products;
    }

    public Store(JSONObject jsonObject) {
        super.category = "stores";

        try {
            super.name = jsonObject.getString("storename");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.location = jsonObject.getString("location");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.phone = jsonObject.getString("phone");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            this.id = jsonObject.getLong("groceryid");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray products = jsonObject.getJSONArray("products");
            this.products = Store.fromJson(products);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        super.arrayList = this.products;
        super.content = this.location +  "\n\n" + this.phone + "\n\n" + arrayListToString(this.products);
    }

    public long getStoreid() {
        return id;
    }

    public void setStoreid(long storeid) {
        this.id = storeid;
    }

    public String getName() {
        return super.name;
    }

    public void setStorename(String storename) {
        super.name = storename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }


    public static Product productFromJson(JSONObject jsonObject) {
        Product b = new Product();
        // Deserialize json into object fields
        try {
            b.id = jsonObject.getLong("productid");
            b.name = jsonObject.getString("productname");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return b;
    }

    public static ArrayList<Product> fromJson(JSONArray jsonArray) {
        JSONObject productJson;
        ArrayList<Product> products = new ArrayList<Product>(jsonArray.length());
        for (int i =0; i < jsonArray.length(); i++) {
            try {
                productJson = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
            Product product = Store.productFromJson(productJson);
            if (product!= null) {
                products.add(product);
            }
        }
        return products;
    }

    public String arrayListToString(ArrayList<Product> array) {
        StringBuilder sb = new StringBuilder("Products: \n");
        for (int i = 0 ; i < array.size(); i++) {
            sb.append(array.get(i).getProductname() + "\n");
        }
        return sb.toString();
    }
}

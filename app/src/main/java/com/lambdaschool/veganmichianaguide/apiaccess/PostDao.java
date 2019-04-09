package com.lambdaschool.veganmichianaguide.apiaccess;

import android.util.Log;

import com.lambdaschool.veganmichianaguide.models.ApiObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public class PostDao {

    final static String POST = "POST";
    final static String PUT = "PUT";
    final static String DELETE = "DELETE";


    public static void addRestaurant(JSONObject object) {

        try {
            NetworkAdapter.httpRequest("http://192.168.1.14:2019/restaurants/add", POST, object, VeganMichianaDao.headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateRestaurant(JSONObject object, long id) {
        String url = "http://192.168.1.14:2019/restaurants/restaurants/" + String.valueOf(id);
        try {
            NetworkAdapter.httpRequest(url, PUT, object, VeganMichianaDao.headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRestaurant(long restId) {
        String url = "http://192.168.1.14:2019/restaurants/restaurants/" + String.valueOf(restId);
        try {
            NetworkAdapter.httpRequest(url, DELETE, null, VeganMichianaDao.headerProperties );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(JSONObject object) {

        try {
            NetworkAdapter.httpRequest("http://192.168.1.14:2019/products/add", POST, object, VeganMichianaDao.headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct(JSONObject object, long id) {
        String url = "http://192.168.1.14:2019/products/products/" + String.valueOf(id);
        try {
            NetworkAdapter.httpRequest(url, PUT, object, VeganMichianaDao.headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(long restId) {
        String url = "http://192.168.1.14:2019/products/products/" + String.valueOf(restId);
        try {
            NetworkAdapter.httpRequest(url, DELETE, null, VeganMichianaDao.headerProperties );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addStore(JSONObject object) {

        try {
            NetworkAdapter.httpRequest("http://192.168.1.14:2019/stores/add", POST, object, VeganMichianaDao.headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateStore(JSONObject object, long id) {
        String url = "http://192.168.1.14:2019/stores/stores/" + String.valueOf(id);
        try {
            NetworkAdapter.httpRequest(url, PUT, object, VeganMichianaDao.headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStore(long restId) {
        String url = "http://192.168.1.14:2019/stores/stores/" + String.valueOf(restId);
        try {
            NetworkAdapter.httpRequest(url, DELETE, null, VeganMichianaDao.headerProperties );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addMenuItem(JSONObject object) {

        try {
            NetworkAdapter.httpRequest("http://192.168.1.14:2019/menuitems/add", POST, object, VeganMichianaDao.headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateMenuItem(JSONObject object, long id) {
        String url = "http://192.168.1.14:2019/menuitems/menuitems/" + String.valueOf(id);
        try {
            NetworkAdapter.httpRequest(url, PUT, object, VeganMichianaDao.headerProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMenuItem(long restId) {
        String url = "http://192.168.1.14:2019/menuitems/menuitems/" + String.valueOf(restId);
        try {
            NetworkAdapter.httpRequest(url, DELETE, null, VeganMichianaDao.headerProperties );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

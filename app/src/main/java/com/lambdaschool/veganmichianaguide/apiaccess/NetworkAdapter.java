package com.lambdaschool.veganmichianaguide.apiaccess;


import android.util.Log;

import com.lambdaschool.veganmichianaguide.models.ApiObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class NetworkAdapter {

    final static int TIMEOUT = 5000;


    public interface NetworkCallback {
        void returnResult(Boolean success, String result);
    }

    public static void httpGetRequest(final String urlString, final NetworkCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                boolean success = false;
                HttpURLConnection connection = null;
                InputStream stream = null;


                try {
                    URL url = new URL(urlString);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    int responseCode = connection.getResponseCode();
                    if(responseCode == HttpURLConnection.HTTP_OK) {
                        stream = connection.getInputStream();
                        if(stream != null) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                            StringBuilder builder = new StringBuilder();
                            String line = reader.readLine();
                            while(line != null){
                                builder.append(line);
                                line = reader.readLine();
                            }
                            result = builder.toString();
                            success = true;
                        }
                    } else {
                        result = String.valueOf(responseCode);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(connection != null) {
                        connection.disconnect();
                    }

                    if(stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    callback.returnResult(success, result);
                }
//                return result;

            }
        }).start();


    }




    public static String httpRequest(String urlString, String requestType, JSONObject object, Map<String, String> headerProps) throws IOException {
        String result = "";
        InputStream stream = null;
        HttpURLConnection connection = null;
        ;

        try {
            URL apiUrl = new URL(urlString);
            connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setReadTimeout(TIMEOUT);
            connection.setConnectTimeout(TIMEOUT);
            connection.setRequestMethod(requestType);

            if (headerProps != null) {
                for (Map.Entry<String, String> entry : headerProps.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            connection.setDoInput(true);

            if ((requestType.equals("POST") || requestType.equals("PUT")) && object != null) {
                connection.setRequestProperty("Content-Type","application/json");
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(object.toString().getBytes());
                outputStream.close();
            } else {
                connection.connect();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                result = Integer.toString(responseCode);
                throw new IOException("HTTP error code: " + responseCode);
            }

            stream = connection.getInputStream();
            if (stream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    builder.append(line); //.append('\n')
                    line = reader.readLine();
                }
                result = builder.toString();

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (stream != null) {

                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return result;
    }



    public static void httpGetRequest(final String urlString, final AtomicBoolean canceled, final NetworkCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(canceled.get()) {
                    Log.i("GetRequestCanceled", urlString);
                    return;
                }


                String result = "";
                boolean success = false;
                HttpURLConnection connection = null;
                InputStream stream = null;
                try {
                    URL url = new URL(urlString);
                    connection = (HttpURLConnection) url.openConnection();

                    if(canceled.get()) {
                        Log.i("GetRequestCanceled", urlString);
                        throw new IOException();
                    }

                    connection.connect();

                    int responseCode = connection.getResponseCode();

                    if(canceled.get()) {
                        Log.i("GetRequestCanceled", urlString);
                        throw new IOException();
                    }

                    if(responseCode == HttpURLConnection.HTTP_OK) {
                        stream = connection.getInputStream();
                        if(stream != null) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                            StringBuilder builder = new StringBuilder();
                            String line = reader.readLine();
                            while(line != null){
                                builder.append(line);
                                line = reader.readLine();
                            }
                            result = builder.toString();
                            success = true;
                        }
                    } else {
                        result = String.valueOf(responseCode);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if(connection != null) {
                        connection.disconnect();
                    }

                    if(stream != null) {
                        try {
                            stream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    callback.returnResult(success, result);
                }
//                return result;

            }
        }).start();


    }
}

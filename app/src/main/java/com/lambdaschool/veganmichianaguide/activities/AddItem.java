package com.lambdaschool.veganmichianaguide.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.lambdaschool.veganmichianaguide.R;
import com.lambdaschool.veganmichianaguide.apiaccess.PostDao;

import org.json.JSONException;
import org.json.JSONObject;

public class AddItem extends AppCompatActivity {

    EditText name;
    EditText description;
    EditText address;
    EditText phone;
    EditText link;
    CheckBox checkbox;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        final int category = getIntent().getExtras().getInt("category");

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = findViewById(R.id.name_input);
                address = findViewById(R.id.address_input);
                phone = findViewById(R.id.phone_input);
                description = findViewById(R.id.description);
                checkbox = findViewById(R.id.checkbox);
                link = findViewById(R.id.link_input);


                final JSONObject object = new JSONObject();

                switch (category) {
                    case ListFragment.CATEGORY_RESTAURANT:

                        try {
                    object.put("restaurantname", name.getText().toString());
                    object.put("description", description.getText().toString());
                    object.put("location", address.getText().toString());
                    object.put("phone", phone.getText().toString());
                    object.put("link", link.getText().toString());
                    object.put("allvegan", checkbox.isChecked());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.i("Restaurant", object.toString());

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                        PostDao.addRestaurant(object);
                            }
                        }).start();
                        break;

                        case ListFragment.CATEGORY_MENU_ITEM:
                            try {
                                object.put("menuitemname", name.getText().toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    PostDao.addMenuItem(object);
                                }
                            }).start();
                            break;

                            case ListFragment.CATEGORY_PRODUCT:
                                try {
                                    object.put("productname", name.getText().toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Log.i("Restaurant", object.toString());

                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        PostDao.addProduct(object);
                                    }
                                }).start();
                                break;

                                case ListFragment.CATEGORY_STORE:
                                    try {
                                        object.put("storename", name.getText().toString());
                                        object.put("location", address.getText().toString());
                                        object.put("phone", phone.getText().toString());
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            PostDao.addStore(object);
                                        }
                                    }).start();
                                    break;

                }


            }
        });
    }
}

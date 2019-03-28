package com.lambdaschool.veganmichianaguide.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.lambdaschool.veganmichianaguide.R;
import com.lambdaschool.veganmichianaguide.apiaccess.PostDao;
import com.lambdaschool.veganmichianaguide.models.ApiObject;

import org.json.JSONException;
import org.json.JSONObject;



public class UpdateItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ApiObject mParam1;
    private String mParam2;

//    private OnFragmentInteractionListener mListener;

    public static final String CATEGORY_RESTAURANT = "restaurants";
    public static final String CATEGORY_MENU_ITEM = "menuitems";
    public static final String CATEGORY_PRODUCT = "products";
    public static final String CATEGORY_STORE = "stores";

    EditText name;
    EditText description;
    EditText address;
    EditText phone;
    EditText link;
    CheckBox checkbox;
    Button updateButton;

    public UpdateItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment UpdateItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateItemFragment newInstance(ApiObject object) {
        UpdateItemFragment fragment = new UpdateItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, object);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (ApiObject) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        final int category = getActivity().getIntent().getExtras().getInt("category");
        final String category = mParam1.getCategory();

        name =  view.findViewById(R.id.update_name_input);
        address =  view.findViewById(R.id.update_address_input);
        phone = view.findViewById(R.id.update_phone_input);
        description = view.findViewById(R.id.update_description);
        checkbox = view.findViewById(R.id.update_checkbox);
        link = view.findViewById(R.id.update_link_input);

        updateButton = (Button) view.findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final JSONObject object = new JSONObject();

                switch (category) {
                    case CATEGORY_RESTAURANT:

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
                                PostDao.updateRestaurant(object, mParam1.getId());
                                if (getFragmentManager().getBackStackEntryCount() != 0) {
                                    getFragmentManager().popBackStack();
                                }
                            }
                        }).start();
                        break;

                    case CATEGORY_MENU_ITEM:
                        try {
                            object.put("menuitemname", name.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                PostDao.updateMenuItem(object, mParam1.getId());
                                if (getFragmentManager().getBackStackEntryCount() != 0) {
                                    getFragmentManager().popBackStack();
                                }
                            }
                        }).start();
                        break;

                    case CATEGORY_PRODUCT:
                        try {
                            object.put("productname", name.getText().toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.i("Restaurant", object.toString());

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                PostDao.updateProduct(object, mParam1.getId());
                                if (getFragmentManager().getBackStackEntryCount() != 0) {
                                    getFragmentManager().popBackStack();
                                }
                            }
                        }).start();
                        break;

                    case CATEGORY_STORE:
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
                                PostDao.updateStore(object, mParam1.getId());
                                if (getFragmentManager().getBackStackEntryCount() != 0) {
                                    getFragmentManager().popBackStack();
                                }
                            }
                        }).start();
                        break;

                }
                Toast.makeText(getContext(), "Item Updated", Toast.LENGTH_SHORT).show();

            }
        });
    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}

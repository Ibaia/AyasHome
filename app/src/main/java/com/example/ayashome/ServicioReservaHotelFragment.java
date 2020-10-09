package com.example.ayashome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ServicioReservaHotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ServicioReservaHotelFragment extends Fragment {

/*
    //get the spinner from the xml.
    Spinner menu = findViewById(R.id.spinnerMenu);
    //create a list of items for the spinner.
    String[] items = new String[]{"1", "2", "three"};
    //create an adapter to describe how the items are displayed, adapters are used in several places in android.
    //There are multiple variations of this, but this is the basic variant.
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
    //set the spinners adapter to the previously created one.
    dropdown.setAdapter(adapter);
 */

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServicioReservaHotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ServicioReservaHotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServicioReservaHotelFragment newInstance(String param1, String param2) {
        ServicioReservaHotelFragment fragment = new ServicioReservaHotelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_servicio_reserva_hotel, container, false);
    }
}
package com.example.denjo.test.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.denjo.test.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HttpRequest.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HttpRequest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HttpRequest extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HttpRequest() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HttpRequest.
     */
    // TODO: Rename and change types and number of parameters
    public static HttpRequest newInstance(String param1, String param2) {
        HttpRequest fragment = new HttpRequest();
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

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(!HttpConnector.isConnected(connectivityManager)){
            new AlertDialog.Builder(getActivity())
                    .setMessage("インターネット接続できません")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        HttpConnector.RequestInfo requestInfo = new HttpConnector.RequestInfo();
        requestInfo.url = "http://114.51.48.10/result";
        requestInfo.params.add(new HttpConnector.Param(HttpConnector.Param.TYPE_STRING, "key_param1", "value_param1"));
        requestInfo.params.add(new HttpConnector.Param(HttpConnector.Param.TYPE_STRING, "key_param2", "value_param2"));
        requestInfo.asyncCallBack = new AsyncCallback() {
            @Override
            public void onPostExecute(InputStream responseIS) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(responseIS));
                StringBuilder buf = new StringBuilder();
                String line;
                try{
                    while((line = reader.readLine()) != null){
                        buf.append(line);
                    }
                } catch (IOException e){
                    e.printStackTrace();
                    return;
                }
                String responseStr = buf.toString();

                JSONObject jsonObject = null;
                try{
                    jsonObject = new JSONObject(responseStr);
                    /* ! */
                } catch (JSONException e){
                    e.printStackTrace();
                    return;
                }

                /*  */
            }
        };
        HttpConnector.Request(getActivity(), requestInfo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_http_request, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

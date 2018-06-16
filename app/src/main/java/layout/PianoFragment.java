package layout;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.minoru.minoru.spajam2018.MainActivity;
import com.minoru.minoru.spajam2018.MediaManager;
import com.minoru.minoru.spajam2018.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PianoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PianoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PianoFragment extends Fragment implements SensorEventListener ,View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private SensorManager manager;
    String TAG = MainActivity.class.getName();
    private int[] buttonIDs = {R.id.PianoButton1,R.id.PianoButton2,R.id.PianoButton3,
            R.id.PianoButton4,R.id.PianoButton5,R.id.PianoButton6,
            R.id.PianoButton7,R.id.PianoButton8,R.id.PianoButton8};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    long ActionTime=0;


    private OnFragmentInteractionListener mListener;

    // Media
    private MediaManager mManager = new MediaManager();

    public PianoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PianoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PianoFragment newInstance(String param1, String param2) {
        PianoFragment fragment = new PianoFragment();
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

        manager = (SensorManager)this.getActivity().getSystemService(Activity.SENSOR_SERVICE);
        List<Sensor> sensors = manager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(sensors.size() > 0) {
            Sensor s = sensors.get(0);
            manager.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
        }
        mManager.setupPiano(this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_piano, container, false);

        Button[] buttons = new Button[8];
        for (int i=0;i<buttons.length;i++){
            buttons[i] = (Button) view.findViewById(buttonIDs[i]);
            buttons[i].setOnClickListener(this);
        }

        return view;
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
    public void Judge(float accX,float accY,float accZ){
        float accSum = Math.abs(accX) + Math.abs(accY) + Math.abs(accZ);
//        Log.d(TAG,Float.toString(accSum));

        int Min = 20; //動作する最小値
        int Middle = 50;
        int Max = 90; //動作する最小値
        int interval = 5; //動作の検知間隔
//        Log.d(TAG,Float.toString(System.currentTimeMillis()));
        if (System.currentTimeMillis()-ActionTime> interval) {
            if (Max < accSum) {
                Log.d(TAG, "------------------------");
                mManager.setVolume(0);
                mManager.playSound();
            } else if (Middle < accSum) {
                mManager.setVolume(1);
                mManager.playSound();
                Log.d(TAG, ":::::::::::::::::::::::");
            } else if (Min < accSum) {
                Log.d(TAG, "***************************");
                mManager.setVolume(2);
                mManager.playSound();
            }
            ActionTime = System.currentTimeMillis();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            float accX = sensorEvent.values[0];
            float accY = sensorEvent.values[1];
            float accZ = sensorEvent.values[2];

            Judge(accX,accY,accZ);
            Log.d(TAG,Float.toString(accX)+","+Float.toString(accY)+","+Float.toString(accZ));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onClick(View view) {
        if (view != null){
            int buttonID = view.getId();
            for (int i=0;i<buttonIDs.length;i++){
                if (buttonID == buttonIDs[i]){
                        Log.d(TAG,"BUTTTON:BUTTON"+Integer.toString(i+1));
                    mManager.selectPianoSound(7-i);
                    mManager.prepare();

                }
            }
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

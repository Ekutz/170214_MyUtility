package com.jspark.android.myutility;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment sMF;
    MainActivity main;
    LocationManager manager;

    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        main = (MainActivity) context;
        manager = main.getLocationManager();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 리스너 등록
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치 제공자
                3000, // 업데이트 간격
                1, // 측정 변경 거리
                listener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치 제공자
                3000,
                10,
                listener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four, container, false);

        sMF = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
        sMF.getMapAsync(this);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
        }
        // 리스너 해제
        manager.removeUpdates(listener);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 1));
    }

    LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();
            float accuracy = location.getAccuracy();
            String provider = location.getProvider();

            LatLng myPosition = new LatLng(latitude, longitude);
            Log.d("위도", String.valueOf(longitude));
            Log.d("경도", String.valueOf(latitude));
            mMap.addMarker(new MarkerOptions().position(myPosition).title("Marker in my position"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 18));
        }

        @Override //provider 상태 변경 시 호출
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override //GPS 사용이 갑자기 가능해 질 때
        public void onProviderEnabled(String s) {

        }

        @Override //GPS 안될 때
        public void onProviderDisabled(String s) {

        }
    };
}

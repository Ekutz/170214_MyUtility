package com.jspark.android.myutility;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jspark.android.myutility.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FiveFragment.OnListFragmentInteractionListener {

    final int tabCount = 5;

    OneFragment one;
    TwoFragment two;
    ThreeFragment three;
    FourFragment four;
    FiveFragment five;

    FragmentManager fm;

    TabLayout tabL;

    ViewPager vp;

    private LocationManager lM;

    private int mPosition=0;

    public List<Integer> lastPosition = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = new OneFragment();
        two = new TwoFragment();
        three = new ThreeFragment();
        four = new FourFragment();
        if(five==null) five = FiveFragment.newInstance(3);

        tabL = (TabLayout)findViewById(R.id.tab);
        tabL.addTab(tabL.newTab().setText("일반 계산기"));
        tabL.addTab(tabL.newTab().setText("단위 변환기"));
        tabL.addTab(tabL.newTab().setText("검색 엔진"));
        tabL.addTab(tabL.newTab().setText("현재 위치"));
        tabL.addTab(tabL.newTab().setText("갤러리"));

        vp = (ViewPager)findViewById(R.id.viewPager);

        fm = getSupportFragmentManager();
        CutomPagerAdapter adapter = new CutomPagerAdapter(fm);
        vp.setAdapter(adapter);

        lastPosition.add(0);

        // ViewPager가 변할 때 TabLayout 변화시키는 리스너
        vp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabL));

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                mPosition = position;
                if((!lastPosition.isEmpty())&&lastPosition.get(lastPosition.size()-1)!=mPosition) {
                    lastPosition.add(mPosition);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // TabLayout이 변할 때 ViewPager 변화시키는 리스너
        tabL.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vp));

        /*
            GPS 기획 순서
            1. manifest 에 ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION 등록
            2. 런타임 퍼미션 획득
            3. GPS 위치 매니저 정의
            4. GPS on/off 확인
            5. 리스너 실행
            6. 리스너 해제
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        } else {
            init();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    final int REQ_CODE = 100;

    // 안드로이드 버전에 따라 호출되는 메소드
    @TargetApi(Build.VERSION_CODES.M)
    public void checkPermission() {
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String permArr[] = {android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

            // 새로운 권한 획득을 위한 다이얼로그 요청 메소드
            requestPermissions(permArr, REQ_CODE);
        } else {
            init();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQ_CODE) {
            // 권한을 수락한 경우
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED &&
                    grantResults[1]==PackageManager.PERMISSION_GRANTED &&
                    grantResults[2]==PackageManager.PERMISSION_GRANTED) {
                init();
            } else { //권한을 수락하지 않은 경우
                Toast.makeText(MainActivity.this, "어플 재시작 후 권한 설정에 동의해 주세요", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void init() {
        lM = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(!gpsCheck()) {
            makeAlertDialog();
        }
    }

    private void makeAlertDialog() {
        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setTitle("GPS 설정");
        ad.setMessage("GPS 설정창으로 이동하시겠습니까?");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "GPS 기능을 사용하실 수 없습니다", Toast.LENGTH_SHORT).show();
            }
        });
        ad.show();
    }

    private boolean gpsCheck() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return lM.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } else {
            String gpsEnable = android.provider.Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if(gpsEnable.matches(".*gps.*")) {
                return true;
            } else {
                return false;
            }
        }
    }

    public LocationManager getLocationManager() {
        return lM;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    class CutomPagerAdapter extends FragmentStatePagerAdapter {

        public CutomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0 :
                    fragment = one;
                    break;
                case 1 :
                    fragment = two;
                    break;
                case 2 :
                    fragment = three;
                    break;
                case 3 :
                    fragment = four;
                    break;
                case 4 :
                    fragment = five;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(lastPosition.size()==1) {
            super.onBackPressed();
        } else {
            switch (mPosition) {
                case 2:
                    if (three.goBack()) {
                        three.goBack();
                    } else {
                        goBackStack();
                    }
                    break;
                default:
                    goBackStack();
                    break;
            }
        }
    }

    private void goBackStack() {
        lastPosition.remove(lastPosition.size() - 1);
        vp.setCurrentItem(lastPosition.get(lastPosition.size() - 1));
    }
}

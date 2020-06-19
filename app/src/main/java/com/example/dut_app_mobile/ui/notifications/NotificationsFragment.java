package com.example.dut_app_mobile.ui.notifications;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.dut_app_mobile.Adapter;
import com.example.dut_app_mobile.Model;
import com.example.dut_app_mobile.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    ViewPager viewPager;
    com.example.dut_app_mobile.Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

         //code for navigation bar
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.profile, container, false);

        //code for carousel
        models = new ArrayList<>();
        models.add(new Model(R.drawable.zero_dechet_bea_johnson));
        models.add(new Model(R.drawable.imparfait_du_futur_emile_bravo));
        models.add(new Model(R.drawable.la_ballade_dhester_day_mercedes_helnwein));
        models.add(new Model(R.drawable.si_cest_un_homme_primo_levi));

        adapter = new Adapter(models, this.getContext());

        viewPager = root.findViewById(R.id.carousel);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.grey),
                getResources().getColor(R.color.pink),
                getResources().getColor(R.color.yellowish),
                getResources().getColor(R.color.green)
        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() -1) && position < (colors.length - 1)){
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else {
                    viewPager.setBackgroundColor(colors[colors.length -1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return root;
    }
}

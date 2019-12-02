package com.naukri.naukrisuchakkendra;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.naukri.naukrisuchakkendra.BD.Login;
import com.naukri.naukrisuchakkendra.Candidate.candidatelogin;
import com.naukri.naukrisuchakkendra.Candidate.dashboard;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Mainboard extends AppCompatActivity {

    LinearLayout bd,cd;
    private ViewPager viewPager;
    private ViewPager viewPager1;
    private Handler handler = new Handler();
    private Runnable runnable = null;
    private LinearLayout layout_dots;
    private static String[] array_namr_place = { "Welcome To Naukri suchak Kendra"};
    Button sp;
    private static String[] array_image_place = { "slider2.png", "slider.png" };
    private static final String url_base = "https://www.naukrisuchak.com/app/global/img/";
    private AdapterImageSlider adapterImageSlider;
    private AdapterNoteSlider adapterNoteSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainboard);

        Toolbar toolbar = findViewById(R.id.toolbarA);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle("Naukri Suchak Kendra");
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
        }

        bd = findViewById(R.id.bdb);
        cd = findViewById(R.id.cb);
sp=findViewById(R.id.support);

sp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showDialogAbout();
    }
});
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(getApplicationContext(), dashboard.class);
                startActivity(intent);
            }
        });

        layout_dots =  findViewById(R.id.layout_dots);
        viewPager =  findViewById(R.id.pager);
        viewPager1 =  findViewById(R.id.pager1);

        initComponent();

        note();

    }
    private void note() {
        adapterNoteSlider = new AdapterNoteSlider(this, new ArrayList<Imagemodel>());

        final List<Imagemodel> items = new ArrayList<>();
        for (String s : array_namr_place) {
            Imagemodel obj = new Imagemodel();
            obj.image = s;
            items.add(obj);
        }

        adapterNoteSlider.setItems(items);
        viewPager1.setAdapter(adapterNoteSlider);
       // startAutonoteSlider(adapterNoteSlider.getCount());
    }

    private static class AdapterNoteSlider extends PagerAdapter {

        private Activity act;
        private List<Imagemodel> items;


        // constructor
        private AdapterNoteSlider(Activity activity, List<Imagemodel> items) {
            this.act = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        public Imagemodel getItem(int pos) {
            return items.get(pos);
        }

        void setItems(List<Imagemodel> items) {
            this.items = items;
            notifyDataSetChanged();
        }


        @NotNull
        @Override
        public Object instantiateItem(@NotNull ViewGroup container, int position) {
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.notesview, container, false);

            TextView image =  v.findViewById(R.id.note);
            image.setText(array_namr_place[position]);


            ViewPager vp = (ViewPager) container;
            vp.addView(v, 0);

            return v;
        }


        @Override
        public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
            return view == object;
        }
        @Override
        public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
            container.removeView((RelativeLayout) object);

        }

    }

/*    private void startAutonoteSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager1.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager1.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }*/

    private void initComponent() {

        adapterImageSlider = new AdapterImageSlider(this, new ArrayList<Imagemodel>());

        final List<Imagemodel> items = new ArrayList<>();
        for (String s : array_image_place) {
            Imagemodel obj = new Imagemodel();
            obj.image = url_base + s;
//            obj.name = array_title_place[i];
            //          obj.brief = array_brief_place[i];
            items.add(obj);
        }

        adapterImageSlider.setItems(items);
        viewPager.setAdapter(adapterImageSlider);

        // displaying selected image first
        viewPager.setCurrentItem(0);
        addBottomDots(layout_dots, adapterImageSlider.getCount(), 0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int pos) {
                addBottomDots(layout_dots, adapterImageSlider.getCount(), pos);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        startAutoSlider(adapterImageSlider.getCount());
    }

    private void addBottomDots(LinearLayout layout_dots, int size, int current) {
        ImageView[] dots = new ImageView[size];

        layout_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle_outline);
            layout_dots.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current].setImageResource(R.drawable.shape_circle);
        }
    }

    private void startAutoSlider(final int count) {
        runnable = new Runnable() {
            @Override
            public void run() {
                int pos = viewPager.getCurrentItem();
                pos = pos + 1;
                if (pos >= count) pos = 0;
                viewPager.setCurrentItem(pos);
                handler.postDelayed(runnable, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }


    private static class AdapterImageSlider extends PagerAdapter {

        private Activity act;
        private List<Imagemodel> items;

        // constructor
        private AdapterImageSlider(Activity activity, List<Imagemodel> items) {
            this.act = activity;
            this.items = items;
        }

        @Override
        public int getCount() {
            return this.items.size();
        }

        public Imagemodel getItem(int pos) {
            return items.get(pos);
        }

        void setItems(List<Imagemodel> items) {
            this.items = items;
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
            return view == object;
        }

        @NotNull
        @Override
        public Object instantiateItem(@NotNull ViewGroup container, int position) {
            final Imagemodel o = items.get(position);
            LayoutInflater inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item_slider_image, container, false);

            ImageView image = v.findViewById(R.id.image);
           Tools.displayImageOriginal(act, image, o.image);

           // new DownloadImageTask(image).execute(o.image);
            container.addView(v);

            return v;
        }

        @Override
        public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
            container.removeView((RelativeLayout) object);

        }

    }

    @Override
    public void onDestroy() {
        if (runnable != null) handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    public static class Tools {

        static void displayImageOriginal(Context ctx, ImageView img, String drawable) {
            try {
                Picasso.with(ctx)
                        .load(drawable)
                        .into(img);

            } catch (Exception ignored) {
            }

        }
    }





    private void showDialogAbout() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_about);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;


        dialog.findViewById(R.id.bt_getcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:7447489685"));
                startActivity(i);
            }
        });

        dialog.findViewById(R.id.bt_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    @Override
    public void onBackPressed() {


        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Naukri Suchak Kendra")
                .setMessage("Are you sure you want to Exit App?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}

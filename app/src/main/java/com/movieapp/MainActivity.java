package com.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<SlideModel> imageList = new ArrayList<>() ;
        imageList.add(new SlideModel("https://bit.ly/2YoJ77H", "The animal population decreased by 58 percent in 42 years.", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://bit.ly/2BteuF2", "The animal population decreased by 58 percent in 42 years.", ScaleTypes.FIT));

        imageSlider = (ImageSlider) findViewById(R.id.movie_slider);
        imageSlider.setImageList(imageList);
    }

}

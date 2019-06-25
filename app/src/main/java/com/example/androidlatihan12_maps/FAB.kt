package com.example.androidlatihan12_maps

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast

class FAB : AppCompatActivity() {

    lateinit var fab_main : FloatingActionButton
    lateinit var fab_sub1 : FloatingActionButton
    lateinit var fab_sub2 : FloatingActionButton
    lateinit var fab_sub3 : FloatingActionButton
    lateinit var fab_sub4 : FloatingActionButton
    lateinit var fab_open : Animation
    lateinit var fab_close : Animation
    lateinit var rotate_cw : Animation
    lateinit var rotate_acw : Animation
    var isOpen : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        fab_main = findViewById(R.id.fab1) as FloatingActionButton
        fab_sub1 = findViewById(R.id.fab2) as FloatingActionButton
        fab_sub2 = findViewById(R.id.fab3) as FloatingActionButton
        fab_sub3 = findViewById(R.id.fab4) as FloatingActionButton
        fab_sub4 = findViewById(R.id.fab5) as FloatingActionButton
        fab_open = AnimationUtils.loadAnimation(applicationContext, R.anim.open_fab)
        fab_close = AnimationUtils.loadAnimation(applicationContext, R.anim.close_fab)
        rotate_cw = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_clockwise)
        rotate_acw = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_anticlockwise)
        fab_main.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {


                if (isOpen){
                    fab_main.setImageResource(R.drawable.pluss)
                    fab_sub1.startAnimation(fab_close)
                    fab_sub2.startAnimation(fab_close)
                    fab_sub3.startAnimation(fab_close)
                    fab_sub4.startAnimation(fab_close)
                    fab_main.startAnimation(rotate_acw)
                    fab_sub1.hide()
                    fab_sub2.hide()
                    fab_sub3.hide()
                    fab_sub4.hide()
                    isOpen = false
                }else{
                    fab_sub1.startAnimation(fab_open)
                    fab_sub2.startAnimation(fab_open)
                    fab_sub3.startAnimation(fab_open)
                    fab_sub4.startAnimation(fab_open)
                    fab_main.startAnimation(rotate_cw)
                    fab_sub1.show()
                    fab_sub2.show()
                    fab_sub3.show()
                    fab_sub4.show()
                    fab_sub1.isClickable = true
                    fab_sub2.isClickable = true
                    isOpen = true
                    fab_main.setImageResource(R.drawable.close)
                }
            }

        })
        fab_sub1.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

            }

        })
        fab_sub2.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

            }

        })
    }
}
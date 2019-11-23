package com.alif.daftarliga

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class LeagueDetailActivity : AppCompatActivity() {

    private var leagueName: String = ""
    private var leagueImg: Int = 0
    private var leagueDescription: String = ""

    private lateinit var tvLeagueName: TextView
    private lateinit var imgLeague: ImageView
    private lateinit var tvLeagueDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        scrollView {
            verticalLayout {
                lparams(matchParent, wrapContent)
                padding = dip(16)
                backgroundColor = ContextCompat.getColor(context, R.color.mainBackground)

                tvLeagueName = textView {
                    textSize = 24f
                    textColor = Color.WHITE
                }.lparams {
                    gravity = Gravity.CENTER
                }

                imgLeague = imageView {
                    padding = dip(16)
                }.lparams {
                    height = dip(325)
                    width = dip(325)
                    gravity = Gravity.CENTER
                }

                tvLeagueDescription = textView {
                    textSize = 16f
                    textColor = Color.WHITE
                }.lparams {
                    gravity = Gravity.CENTER
                }

                val intent = intent
                val bundle = intent.getParcelableExtra<League>("leagueData")

                leagueName = bundle?.name.toString()
                leagueImg = bundle?.image!!.toInt()
                leagueDescription = bundle.description.toString()

                tvLeagueName.text = leagueName
                Glide.with(context).load(leagueImg).into(imgLeague)
                tvLeagueDescription.text = leagueDescription
            }
        }

        // Get a support ActionBar enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_detail_liga)
    }

    // back when the up button is pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

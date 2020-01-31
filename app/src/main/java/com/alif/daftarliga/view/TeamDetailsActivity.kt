package com.alif.daftarliga.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.alif.daftarliga.R
import com.alif.daftarliga.model.FavoriteTeam
import com.alif.daftarliga.model.Team
import com.alif.daftarliga.model.database.dbFavoriteTeams
import com.alif.daftarliga.model.webservice.ApiRepository
import com.alif.daftarliga.presenter.TeamDetailsPresenter
import com.alif.daftarliga.view.adapters.TeamDetailsPagerAdapter
import com.alif.daftarliga.view.viewinterfaces.TeamDetailsView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_team_details.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class TeamDetailsActivity : AppCompatActivity(), TeamDetailsView {
    private var idTeam: String? = null
    private lateinit var teamDetailsPresenter: TeamDetailsPresenter
    private var menu: Menu? = null
    private var favoriteState = false
    private var teamName: String? = null
    private var teamCountry: String? = null
    private var teamBadge: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_details)

        // enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.title_team_details)

        val intent = intent
        idTeam = intent.getStringExtra(MainActivity.ID_TEAM_KEY)

        initData(idTeam)
    }

    private fun initData(idTeam: String?) {
        val apiRepository = ApiRepository()
        val gson = Gson()

        teamDetailsPresenter = TeamDetailsPresenter(this, apiRepository, gson)
        teamDetailsPresenter.getTeamDetailsData(idTeam)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.menu_favorite, menu)
        // check whether the match data is already in FavoriteTeam database or not
        // and then change the favorite icon based on that condition
        checkFavoriteStateAndChangeIcon()
        return super.onCreateOptionsMenu(menu)
    }

    private fun checkFavoriteStateAndChangeIcon() {
        dbFavoriteTeams.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                .whereArgs(
                    "(ID_TEAM = {idTeam})",
                    "idTeam" to idTeam.toString()
                )
            val favoriteTeam = result.parseList(classParser<FavoriteTeam>())
            if (favoriteTeam.isNotEmpty()) favoriteState = true

            // change favoriteIcon based on favoriteState
            changeFavoriteIcon(favoriteState)
        }
    }

    private fun changeFavoriteIcon(favoriteState: Boolean) {
        if (favoriteState) menu?.getItem(0)?.icon =
            ContextCompat.getDrawable(this, R.drawable.ic_star_white_24dp)
        else menu?.getItem(0)?.icon =
            ContextCompat.getDrawable(this, R.drawable.ic_star_border_white_24dp)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorite_match -> {
                if (!favoriteState) addAsFavorite() else removeFromFavorite()

                // jika data telah ditambahkan ke daftar favorit, maka berarti favoritState yang awalnya false harus diubah menjadi true
                // dan sebaliknya jika data telah dihapus dari daftar favorit maka favoriteState yang awalnya true harus diubah menjadi false
                favoriteState = !favoriteState

                // ubah icon favorit sesuai dengan favoriteState yang sekarang
                changeFavoriteIcon(favoriteState)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addAsFavorite() {
        dbFavoriteTeams.use {
            insert(
                FavoriteTeam.TABLE_FAVORITE_TEAM,
                FavoriteTeam.ID_TEAM to idTeam,
                FavoriteTeam.TEAM_NAME to teamName,
                FavoriteTeam.TEAM_COUNTRY to teamCountry,
                FavoriteTeam.TEAM_BADGE to teamBadge
            )
        }
        toast(getString(R.string.toast_added_team_as_favorite))
    }

    private fun removeFromFavorite() {
        dbFavoriteTeams.use {
            delete(
                FavoriteTeam.TABLE_FAVORITE_TEAM,
                "(ID_TEAM = {idTeam})",
                "idTeam" to idTeam.toString()
            )
        }
        toast(getString(R.string.toast_deleted_team_from_favorite))
    }

    // back when the Up button is pressed
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showLoading() {
        container_team_big_image.visibility = View.INVISIBLE
        pb_team_details.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_team_details.visibility = View.GONE
        container_team_big_image.visibility = View.VISIBLE
    }

    override fun showTeamDetails(teamDetails: Team) {
        teamName = teamDetails.strTeam
        teamBadge = teamDetails.strTeamBadge
        teamCountry = teamDetails.strCountry

        Glide.with(applicationContext).load(teamDetails.strTeamBadge).into(img_team_detail)
        tv_team_name_big.text = teamDetails.strTeam

        viewpager_team_details.adapter = TeamDetailsPagerAdapter(supportFragmentManager, teamDetails)
        tabs_team_details.setupWithViewPager(viewpager_team_details)
    }
}

package com.alif.daftarliga.model.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.alif.daftarliga.model.FavoriteTeam
import org.jetbrains.anko.db.*

class FavoriteTeamDBHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteTeams.db", null, 1) {
    companion object {
        private var instance: FavoriteTeamDBHelper? = null

        // create database using singleton instance
        @Synchronized
        fun getInstance(ctx: Context): FavoriteTeamDBHelper {
            if (instance == null) {
                instance = FavoriteTeamDBHelper(ctx.applicationContext)
            }
            return instance as FavoriteTeamDBHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // create database table
        db?.createTable(
            FavoriteTeam.TABLE_FAVORITE_TEAM, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.ID_TEAM to TEXT,
            FavoriteTeam.TEAM_NAME to TEXT,
            FavoriteTeam.TEAM_COUNTRY to TEXT,
            FavoriteTeam.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // upgrade table
        db?.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}

// access property for context
// IMPORTANT: this must be defined outside the DBHelper scope
val Context.dbFavoriteTeams: FavoriteTeamDBHelper
    get() = FavoriteTeamDBHelper.getInstance(applicationContext)
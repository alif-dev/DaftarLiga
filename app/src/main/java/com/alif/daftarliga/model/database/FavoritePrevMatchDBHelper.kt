package com.alif.daftarliga.model.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.alif.daftarliga.model.FavoriteMatch
import org.jetbrains.anko.db.*

class FavoritePrevMatchDbHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoritePrevMatches.db", null, 1) {
    companion object {
        private var instance: FavoritePrevMatchDbHelper? = null

        // create database using singleton instance
        @Synchronized
        fun getInstance(ctx: Context): FavoritePrevMatchDbHelper {
            if (instance == null) {
                instance = FavoritePrevMatchDbHelper(ctx.applicationContext)
            }
            return instance as FavoritePrevMatchDbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // create database table
        db?.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true,
            FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteMatch.ID_EVENT to TEXT,
            FavoriteMatch.ID_HOME_TEAM to TEXT,
            FavoriteMatch.ID_AWAY_TEAM to TEXT,
            FavoriteMatch.DATE_EVENT to TEXT,
            FavoriteMatch.TIME to TEXT,
            FavoriteMatch.HOME_TEAM to TEXT,
            FavoriteMatch.AWAY_TEAM to TEXT,
            FavoriteMatch.HOME_SCORE to TEXT,
            FavoriteMatch.AWAY_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // upgrade table
        db?.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
    }
}

// access property for context
// IMPORTANT: this must be defined outside the DBHelper scope
val Context.dbPrevMatches: FavoritePrevMatchDbHelper
    get() = FavoritePrevMatchDbHelper.getInstance(applicationContext)
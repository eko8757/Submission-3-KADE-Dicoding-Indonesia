package com.example.eko8757.footballclubmatchdatabase.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "MyFavorite.db", null, 1) {

    companion object {
        private var instance: DbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DbHelper {
            if (instance == null) {
                instance = DbHelper(ctx.applicationContext)
            }
            return instance as DbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.EVENT_ID to TEXT + UNIQUE,
                Favorite.HOMETEAM_ID to TEXT,
                Favorite.HOMETEAM to TEXT,
                Favorite.AWAYTEAM_ID to TEXT,
                Favorite.AWAYTEAM to TEXT,
                Favorite.HOMESCORE to TEXT,
                Favorite.AWAYSCORE to TEXT,
                Favorite.DATEEVENT to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database: DbHelper
    get() = DbHelper.getInstance(applicationContext)
package sohee.cheon.moviedb.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

object Bookmark {
    object BookEntry: BaseColumns {
        const val TABLE_NAME = "bookmark"
        const val MOVIE_ID = "movieId"
    }

    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${BookEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                "${BookEntry.MOVIE_ID} INTEGER)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${BookEntry.TABLE_NAME}"
}
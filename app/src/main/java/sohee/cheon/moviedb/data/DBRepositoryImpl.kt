package sohee.cheon.moviedb.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.provider.BaseColumns
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sohee.cheon.moviedb.data.db.Bookmark
import sohee.cheon.moviedb.data.db.BookmarkSQLiteHelper
import sohee.cheon.moviedb.domain.DBRepository
import javax.inject.Inject

class DBRepositoryImpl @Inject constructor(
    private val bookmarkDB : BookmarkSQLiteHelper
): DBRepository {
    override fun checkBookmark(movieId: Int): Flow<Boolean> = flow {
        val db = bookmarkDB.readableDatabase
        val project = arrayOf(BaseColumns._ID, Bookmark.BookEntry.MOVIE_ID)

        val selection = "${Bookmark.BookEntry.MOVIE_ID} = ?"
        val selectionArgs = arrayOf("$movieId")

        val sortOrder = "${Bookmark.BookEntry.MOVIE_ID} DESC"

        val cursor = db.query(
            Bookmark.BookEntry.TABLE_NAME,
            project,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        if (cursor.count == 0) {
            emit(false)
        } else {
            cursor.moveToFirst()
            emit(cursor.getInt(1) == movieId)
        }

        db.close()
    }

    override fun changeBookmark(bookmark: Boolean, movieId: Int): Flow<Boolean> = flow {
        val db = bookmarkDB.writableDatabase

        if (bookmark) {
            val selection = "${Bookmark.BookEntry.MOVIE_ID} LIKE ?"
            val selectionArgs = arrayOf("$movieId")
            val newRowId = db.delete(Bookmark.BookEntry.TABLE_NAME, selection, selectionArgs)
            Log.d("Delete", "$newRowId")

            emit(false)
        } else {
            val value = ContentValues().apply {
                put(Bookmark.BookEntry.MOVIE_ID, movieId)
            }

            val newRowId = db?.insert(Bookmark.BookEntry.TABLE_NAME, null, value)
            Log.d("Insert", "$newRowId")


            emit(true)
        }
        db.close()
    }

    override fun getBookmark(): Flow<Array<Int>> = flow {
        var result = arrayOf<Int>()

        val db = bookmarkDB.readableDatabase
        val project = arrayOf(BaseColumns._ID, Bookmark.BookEntry.MOVIE_ID)

        val cursor = db.query(
            Bookmark.BookEntry.TABLE_NAME,
            project,
            null,
            null,
            null,
            null,
            null
        )

        if (cursor.count == 0) {
            emit(arrayOf())
        } else {
            cursor.moveToFirst()
            result = result.plus(cursor.getInt(1))

            for ( i in 2..cursor.count) {
                cursor.moveToNext()
                result = result.plus(cursor.getInt(1))
            }

            emit(result)
        }

        db.close()
    }

}
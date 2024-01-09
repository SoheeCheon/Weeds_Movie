package sohee.cheon.moviedb.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.provider.BaseColumns
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

        emit(cursor.count == 1)

        db.close()
    }

    override fun changeBookmark(bookmark: Boolean, movieId: Int): Flow<Boolean> = flow {
        val db = bookmarkDB.writableDatabase

        if (bookmark) {
            val selection = "${Bookmark.BookEntry.MOVIE_ID} LIKE ?"
            val selectionArgs = arrayOf("$movieId")
            val deletedRows = db.delete(Bookmark.BookEntry.TABLE_NAME, selection, selectionArgs)

            emit(false)
        } else {
            val value = ContentValues().apply {
                put(Bookmark.BookEntry.MOVIE_ID, movieId)
            }

            val newRowId = db?.insert(Bookmark.BookEntry.TABLE_NAME, null, value)

            emit(true)
        }
    }
}
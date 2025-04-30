package com.android.data.source.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.android.data.model.RestaurantEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class RestaurantDao_Impl implements RestaurantDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RestaurantEntity> __insertionAdapterOfRestaurantEntity;

  public RestaurantDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRestaurantEntity = new EntityInsertionAdapter<RestaurantEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `Restaurant` (`id`,`title`,`opening`,`closing`,`location`,`image_url`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RestaurantEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindString(3, entity.getOpening());
        if (entity.getClosing() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getClosing());
        }
        statement.bindString(5, entity.getLocation());
        if (entity.getImageUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getImageUrl());
        }
      }
    };
  }

  @Override
  public Object insertAll(final List<RestaurantEntity> restaurants,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRestaurantEntity.insert(restaurants);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final RestaurantEntity restaurantEntity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRestaurantEntity.insert(restaurantEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<RestaurantEntity>> $completion) {
    final String _sql = "SELECT * FROM restaurant";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RestaurantEntity>>() {
      @Override
      @NonNull
      public List<RestaurantEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOpening = CursorUtil.getColumnIndexOrThrow(_cursor, "opening");
          final int _cursorIndexOfClosing = CursorUtil.getColumnIndexOrThrow(_cursor, "closing");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
          final List<RestaurantEntity> _result = new ArrayList<RestaurantEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RestaurantEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpOpening;
            _tmpOpening = _cursor.getString(_cursorIndexOfOpening);
            final String _tmpClosing;
            if (_cursor.isNull(_cursorIndexOfClosing)) {
              _tmpClosing = null;
            } else {
              _tmpClosing = _cursor.getString(_cursorIndexOfClosing);
            }
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            _item = new RestaurantEntity(_tmpId,_tmpTitle,_tmpOpening,_tmpClosing,_tmpLocation,_tmpImageUrl);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

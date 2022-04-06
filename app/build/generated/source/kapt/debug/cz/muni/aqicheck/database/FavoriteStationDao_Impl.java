package cz.muni.aqicheck.database;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FavoriteStationDao_Impl implements FavoriteStationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FavoriteStationEntity> __insertionAdapterOfFavoriteStationEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public FavoriteStationDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavoriteStationEntity = new EntityInsertionAdapter<FavoriteStationEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `FavoriteStationEntity` (`id`,`lastKnownAqi`,`lastSyncTime`,`station`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FavoriteStationEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getLastKnownAqi() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getLastKnownAqi());
        }
        if (value.getLastSyncTime() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLastSyncTime());
        }
        if (value.getStation() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getStation());
        }
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM FavoriteStationEntity WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void saveEntity(final FavoriteStationEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfFavoriteStationEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteById(final long id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteById.release(_stmt);
    }
  }

  @Override
  public List<FavoriteStationEntity> getAll() {
    final String _sql = "SELECT * FROM FavoriteStationEntity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfLastKnownAqi = CursorUtil.getColumnIndexOrThrow(_cursor, "lastKnownAqi");
      final int _cursorIndexOfLastSyncTime = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSyncTime");
      final int _cursorIndexOfStation = CursorUtil.getColumnIndexOrThrow(_cursor, "station");
      final List<FavoriteStationEntity> _result = new ArrayList<FavoriteStationEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final FavoriteStationEntity _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpLastKnownAqi;
        if (_cursor.isNull(_cursorIndexOfLastKnownAqi)) {
          _tmpLastKnownAqi = null;
        } else {
          _tmpLastKnownAqi = _cursor.getString(_cursorIndexOfLastKnownAqi);
        }
        final String _tmpLastSyncTime;
        if (_cursor.isNull(_cursorIndexOfLastSyncTime)) {
          _tmpLastSyncTime = null;
        } else {
          _tmpLastSyncTime = _cursor.getString(_cursorIndexOfLastSyncTime);
        }
        final String _tmpStation;
        if (_cursor.isNull(_cursorIndexOfStation)) {
          _tmpStation = null;
        } else {
          _tmpStation = _cursor.getString(_cursorIndexOfStation);
        }
        _item = new FavoriteStationEntity(_tmpId,_tmpLastKnownAqi,_tmpLastSyncTime,_tmpStation);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

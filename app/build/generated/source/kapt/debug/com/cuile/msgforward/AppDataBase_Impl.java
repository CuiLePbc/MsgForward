package com.cuile.msgforward;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDataBase_Impl extends AppDataBase {
  private volatile ForwardMsgDao _forwardMsgDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `forwardTable` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `from` TEXT NOT NULL, `to` TEXT NOT NULL, `content` TEXT NOT NULL, `sendTime` TEXT NOT NULL, `forwardTime` TEXT NOT NULL, `forwardType` TEXT NOT NULL, `other` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f63e36d2282a492ff4ed8958c11d68a4')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `forwardTable`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsForwardTable = new HashMap<String, TableInfo.Column>(8);
        _columnsForwardTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsForwardTable.put("from", new TableInfo.Column("from", "TEXT", true, 0));
        _columnsForwardTable.put("to", new TableInfo.Column("to", "TEXT", true, 0));
        _columnsForwardTable.put("content", new TableInfo.Column("content", "TEXT", true, 0));
        _columnsForwardTable.put("sendTime", new TableInfo.Column("sendTime", "TEXT", true, 0));
        _columnsForwardTable.put("forwardTime", new TableInfo.Column("forwardTime", "TEXT", true, 0));
        _columnsForwardTable.put("forwardType", new TableInfo.Column("forwardType", "TEXT", true, 0));
        _columnsForwardTable.put("other", new TableInfo.Column("other", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysForwardTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesForwardTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoForwardTable = new TableInfo("forwardTable", _columnsForwardTable, _foreignKeysForwardTable, _indicesForwardTable);
        final TableInfo _existingForwardTable = TableInfo.read(_db, "forwardTable");
        if (! _infoForwardTable.equals(_existingForwardTable)) {
          throw new IllegalStateException("Migration didn't properly handle forwardTable(com.cuile.msgforward.ForwardInfo).\n"
                  + " Expected:\n" + _infoForwardTable + "\n"
                  + " Found:\n" + _existingForwardTable);
        }
      }
    }, "f63e36d2282a492ff4ed8958c11d68a4", "4d3c7c1a9b95abed4b33ceac05ab4d91");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "forwardTable");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `forwardTable`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public ForwardMsgDao forwardMsgDao() {
    if (_forwardMsgDao != null) {
      return _forwardMsgDao;
    } else {
      synchronized(this) {
        if(_forwardMsgDao == null) {
          _forwardMsgDao = new ForwardMsgDao_Impl(this);
        }
        return _forwardMsgDao;
      }
    }
  }
}

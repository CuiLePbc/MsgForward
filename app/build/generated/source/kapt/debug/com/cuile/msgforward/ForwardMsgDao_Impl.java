package com.cuile.msgforward;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ForwardMsgDao_Impl implements ForwardMsgDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfForwardInfo;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllForwardMsgs;

  public ForwardMsgDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfForwardInfo = new EntityInsertionAdapter<ForwardInfo>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `forwardTable`(`id`,`from`,`to`,`content`,`sendTime`,`forwardTime`,`forwardType`,`other`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ForwardInfo value) {
        stmt.bindLong(1, value.getId());
        if (value.getFrom() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFrom());
        }
        if (value.getTo() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTo());
        }
        if (value.getContent() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getContent());
        }
        if (value.getSendTime() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSendTime());
        }
        if (value.getForwardTime() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getForwardTime());
        }
        if (value.getForwardType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getForwardType());
        }
        if (value.getOther() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getOther());
        }
      }
    };
    this.__preparedStmtOfDeleteAllForwardMsgs = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM forwardTable";
        return _query;
      }
    };
  }

  @Override
  public void insertForwardMsg(final ForwardInfo forwardInfo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfForwardInfo.insert(forwardInfo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllForwardMsgs() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllForwardMsgs.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllForwardMsgs.release(_stmt);
    }
  }

  @Override
  public List<ForwardInfo> getAllForwardMsgs() {
    final String _sql = "SELECT * FROM forwardTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFrom = CursorUtil.getColumnIndexOrThrow(_cursor, "from");
      final int _cursorIndexOfTo = CursorUtil.getColumnIndexOrThrow(_cursor, "to");
      final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
      final int _cursorIndexOfSendTime = CursorUtil.getColumnIndexOrThrow(_cursor, "sendTime");
      final int _cursorIndexOfForwardTime = CursorUtil.getColumnIndexOrThrow(_cursor, "forwardTime");
      final int _cursorIndexOfForwardType = CursorUtil.getColumnIndexOrThrow(_cursor, "forwardType");
      final int _cursorIndexOfOther = CursorUtil.getColumnIndexOrThrow(_cursor, "other");
      final List<ForwardInfo> _result = new ArrayList<ForwardInfo>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ForwardInfo _item;
        final String _tmpFrom;
        _tmpFrom = _cursor.getString(_cursorIndexOfFrom);
        final String _tmpTo;
        _tmpTo = _cursor.getString(_cursorIndexOfTo);
        final String _tmpContent;
        _tmpContent = _cursor.getString(_cursorIndexOfContent);
        final String _tmpSendTime;
        _tmpSendTime = _cursor.getString(_cursorIndexOfSendTime);
        final String _tmpForwardTime;
        _tmpForwardTime = _cursor.getString(_cursorIndexOfForwardTime);
        final String _tmpForwardType;
        _tmpForwardType = _cursor.getString(_cursorIndexOfForwardType);
        final String _tmpOther;
        _tmpOther = _cursor.getString(_cursorIndexOfOther);
        _item = new ForwardInfo(_tmpFrom,_tmpTo,_tmpContent,_tmpSendTime,_tmpForwardTime,_tmpForwardType,_tmpOther);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}

package com.cuile.msgforward;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\'J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H\'\u00a8\u0006\t"}, d2 = {"Lcom/cuile/msgforward/ForwardMsgDao;", "", "deleteAllForwardMsgs", "", "getAllForwardMsgs", "", "Lcom/cuile/msgforward/ForwardInfo;", "insertForwardMsg", "forwardInfo", "app_debug"})
public abstract interface ForwardMsgDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM forwardTable")
    public abstract java.util.List<com.cuile.msgforward.ForwardInfo> getAllForwardMsgs();
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertForwardMsg(@org.jetbrains.annotations.NotNull()
    com.cuile.msgforward.ForwardInfo forwardInfo);
    
    @androidx.room.Query(value = "DELETE FROM forwardTable")
    public abstract void deleteAllForwardMsgs();
}
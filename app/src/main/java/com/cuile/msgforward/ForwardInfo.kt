package com.cuile.msgforward

import androidx.lifecycle.LiveData
import androidx.room.*

@Database(entities = [ForwardInfo::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun forwardMsgDao(): ForwardMsgDao
}

@Entity (tableName = "forwardTable")
data class ForwardInfo (
    var from: String,
    var to: String,
    var content: String,
    var sendTime: String,
    var forwardTime: String,
    var forwardType: String,
    var other: String
) {
    @PrimaryKey(autoGenerate = true)var id: Long = 0
}

@Dao
interface ForwardMsgDao{
    @Query("SELECT * FROM forwardTable")
    fun getAllForwardMsgs(): LiveData<List<ForwardInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForwardMsg(forwardInfo: ForwardInfo)

    @Query("DELETE FROM forwardTable")
    fun deleteAllForwardMsgs()
}
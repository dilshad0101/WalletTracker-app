package com.app.spendr.data

import android.content.Context
import androidx.room.*

@Database(entities = [Transaction::class], version = 1)
@TypeConverters(Converters::class)
abstract class TransactionDatabase: RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object{
        @Volatile
        private var INSTANCE : TransactionDatabase? = null
        fun getDatabase(context: Context): TransactionDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }
            synchronized(this){}
            val instance = Room.databaseBuilder(
                context.applicationContext,
                TransactionDatabase::class.java,
                "app_database"
            ).build()
            INSTANCE = instance
            return instance
        }
    }



}
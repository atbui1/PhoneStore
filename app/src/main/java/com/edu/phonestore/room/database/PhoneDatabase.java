package com.edu.phonestore.room.database;
import android.content.Context;

import com.edu.phonestore.room.dao.UserDao;
import com.edu.phonestore.room.entity.User;
import com.edu.phonestore.utils.RoleConverters;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
//import static com.edu.phonestore.room.database.PhoneDatabase.DATABASE_VERSION;

@Database(entities = {User.class}, exportSchema = false, version = 1)
@TypeConverters({RoleConverters.class})
public abstract class PhoneDatabase extends RoomDatabase {
//    public static final int DATABASE_VERSION = 1;

//    static Migration migration_from_1_to_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE user ADD COLUNM year TEXT");
//        }
//    };

//    Migration cover db old to new db new

    public static final String DATABASE_NAME = "phone_db_1";
    private static PhoneDatabase INSTANCE;

    public abstract UserDao userDao();

    public static PhoneDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (PhoneDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhoneDatabase.class, DATABASE_NAME)
//                            .allowMainThreadQueries()
//                            .addMigrations(migration_from_1_to_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

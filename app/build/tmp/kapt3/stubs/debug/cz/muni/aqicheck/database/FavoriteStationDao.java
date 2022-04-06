package cz.muni.aqicheck.database;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH\'\u00a8\u0006\u000b"}, d2 = {"Lcz/muni/aqicheck/database/FavoriteStationDao;", "", "deleteById", "", "id", "", "getAll", "", "Lcz/muni/aqicheck/database/FavoriteStationEntity;", "saveEntity", "entity", "app_debug"})
public abstract interface FavoriteStationDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void saveEntity(@org.jetbrains.annotations.NotNull()
    cz.muni.aqicheck.database.FavoriteStationEntity entity);
    
    @androidx.room.Query(value = "DELETE FROM FavoriteStationEntity WHERE id = :id")
    public abstract void deleteById(long id);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM FavoriteStationEntity")
    public abstract java.util.List<cz.muni.aqicheck.database.FavoriteStationEntity> getAll();
}
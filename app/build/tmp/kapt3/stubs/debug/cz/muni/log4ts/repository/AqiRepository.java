package cz.muni.log4ts.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000f\u001a\u00020\u0010J6\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00120\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00120\u0016J\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\fH\u0002J<\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\n2\u0018\u0010\u0015\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0004\u0012\u00020\u00120\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00120\u0016J\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcz/muni/log4ts/repository/AqiRepository;", "", "context", "Landroid/content/Context;", "favoriteStationDao", "Lcz/muni/log4ts/database/FavoriteStationDao;", "aqiApi", "Lcz/muni/log4ts/webservice/AqiApi;", "(Landroid/content/Context;Lcz/muni/log4ts/database/FavoriteStationDao;Lcz/muni/log4ts/webservice/AqiApi;)V", "token", "", "getFavorites", "", "Lcz/muni/log4ts/data/AqiPresentableListItem;", "getMockedData", "count", "", "getStationById", "", "id", "", "onSuccess", "Lkotlin/Function1;", "Lcz/muni/log4ts/webservice/response/AqiDetailResponse;", "onFailure", "", "mapAqi", "items", "Lcz/muni/log4ts/webservice/response/AqiListItem;", "search", "keyword", "updateFavorite", "item", "app_debug"})
public final class AqiRepository {
    private final cz.muni.log4ts.database.FavoriteStationDao favoriteStationDao = null;
    private final cz.muni.log4ts.webservice.AqiApi aqiApi = null;
    private final java.lang.String token = "ebb5ee840b6a6590cfcc2e354b7c26b135a21cb0";
    
    public AqiRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    cz.muni.log4ts.database.FavoriteStationDao favoriteStationDao, @org.jetbrains.annotations.NotNull()
    cz.muni.log4ts.webservice.AqiApi aqiApi) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<cz.muni.log4ts.data.AqiPresentableListItem> getMockedData(int count) {
        return null;
    }
    
    public final void search(@org.jetbrains.annotations.NotNull()
    java.lang.String keyword, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<cz.muni.log4ts.data.AqiPresentableListItem>, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> onFailure) {
    }
    
    private final java.util.List<cz.muni.log4ts.data.AqiPresentableListItem> mapAqi(java.util.List<cz.muni.log4ts.webservice.response.AqiListItem> items) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<cz.muni.log4ts.data.AqiPresentableListItem> getFavorites() {
        return null;
    }
    
    public final void updateFavorite(@org.jetbrains.annotations.NotNull()
    cz.muni.log4ts.data.AqiPresentableListItem item) {
    }
    
    public final void getStationById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super cz.muni.log4ts.webservice.response.AqiDetailResponse, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Throwable, kotlin.Unit> onFailure) {
    }
}
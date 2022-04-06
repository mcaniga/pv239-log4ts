package cz.muni.log4ts.webservice;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u000b\u001a\u00020\b2\b\b\u0001\u0010\u0007\u001a\u00020\bH\'\u00a8\u0006\f"}, d2 = {"Lcz/muni/log4ts/webservice/AqiApi;", "", "getSearchAqiById", "Lretrofit2/Call;", "Lcz/muni/log4ts/webservice/response/AqiDetailResponse;", "id", "", "token", "", "getSearchAqiByName", "Lcz/muni/log4ts/webservice/response/AqiListResponse;", "keyboard", "app_debug"})
public abstract interface AqiApi {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "search/")
    public abstract retrofit2.Call<cz.muni.log4ts.webservice.response.AqiListResponse> getSearchAqiByName(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "keyword")
    java.lang.String keyboard, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "token")
    java.lang.String token);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "feed/@{id}/")
    public abstract retrofit2.Call<cz.muni.log4ts.webservice.response.AqiDetailResponse> getSearchAqiById(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "token")
    java.lang.String token);
}
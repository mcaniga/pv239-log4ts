package cz.muni.log4ts.webservice;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0005\u001a\u0002H\u0006\"\u0006\b\u0000\u0010\u0006\u0018\u00012\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0082\b\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcz/muni/log4ts/webservice/RetrofitUtil;", "", "()V", "BASE_URL", "", "create", "T", "baseUrl", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Ljava/lang/String;Lokhttp3/OkHttpClient;)Ljava/lang/Object;", "createAqiWebService", "Lcz/muni/log4ts/webservice/AqiApi;", "createOkHttpClient", "app_debug"})
public final class RetrofitUtil {
    @org.jetbrains.annotations.NotNull()
    public static final cz.muni.log4ts.webservice.RetrofitUtil INSTANCE = null;
    private static final java.lang.String BASE_URL = "https://api.waqi.info/";
    
    private RetrofitUtil() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final cz.muni.log4ts.webservice.AqiApi createAqiWebService() {
        return null;
    }
    
    private final okhttp3.OkHttpClient createOkHttpClient() {
        return null;
    }
}
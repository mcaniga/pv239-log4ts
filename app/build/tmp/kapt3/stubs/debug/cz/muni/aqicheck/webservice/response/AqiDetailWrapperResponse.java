package cz.muni.aqicheck.webservice.response;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\fH\u00c6\u0003JA\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\t\u0010#\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006$"}, d2 = {"Lcz/muni/aqicheck/webservice/response/AqiDetailWrapperResponse;", "", "aqi", "", "attributions", "", "Lcz/muni/aqicheck/webservice/response/Attribution;", "city", "Lcz/muni/aqicheck/webservice/response/AqiDetailCityResponse;", "time", "Lcz/muni/aqicheck/webservice/response/AqiDetailTime;", "forecast", "Lcz/muni/aqicheck/webservice/response/ForecastWrapper;", "(Ljava/lang/String;Ljava/util/List;Lcz/muni/aqicheck/webservice/response/AqiDetailCityResponse;Lcz/muni/aqicheck/webservice/response/AqiDetailTime;Lcz/muni/aqicheck/webservice/response/ForecastWrapper;)V", "getAqi", "()Ljava/lang/String;", "getAttributions", "()Ljava/util/List;", "getCity", "()Lcz/muni/aqicheck/webservice/response/AqiDetailCityResponse;", "getForecast", "()Lcz/muni/aqicheck/webservice/response/ForecastWrapper;", "getTime", "()Lcz/muni/aqicheck/webservice/response/AqiDetailTime;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class AqiDetailWrapperResponse {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String aqi = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<cz.muni.aqicheck.webservice.response.Attribution> attributions = null;
    @org.jetbrains.annotations.NotNull()
    private final cz.muni.aqicheck.webservice.response.AqiDetailCityResponse city = null;
    @org.jetbrains.annotations.NotNull()
    private final cz.muni.aqicheck.webservice.response.AqiDetailTime time = null;
    @org.jetbrains.annotations.NotNull()
    private final cz.muni.aqicheck.webservice.response.ForecastWrapper forecast = null;
    
    @org.jetbrains.annotations.NotNull()
    public final cz.muni.aqicheck.webservice.response.AqiDetailWrapperResponse copy(@org.jetbrains.annotations.NotNull()
    java.lang.String aqi, @org.jetbrains.annotations.NotNull()
    java.util.List<cz.muni.aqicheck.webservice.response.Attribution> attributions, @org.jetbrains.annotations.NotNull()
    cz.muni.aqicheck.webservice.response.AqiDetailCityResponse city, @org.jetbrains.annotations.NotNull()
    cz.muni.aqicheck.webservice.response.AqiDetailTime time, @org.jetbrains.annotations.NotNull()
    cz.muni.aqicheck.webservice.response.ForecastWrapper forecast) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    public AqiDetailWrapperResponse(@org.jetbrains.annotations.NotNull()
    java.lang.String aqi, @org.jetbrains.annotations.NotNull()
    java.util.List<cz.muni.aqicheck.webservice.response.Attribution> attributions, @org.jetbrains.annotations.NotNull()
    cz.muni.aqicheck.webservice.response.AqiDetailCityResponse city, @org.jetbrains.annotations.NotNull()
    cz.muni.aqicheck.webservice.response.AqiDetailTime time, @org.jetbrains.annotations.NotNull()
    cz.muni.aqicheck.webservice.response.ForecastWrapper forecast) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAqi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<cz.muni.aqicheck.webservice.response.Attribution> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<cz.muni.aqicheck.webservice.response.Attribution> getAttributions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final cz.muni.aqicheck.webservice.response.AqiDetailCityResponse component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final cz.muni.aqicheck.webservice.response.AqiDetailCityResponse getCity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final cz.muni.aqicheck.webservice.response.AqiDetailTime component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final cz.muni.aqicheck.webservice.response.AqiDetailTime getTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final cz.muni.aqicheck.webservice.response.ForecastWrapper component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final cz.muni.aqicheck.webservice.response.ForecastWrapper getForecast() {
        return null;
    }
}
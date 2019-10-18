package com.cuile.msgforward.msgreceiver;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\'\u0010\u0002\u001a\u00020\u00032\u0014\b\u0001\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\t"}, d2 = {"Lcom/cuile/msgforward/msgreceiver/FangTangService;", "", "sendMessage", "Lokhttp3/ResponseBody;", "map", "", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface FangTangService {
    public static final com.cuile.msgforward.msgreceiver.FangTangService.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "https://sc.ftqq.com/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SC_KEY = "SCU49776T2a19f9f81f181cc78be559a0017bd13f5cc27bbaa2b30";
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "SCU49776T2a19f9f81f181cc78be559a0017bd13f5cc27bbaa2b30.send")
    public abstract java.lang.Object sendMessage(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.QueryMap()
    java.util.Map<java.lang.String, java.lang.String> map, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super okhttp3.ResponseBody> p1);
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/cuile/msgforward/msgreceiver/FangTangService$Companion;", "", "()V", "BASE_URL", "", "SC_KEY", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String BASE_URL = "https://sc.ftqq.com/";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String SC_KEY = "SCU49776T2a19f9f81f181cc78be559a0017bd13f5cc27bbaa2b30";
        
        private Companion() {
            super();
        }
    }
}
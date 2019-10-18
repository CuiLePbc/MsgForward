package com.cuile.msgforward.msgreceiver;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u001b\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\b"}, d2 = {"Lcom/cuile/msgforward/msgreceiver/FeiGeService;", "", "sendMessage", "Lcom/cuile/msgforward/msgreceiver/FeiGeResponseBody;", "requestBody", "Lokhttp3/RequestBody;", "(Lokhttp3/RequestBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface FeiGeService {
    public static final com.cuile.msgforward.msgreceiver.FeiGeService.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "http://u.ifeige.cn/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FEI_GE_SECRET = "f4f5abb5234257b4c40d748d1543298a";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TEMPLATE_ID = "5uZIvSm5GAdUR1X25HNpjuOp6jSiL88v4opn4a4GLa0";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String APP_KEY = "a2a6bcddad127f223cc1b6bcd74b1669";
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "api/message/send")
    @retrofit2.http.Headers(value = {"Host: u.ifeige.cn", "Content-Type: application/json"})
    public abstract java.lang.Object sendMessage(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    okhttp3.RequestBody requestBody, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.cuile.msgforward.msgreceiver.FeiGeResponseBody> p1);
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/cuile/msgforward/msgreceiver/FeiGeService$Companion;", "", "()V", "APP_KEY", "", "BASE_URL", "FEI_GE_SECRET", "TEMPLATE_ID", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String BASE_URL = "http://u.ifeige.cn/";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String FEI_GE_SECRET = "f4f5abb5234257b4c40d748d1543298a";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String TEMPLATE_ID = "5uZIvSm5GAdUR1X25HNpjuOp6jSiL88v4opn4a4GLa0";
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String APP_KEY = "a2a6bcddad127f223cc1b6bcd74b1669";
        
        private Companion() {
            super();
        }
    }
}
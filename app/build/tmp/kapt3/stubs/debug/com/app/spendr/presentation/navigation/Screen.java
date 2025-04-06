package com.app.spendr.presentation.navigation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0003\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lcom/app/spendr/presentation/navigation/Screen;", "", "route", "", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "Expense", "MainScreen", "Savings", "Lcom/app/spendr/presentation/navigation/Screen$Expense;", "Lcom/app/spendr/presentation/navigation/Screen$MainScreen;", "Lcom/app/spendr/presentation/navigation/Screen$Savings;", "app_debug"})
public abstract class Screen {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String route = null;
    
    private Screen(java.lang.String route) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRoute() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/app/spendr/presentation/navigation/Screen$Expense;", "Lcom/app/spendr/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class Expense extends com.app.spendr.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.app.spendr.presentation.navigation.Screen.Expense INSTANCE = null;
        
        private Expense() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/app/spendr/presentation/navigation/Screen$MainScreen;", "Lcom/app/spendr/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class MainScreen extends com.app.spendr.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.app.spendr.presentation.navigation.Screen.MainScreen INSTANCE = null;
        
        private MainScreen() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/app/spendr/presentation/navigation/Screen$Savings;", "Lcom/app/spendr/presentation/navigation/Screen;", "()V", "app_debug"})
    public static final class Savings extends com.app.spendr.presentation.navigation.Screen {
        @org.jetbrains.annotations.NotNull()
        public static final com.app.spendr.presentation.navigation.Screen.Savings INSTANCE = null;
        
        private Savings() {
        }
    }
}
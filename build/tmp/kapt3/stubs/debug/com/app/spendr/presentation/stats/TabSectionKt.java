package com.app.spendr.presentation.stats;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u001a@\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u0007\u001a4\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\t\u001a\u00020\nH\u0007\u00a8\u0006\u000e"}, d2 = {"TabSection", "", "stats", "Lcom/app/spendr/presentation/stats/Stats;", "onGraphChange", "Lkotlin/Function1;", "Lcom/app/spendr/presentation/stats/GraphType;", "deleteTransaction", "Lcom/app/spendr/data/Transaction;", "savedCurrency", "Lcom/app/spendr/presentation/home/UsersCurrency;", "TabSections", "selectedTabIndex", "", "app_debug"})
public final class TabSectionKt {
    
    @androidx.compose.runtime.Composable()
    public static final void TabSection(@org.jetbrains.annotations.NotNull()
    com.app.spendr.presentation.stats.Stats stats, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.app.spendr.presentation.stats.GraphType, kotlin.Unit> onGraphChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.app.spendr.data.Transaction, kotlin.Unit> deleteTransaction, @org.jetbrains.annotations.NotNull()
    com.app.spendr.presentation.home.UsersCurrency savedCurrency) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.animation.ExperimentalAnimationApi.class})
    @androidx.compose.runtime.Composable()
    public static final void TabSections(int selectedTabIndex, @org.jetbrains.annotations.NotNull()
    com.app.spendr.presentation.stats.Stats stats, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.app.spendr.data.Transaction, kotlin.Unit> deleteTransaction, @org.jetbrains.annotations.NotNull()
    com.app.spendr.presentation.home.UsersCurrency savedCurrency) {
    }
}
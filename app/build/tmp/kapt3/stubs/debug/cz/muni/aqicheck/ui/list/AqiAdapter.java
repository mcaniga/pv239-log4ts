package cz.muni.aqicheck.ui.list;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u0011\u001a\u00020\fH\u0016J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\fH\u0016J\u0018\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\fH\u0016J\u000e\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\fJ\u0014\u0010\u001a\u001a\u00020\u00062\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u001cJ\u0016\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fR\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R,\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\u000bX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcz/muni/aqicheck/ui/list/AqiAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcz/muni/aqicheck/ui/list/AqiViewHolder;", "onItemClick", "Lkotlin/Function1;", "Lcz/muni/aqicheck/data/AqiPresentableListItem;", "", "(Lkotlin/jvm/functions/Function1;)V", "listItems", "", "onFavoriteClick", "Lkotlin/Function2;", "", "getOnFavoriteClick", "()Lkotlin/jvm/functions/Function2;", "setOnFavoriteClick", "(Lkotlin/jvm/functions/Function2;)V", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "removeItem", "submitList", "newListItems", "", "updateFavorite", "isFavorite", "", "app_debug"})
public final class AqiAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<cz.muni.aqicheck.ui.list.AqiViewHolder> {
    private final kotlin.jvm.functions.Function1<cz.muni.aqicheck.data.AqiPresentableListItem, kotlin.Unit> onItemClick = null;
    private java.util.List<cz.muni.aqicheck.data.AqiPresentableListItem> listItems;
    public kotlin.jvm.functions.Function2<? super cz.muni.aqicheck.data.AqiPresentableListItem, ? super java.lang.Integer, kotlin.Unit> onFavoriteClick;
    
    public AqiAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super cz.muni.aqicheck.data.AqiPresentableListItem, kotlin.Unit> onItemClick) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function2<cz.muni.aqicheck.data.AqiPresentableListItem, java.lang.Integer, kotlin.Unit> getOnFavoriteClick() {
        return null;
    }
    
    public final void setOnFavoriteClick(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super cz.muni.aqicheck.data.AqiPresentableListItem, ? super java.lang.Integer, kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public cz.muni.aqicheck.ui.list.AqiViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    cz.muni.aqicheck.ui.list.AqiViewHolder holder, int position) {
    }
    
    public final void submitList(@org.jetbrains.annotations.NotNull()
    java.util.List<cz.muni.aqicheck.data.AqiPresentableListItem> newListItems) {
    }
    
    public final void removeItem(int position) {
    }
    
    public final void updateFavorite(int position, boolean isFavorite) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
}
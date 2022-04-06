// Generated by view binder compiler. Do not edit!
package cz.muni.log4ts.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import cz.muni.log4ts.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentDetailBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView aqiTextView;

  @NonNull
  public final MaterialCardView contentCard;

  @NonNull
  public final View indicator;

  @NonNull
  public final View locationDivider;

  @NonNull
  public final ImageView locationImageView;

  @NonNull
  public final TextView locationTextView;

  @NonNull
  public final TextView nameTextView;

  @NonNull
  public final View stationDivider;

  @NonNull
  public final ImageView stationImageView;

  @NonNull
  public final TextView stationTextView;

  @NonNull
  public final TextView timeTextView;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final View webDivider;

  @NonNull
  public final ImageView webImageView;

  @NonNull
  public final TextView webTextView;

  private FragmentDetailBinding(@NonNull ConstraintLayout rootView, @NonNull TextView aqiTextView,
      @NonNull MaterialCardView contentCard, @NonNull View indicator, @NonNull View locationDivider,
      @NonNull ImageView locationImageView, @NonNull TextView locationTextView,
      @NonNull TextView nameTextView, @NonNull View stationDivider,
      @NonNull ImageView stationImageView, @NonNull TextView stationTextView,
      @NonNull TextView timeTextView, @NonNull Toolbar toolbar, @NonNull View webDivider,
      @NonNull ImageView webImageView, @NonNull TextView webTextView) {
    this.rootView = rootView;
    this.aqiTextView = aqiTextView;
    this.contentCard = contentCard;
    this.indicator = indicator;
    this.locationDivider = locationDivider;
    this.locationImageView = locationImageView;
    this.locationTextView = locationTextView;
    this.nameTextView = nameTextView;
    this.stationDivider = stationDivider;
    this.stationImageView = stationImageView;
    this.stationTextView = stationTextView;
    this.timeTextView = timeTextView;
    this.toolbar = toolbar;
    this.webDivider = webDivider;
    this.webImageView = webImageView;
    this.webTextView = webTextView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.aqi_text_view;
      TextView aqiTextView = ViewBindings.findChildViewById(rootView, id);
      if (aqiTextView == null) {
        break missingId;
      }

      id = R.id.content_card;
      MaterialCardView contentCard = ViewBindings.findChildViewById(rootView, id);
      if (contentCard == null) {
        break missingId;
      }

      id = R.id.indicator;
      View indicator = ViewBindings.findChildViewById(rootView, id);
      if (indicator == null) {
        break missingId;
      }

      id = R.id.location_divider;
      View locationDivider = ViewBindings.findChildViewById(rootView, id);
      if (locationDivider == null) {
        break missingId;
      }

      id = R.id.location_image_view;
      ImageView locationImageView = ViewBindings.findChildViewById(rootView, id);
      if (locationImageView == null) {
        break missingId;
      }

      id = R.id.location_text_view;
      TextView locationTextView = ViewBindings.findChildViewById(rootView, id);
      if (locationTextView == null) {
        break missingId;
      }

      id = R.id.name_text_view;
      TextView nameTextView = ViewBindings.findChildViewById(rootView, id);
      if (nameTextView == null) {
        break missingId;
      }

      id = R.id.station_divider;
      View stationDivider = ViewBindings.findChildViewById(rootView, id);
      if (stationDivider == null) {
        break missingId;
      }

      id = R.id.station_image_view;
      ImageView stationImageView = ViewBindings.findChildViewById(rootView, id);
      if (stationImageView == null) {
        break missingId;
      }

      id = R.id.station_text_view;
      TextView stationTextView = ViewBindings.findChildViewById(rootView, id);
      if (stationTextView == null) {
        break missingId;
      }

      id = R.id.time_text_view;
      TextView timeTextView = ViewBindings.findChildViewById(rootView, id);
      if (timeTextView == null) {
        break missingId;
      }

      id = R.id.toolbar;
      Toolbar toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }

      id = R.id.web_divider;
      View webDivider = ViewBindings.findChildViewById(rootView, id);
      if (webDivider == null) {
        break missingId;
      }

      id = R.id.web_image_view;
      ImageView webImageView = ViewBindings.findChildViewById(rootView, id);
      if (webImageView == null) {
        break missingId;
      }

      id = R.id.web_text_view;
      TextView webTextView = ViewBindings.findChildViewById(rootView, id);
      if (webTextView == null) {
        break missingId;
      }

      return new FragmentDetailBinding((ConstraintLayout) rootView, aqiTextView, contentCard,
          indicator, locationDivider, locationImageView, locationTextView, nameTextView,
          stationDivider, stationImageView, stationTextView, timeTextView, toolbar, webDivider,
          webImageView, webTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
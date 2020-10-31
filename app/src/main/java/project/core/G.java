package project.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.TransitionRes;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohammadrezaee.matrix.R;

import java.io.File;


public class G extends Application {

  public static Context context;
  private static Activity currentActivity;
  private static LayoutInflater layoutInflater;
  private static TransitionInflater transitionInflater;
  private static Handler handler;
  private static DisplayMetrics displayMetrics;
  private static G g;


  public static final File STORAGE_PATH = Environment.getExternalStorageDirectory();
  public static final String HOME_DIR = STORAGE_PATH + context.getString(R.string.NameOfApplication);
  public static final String APP_DIR  = HOME_DIR + context.getString(R.string.NameOfDirectory);

  @Override
  public void onCreate() {
    super.onCreate();

    g = this;
    context = getApplicationContext();
    handler = new Handler();
    displayMetrics = getResources().getDisplayMetrics();
    layoutInflater = LayoutInflater.from(context);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      transitionInflater = TransitionInflater.from(context);
    }

  }

  public static G get() {
    return g;
  }

  public static Context getContext() {
    if (currentActivity != null) {
      return currentActivity;
    }
    return context;
  }

  public static void setCurrentActivity(Activity activity) {
    currentActivity = activity;
  }

  public static Activity getCurrentActivity() {
    return currentActivity;
  }

  public static LayoutInflater getLayoutInflater() {
    return layoutInflater;
  }

  public static TransitionInflater getTransitionInflater() {
    return transitionInflater;
  }

  public static Transition inflateTransition(@TransitionRes int res) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      return transitionInflater.inflateTransition(res);
    }

    return null;
  }

  public static View inflateLayout(@LayoutRes int res) {
    return layoutInflater.inflate(res, null);
  }

  public static View inflateLayout(@LayoutRes int res, @Nullable ViewGroup root) {
    return layoutInflater.inflate(res, root);
  }

  public static Handler getHandler() {
    return handler;
  }

  public static DisplayMetrics getDisplayMetrics() {
    return displayMetrics;
  }
}

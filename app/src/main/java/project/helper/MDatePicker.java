package project.helper;

import android.app.Activity;
import android.app.DatePickerDialog;

import java.util.Calendar;

public class MDatePicker {

  public static void openDatePicker(Activity activity,DatePickerDialog.OnDateSetListener listener){
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    show(activity,year,month,day,listener);
  }

  public static void openDatePicker(Activity activity,int year,int month,int day,DatePickerDialog.OnDateSetListener listener){
    show(activity,year,month,day,listener);
  }

  private static void show(Activity activity,int year,int month,int day,DatePickerDialog.OnDateSetListener listener){
    new DatePickerDialog(activity,listener,year,month,day).show();
  }
}

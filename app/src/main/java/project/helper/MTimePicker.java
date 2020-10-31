package project.helper;

import android.app.Activity;
import android.app.TimePickerDialog;

import java.util.Calendar;

public class MTimePicker {

  public static void openTimePicker(Activity activity,boolean is24format,TimePickerDialog.OnTimeSetListener listener){
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    show(activity,true,hour,minute,listener);
  }

  public static void openTimePicker(Activity activity,boolean is24format,int hour,int minute,TimePickerDialog.OnTimeSetListener listener){
    show(activity,true,hour,minute,listener);
  }
  public static void openTimePicker(Activity activity,TimePickerDialog.OnTimeSetListener listener){
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    show(activity,true,hour,minute,listener);
  }

  private static void show(Activity activity,boolean is24format,int hour,int minute,TimePickerDialog.OnTimeSetListener listener){
    new TimePickerDialog(activity,listener,hour,minute,is24format).show();
  }
}

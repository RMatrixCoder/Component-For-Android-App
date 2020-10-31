package project.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MSelect {

  private final Activity activity;
  private CharSequence title;
  private CharSequence[] options;
  private CharSequence defaultValue;
  private boolean isCancelable;
  private boolean isImmediate;
  private OnItemSelectedListener listener;
  private OnItemMultiChoiceSelectedListener multiChoiceListener;
  private CharSequence okLabel = "Ok";
  private int defaultIndex = -1;
  private boolean singleChoiceItem;
  private int selectedIndex = -1;
  private CharSequence cancelLable = "Cancel";
  private boolean multiChoiceItem;
  private boolean[] defaultvalueforMultiChoice;

  public interface OnItemSelectedListener{
    void onItemSelected(int selectedIndex, CharSequence selectedValue);
  }

  public interface OnItemMultiChoiceSelectedListener{
    void onItemMultiChoiceSelected(boolean[] itemSelected, CharSequence[] values);
  }

  public MSelect setOnItemMultichoiceSelectedListener(OnItemMultiChoiceSelectedListener listener){
    this.multiChoiceListener = listener;
    return this;
  }

  public MSelect(Activity activity){
    this.activity = activity;

  }

  public MSelect setTitle(CharSequence title){
    this.title = title;
    return this;
  }

  public MSelect setOptions(CharSequence... options){
    this.options = options;
    return this;
  }

  public MSelect setDefaultValue(CharSequence defaultValue){
    this.defaultValue = defaultValue;
    return this;
  }
  public MSelect setDefaultvalueforMultiChoice(boolean... defaultvalueforMultiChoice){
    this.defaultvalueforMultiChoice = defaultvalueforMultiChoice;
    return this;
  }

  public MSelect setIsCancelable(boolean isCancelable){
    this.isCancelable = isCancelable;
    return this;
  }

  public MSelect isSingleChoiceItem(boolean singleChoiceItem){
    this.singleChoiceItem = singleChoiceItem;
    return this;
  }

  public MSelect isMultiChoiceItem(boolean multiChoiceItem){
    this.multiChoiceItem = multiChoiceItem;
    return this;
  }

  public MSelect setIsImmediate(boolean isImmediate){
    this.isImmediate = isImmediate;
    return this;
  }
  public MSelect setOnItemSelectedListener(OnItemSelectedListener listener){
    this.listener = listener;
    return this;
  }
  public MSelect setOkLabel(CharSequence label) {
    this.okLabel = label;
    return this;
  }
  public MSelect setOkLabel(int label) {
    this.okLabel = activity.getString(label);
    return this;
  }
  public MSelect setCancelLabel(CharSequence label) {
    this.cancelLable = label;
    return this;
  }
  public MSelect setCancelLabel(int label) {
    this.cancelLable = activity.getString(label);
    return this;
  }

  public MSelect setDefaultIndex(int value){
    this.defaultIndex =  value;
    return this;
  }

  public void show(){
    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
    builder.setTitle(title);
    builder.setCancelable(isCancelable);

    if (defaultValue != null){
      for (int i=0; i<options.length;i++){
        if (options[i].equals(defaultValue)){
          defaultIndex = i;
          break;
        }
      }
    }

    if (singleChoiceItem){
      builder.setSingleChoiceItems(options, defaultIndex, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          selectedIndex = which;

          if (isImmediate) {
            if (listener != null) {
              listener.onItemSelected
                (selectedIndex,options[selectedIndex]);
            }

            dialog.dismiss();
          }
        }
      });
      if (!isImmediate) {
        builder.setPositiveButton(okLabel, new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            if (listener != null) {
              listener.onItemSelected(selectedIndex, options[selectedIndex]);
            }
          }
        });
      }

      if (isCancelable) {
        builder.setNegativeButton(cancelLable, null);
      }
    }

    if (multiChoiceItem){

      builder.setMultiChoiceItems(options, defaultvalueforMultiChoice, new DialogInterface.OnMultiChoiceClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
          defaultvalueforMultiChoice[which] = isChecked;
        }
      });

      builder.setCancelable(false);

      builder.setPositiveButton(okLabel, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
          if (multiChoiceListener != null){

            multiChoiceListener.onItemMultiChoiceSelected(defaultvalueforMultiChoice,options);
          }
        }
      });

      builder.setNegativeButton(cancelLable, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
          if (multiChoiceListener != null){
            multiChoiceListener.onItemMultiChoiceSelected(defaultvalueforMultiChoice,options);
          }
        }
      });
    }

    AlertDialog dialog = builder.create();
    dialog.show();

  }
}

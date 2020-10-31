package project.widget;

import android.view.View;

import java.util.ArrayList;

public class ColorListViewAdapter<T> extends ListViewAdapter<T> {

  public ColorListViewAdapter(ArrayList<T> list ,int res) {
    super(list,res);
  }

  public static class ColorViewHolder extends ListViewAdapter.ViewHolder {
    //......
  }

  @Override
  public ListViewAdapter.ViewHolder assign(View convertView) {
    ColorViewHolder viewHolder = new ColorViewHolder();
    //........
    return viewHolder;
  }

  @Override
  public void fill(ListViewAdapter.ViewHolder upcastedViewHolder, T item) {
    ColorViewHolder viewHolder = (ColorViewHolder) upcastedViewHolder;
    //.....
  }
}

package project.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import project.core.G;

public abstract class ListViewAdapter<T> extends ArrayAdapter<T> {

  private int layoutRes;

  public ListViewAdapter(ArrayList<T> list,int res) {
    super(G.getCurrentActivity(), 0, list);

    this.layoutRes = res;
  }

  public static class ViewHolder {}

  public abstract ViewHolder assign(View convertView);
  public abstract void fill(ViewHolder upcastedViewHolder, T item);

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    T item = getItem(position);

    ViewHolder viewHolder;
    if (convertView == null) {
      convertView = G.inflateLayout(layoutRes, parent);

      viewHolder = assign(convertView);

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
    }

    fill(viewHolder, item);

    return convertView;
  }
}

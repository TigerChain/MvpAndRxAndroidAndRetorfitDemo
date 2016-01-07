package com.jun.mvpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jun.mvpdemo.R;
import com.jun.mvpdemo.model.Repo;

import java.util.List;

/**
 * Author：JunJun
 * Description:
 */
public class UserListAdapter extends BaseAdapter {

    private Context context ;
    private List<Repo> repoList ;

    public UserListAdapter(Context context,List<Repo> repoList){
        this.context = context ;
        this.repoList = repoList ;

    }
    @Override
    public int getCount() {
        return repoList.size();
    }

    @Override
    public Object getItem(int position) {
        return repoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null ;
        if(null == convertView){
            viewHolder = new ViewHolder() ;
            convertView = LayoutInflater.from(context).inflate(R.layout.user_list_item,parent,false) ;
            viewHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id) ;
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name) ;

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Repo repo = repoList.get(position) ;
        viewHolder.tv_id.setText(String.format("ID:%s", repo.getId()));
        viewHolder.tv_name.setText(String.format("Name:%s", repo.getName()));
        return convertView;
    }

    private class ViewHolder{
        private TextView tv_id ;
        private TextView tv_name ;

    }
}

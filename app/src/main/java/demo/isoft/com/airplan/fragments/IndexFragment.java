package demo.isoft.com.airplan.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.isoft.com.airplan.AdInfoActivity;
import demo.isoft.com.airplan.R;
import demo.isoft.com.airplan.myview.MygrideView;
import demo.isoft.com.airplan.pojo.FragmentIndexGridviewItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends Fragment {
    private List<FragmentIndexGridviewItem> list;
    private MygrideView gridView;
    private MyAdapter adapter;
    public IndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        gridView = (MygrideView) view.findViewById(R.id.fragment_index_gridview);
        setData();
        adapter =new MyAdapter();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"position"+list.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(), AdInfoActivity.class);
                intent.putExtra("title",list.get(position).getTitle());
                startActivity(intent);
            }
        });
        return view;
    }
    public void setData(){
        list=new ArrayList<>();
        list.add(new FragmentIndexGridviewItem(R.mipmap.ad_image1,"免费乘机泊车","深航旅客专享"));
        list.add(new FragmentIndexGridviewItem(R.mipmap.ad_image2,"免费升舱及积分礼包","青岛航空新会员"));
        list.add(new FragmentIndexGridviewItem(R.mipmap.ad_image1,"免费乘机泊车2","深航旅客专享2"));
        list.add(new FragmentIndexGridviewItem(R.mipmap.ad_image1,"免费乘机泊车3","深航旅客专享3"));
    }

    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=LayoutInflater.from(getContext()).inflate(R.layout.fregment_index_gridview_item,null);
            ImageView imageView= (ImageView) view.findViewById(R.id.fregment_index_gridview_item_imageview);
            TextView textView1= (TextView) view.findViewById(R.id.fregment_index_gridview_item_textview1);
            TextView textView2= (TextView) view.findViewById(R.id.fregment_index_gridview_item_textview2);
            imageView.setImageResource(list.get(position).getImageId());
            textView1.setText(list.get(position).getTitle());
            textView2.setText(list.get(position).getSubtitle());
            return view;
        }
    }

}

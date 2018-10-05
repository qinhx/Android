package com.sysu.mypro2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/12/30.
 */

public class Fragment2 extends Fragment {

    private MainActivity mainActivity;
    private View view;

    private LinearLayout ll;
    //左侧的两个listview
    private ListView listView1;
    private ListView listView2;
    private ArrayAdapter<String> listView1_adapter;
    private ArrayAdapter<String> listView2_adapter;

    //右侧数据的adaptr
    private ArrayAdapter<String> fl_grid_adapter1, fl_grid_adapter2, fl_grid_adapter3, fl_grid_adapter4,
            fl_grid_adapter5, fl_grid_adapter6, fl_grid_adapter7, fl_grid_adapter8,
            fl_grid_adapter9, fl_grid_adapter10, fl_grid_adapter11;

    //右侧具体数据的分类Gridview
    private GridView fl_grid1, fl_grid2, fl_grid3, fl_grid4, fl_grid5,
            fl_grid6, fl_grid7, fl_grid8, fl_grid9, fl_grid10, fl_grid11;

    //右侧数据的adaptr2
    private ArrayAdapter<String> sc_grid_adapter1, sc_grid_adapter2, sc_grid_adapter3, sc_grid_adapter4,
            sc_grid_adapter5, sc_grid_adapter6, sc_grid_adapter7, sc_grid_adapter8,
            sc_grid_adapter9, sc_grid_adapter10, sc_grid_adapter11;

    //右侧具体数据的食材Gridview2
    private GridView sc_grid1, sc_grid2, sc_grid3, sc_grid4, sc_grid5,
            sc_grid6, sc_grid7, sc_grid8, sc_grid9, sc_grid10, sc_grid11;

    //数据
    String[] fl_data1 ={},fl_data2={},fl_data3={},fl_data4={},fl_data5={},fl_data6={},
            fl_data7={},fl_data8={},fl_data9={},fl_data10={},fl_data11={};
    String[] sc_data1 ={},sc_data2={},sc_data3={},sc_data4={},sc_data5={},sc_data6={},
            sc_data7={},sc_data8={},sc_data9={},sc_data10={},sc_data11={};
    //顶部的button
    private RadioGroup radios;
    //分类按钮
    private RadioButton button1;

    //分类界面左边listview的adapter
    private MyListAdapter myListAdapter1,myListAdapter2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivity = (MainActivity) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        initEvent();


    }

    //隐藏所有gridview
    public void resetGridView() {
        fl_grid1.setVisibility(View.GONE);
        fl_grid2.setVisibility(View.GONE);
        fl_grid3.setVisibility(View.GONE);
        fl_grid4.setVisibility(View.GONE);
        fl_grid5.setVisibility(View.GONE);
        fl_grid6.setVisibility(View.GONE);
        fl_grid7.setVisibility(View.GONE);
        fl_grid8.setVisibility(View.GONE);
        fl_grid9.setVisibility(View.GONE);
        fl_grid10.setVisibility(View.GONE);
        fl_grid11.setVisibility(View.GONE);

        //食材
        sc_grid1.setVisibility(View.GONE);
        sc_grid2.setVisibility(View.GONE);
        sc_grid3.setVisibility(View.GONE);
        sc_grid4.setVisibility(View.GONE);
        sc_grid5.setVisibility(View.GONE);
        sc_grid6.setVisibility(View.GONE);
        sc_grid7.setVisibility(View.GONE);
        sc_grid8.setVisibility(View.GONE);
        sc_grid9.setVisibility(View.GONE);
        sc_grid10.setVisibility(View.GONE);
        sc_grid11.setVisibility(View.GONE);
    }

    //初始化界面
    public void initView() {

        ll = (LinearLayout) view.findViewById(R.id.title_search);
        listView1 = (ListView) view.findViewById(R.id.zhonglei_listview);
        listView2 = (ListView) view.findViewById(R.id.shicai_listview);
        button1 = (RadioButton) view.findViewById(R.id.class_title_one);
        radios = (RadioGroup) view.findViewById(R.id.class_title_radios);
        fl_grid1 = (GridView) view.findViewById(R.id.fenlei_gridview1);
        fl_grid2 = (GridView) view.findViewById(R.id.fenlei_gridview2);
        fl_grid3 = (GridView) view.findViewById(R.id.fenlei_gridview3);
        fl_grid4 = (GridView) view.findViewById(R.id.fenlei_gridview4);
        fl_grid5 = (GridView) view.findViewById(R.id.fenlei_gridview5);
        fl_grid6 = (GridView) view.findViewById(R.id.fenlei_gridview6);
        fl_grid7 = (GridView) view.findViewById(R.id.fenlei_gridview7);
        fl_grid8 = (GridView) view.findViewById(R.id.fenlei_gridview8);
        fl_grid9 = (GridView) view.findViewById(R.id.fenlei_gridview9);
        fl_grid10 = (GridView) view.findViewById(R.id.fenlei_gridview10);
        fl_grid11 = (GridView) view.findViewById(R.id.fenlei_gridview11);
        //食材
        sc_grid1 = (GridView) view.findViewById(R.id.shicai_gridview1);
        sc_grid2 = (GridView) view.findViewById(R.id.shicai_gridview2);
        sc_grid3 = (GridView) view.findViewById(R.id.shicai_gridview3);
        sc_grid4 = (GridView) view.findViewById(R.id.shicai_gridview4);
        sc_grid5 = (GridView) view.findViewById(R.id.shicai_gridview5);
        sc_grid6 = (GridView) view.findViewById(R.id.shicai_gridview6);
        sc_grid7 = (GridView) view.findViewById(R.id.shicai_gridview7);
        sc_grid8 = (GridView) view.findViewById(R.id.shicai_gridview8);
        sc_grid9 = (GridView) view.findViewById(R.id.shicai_gridview9);
        sc_grid10 = (GridView) view.findViewById(R.id.shicai_gridview10);
        sc_grid11 = (GridView) view.findViewById(R.id.shicai_gridview11);
    }

    //初始化数据
    public void initData() {

        //左侧list数据的设置
        String[] listView1_data = {"热门", "菜式", "菜系", "特色", "烘焙", "主食", "器具", "烹饪方式", "口味", "场合", "节日"};

        myListAdapter1 = new MyListAdapter(listView1_data,mainActivity);
        listView1.setAdapter(myListAdapter1);

        String[] listView2_data = {"肉类", "蛋/奶", "鱼类", "水产", "蔬菜", "豆类", "水果品", "五谷杂粮", "药食", "调味品", "其他"};
        myListAdapter2 = new MyListAdapter(listView2_data,mainActivity);
        listView2.setAdapter(myListAdapter2);

        //右侧Gridview的详细数据
        String[] tmp1 = {"家常菜", "川菜", "早餐", "素材", "煲汤",
                "烘焙", "甜点", "创意菜", "冰品", "儿童营养", "家常", "晚餐",
                "凉菜", "面食", "粥", "烤箱", "面条", "下饭菜", "汤",
                "汤羹", "东北菜", "粤菜", "清淡", "湘菜", "荤菜",
                "中餐", "煲汤", "饼干", "黄浩新", "孕妇", "西餐",
                "私房菜", "蒸", "糕点", "糖水", "主食", "电饼铛",
                "家常味", "酸菜", "煮", "蛋挞", "凉拌",
                "鲁菜", "砂锅", "热菜", "海鲜", "意大利面", "煲仔饭",
                "秋季菜谱", "甜点", "宝宝辅食", "农家菜", "儿童早餐", "布丁",
                "饭团"};
        String[] tmp2 = {"家常菜", "素菜", "凉菜", "下饭菜", "面食", "小吃",
                "粥", "汤", "煲汤", "私房菜", "汤羹", "糕点",
                "甜点", "饮品", "创意菜", "腌制", "自制酱料", "冰品",
                "热菜", "农家菜", "荤菜", "主食", "宴客菜", "开胃菜",
                "海鲜", "下酒菜", "懒人食谱", "鲁菜"
        };
        String[] tmp3 = {"川菜", "东北菜", "粤菜", "湘菜", "西餐", "鲁菜",
                "韩式料理", "日式料理", "淮扬菜", "闽菜", "浙菜", "徽菜",
                "清真", "苏菜", "东南亚", "贵州菜", "本帮菜", "法国菜",
                "新疆菜", "潮州菜", "客家菜", "意大利菜", "泰国菜", "英国菜",
                "台湾美食", "香港美食", "豫菜", "印度菜", "云南菜", "西班牙菜",
                "赣菜", "京菜", "澳大利亚菜", "西北菜", "澳门美食", "鄂州菜"

        };
        String[] tmp4 = {"泡菜", "冰淇淋", "粽子", "沙拉", "油条", "豆浆",
                "零食", "布丁", "糖水", "自制食材", "果汁", "果冻",
                "糖果", "果酱", "果茶", "酸菜", "香锅", "青团", "饮料", "水产", "海产"
        };

        String[] tmp5 = {"蛋糕","披萨","面包","月饼","吐司","饼干",
                "杯子蛋糕","蛋挞","曲奇","派","泡芙","慕斯",
                "小蛋糕","马卡龙","威风蛋糕","戚风蛋糕","马芬","蛋糕卷",
                "芝士蛋糕","提拉米苏","海绵蛋糕","奶油蛋糕","乳酪蛋糕",
                "挞","翻糖蛋糕","磅蛋糕"
        };

        String[] tmp6 = {"寿司","饼","炒饭","馒头","饺子","炒面",
                "包子","三明治","便当","拌面","汤圆","混沌",
                "面条","焖饭","盖浇饭","意大利面","凉面","饭团",
                "煎饼","发糕","汉堡","花卷","煲仔饭","春卷",
                "卷饼","米线","春饼","锅贴","玉米饼","焖面",
                "盒子","窝头","炒饼","烩饭","焗饭","河粉","元宵","锅灰"

        };

        String[] tmp7 = {"烤箱","砂锅","微波炉","电饼铛","电炖锅","平底锅",
                "电饭煲","料理机","电压力锅","豆浆机","料理机","电压力锅",
                "面包机","高压锅","榨汁机","电磁炉","蒸锅","不粘锅","搅拌机",
                "炒锅","打蛋器","酸奶机","咖啡机","调酒器"

        };
        String[] tmp8 = {"烘焙","拌","火锅","蒸","干锅","红烧","煮",
                "煎","炸","卤","煲","烤","拔丝","焖","炖","爆","腌","扣",
                "叉烧","杂烩","勾芡","熏","油焖","葱爆","鲜榨"

        };
        String[] tmp9 = {"清淡","咖喱","麻辣","辣","香辣","甜","酸辣","孜然","酸",
                "苦","家常","泡椒","巧克力","椒盐","剁椒","香酥","红油","黑椒","酱香",
                "鱼香","原味","超辣","五香","柠檬味","薄荷味","番茄味","奶香","蒜香",
                "微辣","苹果味","西瓜味","怪味","橘子味","姜汁"

        };
        String[] tmp10 = {"早餐","晚餐","中餐","下午茶","宵夜","熬夜餐",
                "春季菜谱","早茶","夏季菜谱","朋友聚餐","二人世界","户外野炊",
                "深夜食堂","单身食谱","早午餐"

        };
        String[] tmp11 = {"春节","中秋节","元旦","元宵节","教师节","七夕节","端午节","清明节",
                "圣诞节","小年","万圣节","重阳节","母亲节","感恩节","父亲节","情人节","儿童节","复活节"

        };

        fl_data1 = tmp1;
        fl_data2 = tmp2;
        fl_data3 = tmp3;
        fl_data4 = tmp4;
        fl_data5 = tmp5;
        fl_data6 = tmp6;
        fl_data7 = tmp7;
        fl_data8 = tmp8;
        fl_data9 = tmp9;
        fl_data10 = tmp10;
        fl_data11 = tmp11;

        fl_grid_adapter1 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data1);
        fl_grid1.setAdapter(fl_grid_adapter1);
        fl_grid_adapter2 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data2);
        fl_grid2.setAdapter(fl_grid_adapter2);
        fl_grid_adapter3 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data3);
        fl_grid3.setAdapter(fl_grid_adapter3);
        fl_grid_adapter4 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data4);
        fl_grid4.setAdapter(fl_grid_adapter4);
        fl_grid_adapter5 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data5);
        fl_grid5.setAdapter(fl_grid_adapter5);
        fl_grid_adapter6 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data6);
        fl_grid6.setAdapter(fl_grid_adapter6);
        fl_grid_adapter7 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data7);
        fl_grid7.setAdapter(fl_grid_adapter7);
        fl_grid_adapter8 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data8);
        fl_grid8.setAdapter(fl_grid_adapter8);
        fl_grid_adapter9 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data9);
        fl_grid9.setAdapter(fl_grid_adapter9);
        fl_grid_adapter10 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data10);
        fl_grid10.setAdapter(fl_grid_adapter10);
        fl_grid_adapter11 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, fl_data11);
        fl_grid11.setAdapter(fl_grid_adapter11);

        //食材
        String[] sc_tmp1 = {
                "猪肉","排骨","猪蹄","猪肚","五花肉","猪肝","猪血","猪腰","猪皮",
                "猪肘","猪耳朵","猪心","猪肺","猪大肠","猪大骨头","猪小排","猪脑","猪舌头","猪小排",
                "牛肉","牛腩","牛排","肥牛","牛肚","牛蹄筋","牛尾","牛肺","牛鞭",
                "羊肉","羊排","羊肝","羊肚","羊血","羊肾","羊骨","羊蝎子",
                "鸡肉","鸡翅","鸡腿","鸡爪","鸡肝","鸡血","鸡心","火鸡肉","乌鸡肉",
                "鸭肉","鸭肝","鸭腿","鸭翅","鸭肠","鸭血","鸭掌",
                "肉制品","腊肉","火腿","香肠","咸肉","肉松","培根","熏肉",
                "其他肉类","兔肉","鹿肉","驴肉","鹅肉","乳鸽"
        };
        String[] sc_tmp2 = {
                "蛋类","鸡蛋","鸭蛋","鹌鹑蛋","咸鸭蛋","鸽子蛋","松花蛋","鹅蛋",
                "乳制品","奶酪","黄油","奶油"
        };
        String[] sc_tmp3 = {
                "淡水鱼","草鱼","鲤鱼","鲫鱼","鲢鱼","青鱼","鲶鱼","银鱼","桂鱼","武昌鱼",
                "咸水鱼","带鱼","鳕鱼","金枪鱼","三文鱼","秋刀鱼","比目鱼","石斑鱼","小黄鱼","马面鱼",
                "鱼制品","鱼丸","鱼头","鱼干","鱼籽"
        };
        String[] sc_tmp4 = {
                "虾","虾米","龙虾","虾皮","海虾","北极虾","河虾","皮皮虾","虾仁",
                "蟹","螃蟹","梭子蟹","河蟹",
                "贝","蛤蜊","牡蛎","鲍鱼","干贝","扇贝","鲜贝","海螺",
                "藻类","海带","紫菜","海藻","发菜",
                "其他水产","墨鱼","章鱼","田鸡","海参","海胆","牛蛙"
        };
        String[] sc_tmp5 = {
                "茎叶花类","白菜","油菜","青菜","小白菜","菠菜",
                "根茎类","土豆","红薯","芋头","胡萝卜","竹笋","山药",
                "瓜果类","豆角","茄子","青椒","西红柿","黄瓜","四季豆",
                "菌菇类","蘑菇","草菇","香菇","木耳","虫花草"
        };
        String[] sc_tmp6 = {
                "豆类","黄豆","毛豆","绿豆","蚕豆","花豆",
                "豆制品","豆腐","豆干","腐竹","豆豉","豆腐皮"
        };
        String[] sc_tmp7 = {
                "水果","苹果","香蕉","草莓","柿子","柚子","芒果","火龙果",
                "干果","花生","松子","核桃","红枣","葡萄干"
        };
        String[] sc_tmp8 = {
                "米面制品","面包","米饭","粉丝","米粉",
                "五谷类","大米","麦芽","小米","玉米","红豆"
        };
        String[] sc_tmp9 = {
                "药食","人参","燕窝","何首乌","鱼腥草","山楂叶"
        };
        String[] sc_tmp10 = {
                "调味品","味精","红糖","冰糖","白糖","姜","醋"
        };
        String[] sc_tmp11 = {
                "休闲食品","巧克力","爆米花","西瓜子",
                "饮品","牛奶","酸奶","豆浆"
        };

        sc_data1 = sc_tmp1;
        sc_data2 = sc_tmp2;
        sc_data3 = sc_tmp3;
        sc_data4 = sc_tmp4;
        sc_data5 = sc_tmp5;
        sc_data6 = sc_tmp6;
        sc_data7 = sc_tmp7;
        sc_data8 = sc_tmp8;
        sc_data9 = sc_tmp9;
        sc_data10 = sc_tmp10;
        sc_data11 = sc_tmp11;
        sc_grid_adapter1 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data1);
        sc_grid1.setAdapter(sc_grid_adapter1);
        sc_grid_adapter2 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data2);
        sc_grid2.setAdapter(sc_grid_adapter2);
        sc_grid_adapter3 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data3);
        sc_grid3.setAdapter(sc_grid_adapter3);
        sc_grid_adapter4 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data4);
        sc_grid4.setAdapter(sc_grid_adapter4);
        sc_grid_adapter5 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data5);
        sc_grid5.setAdapter(sc_grid_adapter5);
        sc_grid_adapter6 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data6);
        sc_grid6.setAdapter(sc_grid_adapter6);
        sc_grid_adapter7 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data7);
        sc_grid7.setAdapter(sc_grid_adapter7);
        sc_grid_adapter8 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data8);
        sc_grid8.setAdapter(sc_grid_adapter8);
        sc_grid_adapter9 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data9);
        sc_grid9.setAdapter(sc_grid_adapter9);
        sc_grid_adapter10 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data10);
        sc_grid10.setAdapter(sc_grid_adapter10);
        sc_grid_adapter11 = new ArrayAdapter<String>(mainActivity, R.layout.fragment2_grid_item, sc_data11);
        sc_grid11.setAdapter(sc_grid_adapter11);


    }

    //事件处理
    public void initEvent() {
        //模拟第一次点击
        button1.performClick();

        //搜索按钮
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);
                intent.putExtra("value","");
                startActivity(intent);

            }
        });

        //食材点击事件
        sc_grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data1[position]);
                startActivity(intent);
            }
        });
        sc_grid2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data2[position]);
                startActivity(intent);
            }
        });
        sc_grid3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data3[position]);
                startActivity(intent);
            }
        });
        sc_grid4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data4[position]);
                startActivity(intent);
            }
        });
        sc_grid5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data5[position]);
                startActivity(intent);
            }
        });
        sc_grid6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data6[position]);
                startActivity(intent);
            }
        });
        sc_grid7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data7[position]);
                startActivity(intent);
            }
        });
        sc_grid8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data8[position]);
                startActivity(intent);
            }
        });
        sc_grid9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data9[position]);
                startActivity(intent);
            }
        });
        sc_grid10.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data10[position]);
                startActivity(intent);
            }
        });
        sc_grid11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",sc_data11[position]);
                startActivity(intent);
            }
        });

        //分类的gridviewItem点击事件
        fl_grid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data1[position]);
                startActivity(intent);
            }
        });
        fl_grid2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data2[position]);
                startActivity(intent);
            }
        });
        fl_grid3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data3[position]);
                startActivity(intent);
            }
        });
        fl_grid4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data4[position]);
                startActivity(intent);
            }
        });
        fl_grid5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data5[position]);
                startActivity(intent);
            }
        });
        fl_grid6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data6[position]);
                startActivity(intent);
            }
        });
        fl_grid7.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data7[position]);
                startActivity(intent);
            }
        });
        fl_grid8.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data8[position]);
                startActivity(intent);
            }
        });
        fl_grid9.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data9[position]);
                startActivity(intent);
            }
        });
        fl_grid10.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data10[position]);
                startActivity(intent);
            }
        });
        fl_grid11.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mainActivity,SearchActivity.class);

                intent.putExtra("value",fl_data11[position]);
                startActivity(intent);
            }
        });
        //listShicai
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myListAdapter2.changeSelected(position);
                switch (position){
                    case 0:
                        resetGridView();
                        sc_grid1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        resetGridView();
                        sc_grid2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        resetGridView();
                        sc_grid3.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        resetGridView();
                        sc_grid4.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        resetGridView();
                        sc_grid5.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        resetGridView();
                        sc_grid6.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        resetGridView();
                        sc_grid7.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        resetGridView();
                        sc_grid8.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        resetGridView();
                        sc_grid9.setVisibility(View.VISIBLE);
                        break;
                    case 9:
                        resetGridView();
                        sc_grid10.setVisibility(View.VISIBLE);
                        break;
                    case 10:
                        resetGridView();
                        sc_grid11.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });

        //listItem点击事件
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                myListAdapter1.changeSelected(position);
                switch (position) {
                    case 0:
                        resetGridView();
                        fl_grid1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        resetGridView();
                        fl_grid2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        resetGridView();
                        fl_grid3.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        resetGridView();
                        fl_grid4.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        resetGridView();
                        fl_grid5.setVisibility(View.VISIBLE);
                        break;
                    case 5:
                        resetGridView();
                        fl_grid6.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        resetGridView();
                        fl_grid7.setVisibility(View.VISIBLE);
                        break;
                    case 7:
                        resetGridView();
                        fl_grid8.setVisibility(View.VISIBLE);
                        break;
                    case 8:
                        resetGridView();
                        fl_grid9.setVisibility(View.VISIBLE);
                        break;
                    case 9:
                        resetGridView();
                        fl_grid10.setVisibility(View.VISIBLE);
                        break;
                    case 10:
                        resetGridView();
                        fl_grid11.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });

        //顶部按钮事件
        radios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                resetGridView();
                switch (checkedId) {
                    case R.id.class_title_one:
                        fl_grid1.setVisibility(View.VISIBLE);
                        listView1.setVisibility(View.VISIBLE);
                        listView2.setVisibility(View.GONE);
                        break;
                    case R.id.class_title_two:
                        sc_grid1.setVisibility(View.VISIBLE);
                        listView1.setVisibility(View.GONE);
                        listView2.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

    }


}

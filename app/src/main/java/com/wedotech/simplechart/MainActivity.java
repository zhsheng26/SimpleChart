package com.wedotech.simplechart;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.wedotech.linechart.ChartView;
import com.wedotech.linechart.ChartViewConfig;
import com.wedotech.linechart.KeduValue;
import com.wedotech.linechart.PointValue;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG ="MainActivity" ;
    private ChartView chartview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initChartView();
    }



    private void initChartView(){
        chartview = (ChartView)findViewById(R.id.chartview);
        int mScreenWidth = getResources().getDisplayMetrics().widthPixels;
        //构造横刻度
        List<KeduValue> listHorizontal = getHorizontalKedu();

        //构造绘制点
        List<PointValue> listPoint =getPoint();

        //构造绘制区域范围的点
        List<PointValue> listRegion = getRegion();

        config(mScreenWidth,listHorizontal,listPoint,listRegion);
    }
    /**
     * 构造横刻度
     * @return
     */
    private List<KeduValue> getHorizontalKedu(){
        List<KeduValue> list = new ArrayList<>();
        for(int i=0;i<20;i++){
            KeduValue pointValue = new KeduValue();
            pointValue.display_value=i+"天月";//显示的文本
            pointValue.value =i+"";//具体的横刻度值
            list.add(pointValue);
        }
        return list;
    }

    /**
     * 构造点
     * @return
     */
    private List<PointValue> getPoint(){
        List<PointValue> list = new ArrayList<>();
        int temp = 1;
        for(int i=0;i<10;i++){
            PointValue pointValue = new PointValue();
            //横刻度值，必须在横刻度范围内，否则无法显示
            pointValue.horizontal_value = i+"";
            temp++;
            //竖刻度值，必须在竖刻度范围内，否则会取边界值
            pointValue.verical_value=temp+"";
            if(temp==5){
                temp=1;
            }
            //显示在游标的标题
            pointValue.title=pointValue.verical_value+"";
            //显示在游标的子标题
            pointValue.title_sub = "calendar";
            list.add(pointValue);
        }
        return list;
    }


    /**
     * 构造区域点  主要场景用于 显示 范围
     * @return
     */
    private List<PointValue> getRegion(){
        List<PointValue> list = new ArrayList<>();
        int temp = 5;
        //正向
        for(int i=-5;i<20;i++){
            PointValue pointValue = new PointValue();
            //横刻度值，必须在横刻度范围内，否则无法显示
            pointValue.horizontal_value = i+"";
            //竖刻度值，必须在竖刻度范围内，否则会取边界值
            pointValue.verical_value=10+"";
            list.add(pointValue);
        }

        for(int i=19;i>=-5;i--){
            PointValue pointValue = new PointValue();
            //横刻度值，必须在横刻度范围内，否则无法显示
            pointValue.horizontal_value = i+"";
            //竖刻度值，必须在竖刻度范围内，否则会取边界值
            pointValue.verical_value=5+"";
            list.add(pointValue);
        }
        return list;
    }


    private void config(int mScreenWidth, List<KeduValue> listHorizontal, List<PointValue> listPoint,  List<PointValue> listPointRegion) {
        //初始化
        ChartViewConfig config = new ChartViewConfig();
        config
                //设置列数
                .setCloumn(4)
                //设置行数
                .setRow(10)
                //设置每一个格子的高度
                .setItem_height(80)
                //设置每一个格子的宽度
                .setItem_width(mScreenWidth / 4)
                //设置格子线的颜色
                .setGrid_line_color(R.color.xiyou_blue)
                //设置横竖刻度线的颜色
                .setGrid_line_kedu_color(R.color.xiyou_blue)
                //设置是否显示格子线，PS:即使不显示格子线，以上的参数还是必须设置
                .setIsShowGridLine(true)
                //横向网格显示
                .setIsShowGridHorizontalLine(true)
                //竖向网格不显示
                .setIsShowGridVericalLine(false)
                //网格空心
                .setIsGridLinePathEffect(true)

                //设置竖向刻度左边距
                .setVerical_kedu_leftmargin(mScreenWidth / 8)
                //设置竖向刻度单位文案
                .setVerical_unit_text("KG")
                //设置竖向刻度开始值
                .setVerical_unit_start(0)
                //设置竖向刻度结束值
                .setVerical_unit_end(15)
                //设置竖向刻度增量
                .setVerical_unit_incremetal(3)
                //设置竖向刻度值的类型，支持int和float
                .setVerical_lable_value_type(0)
                //竖向刻度是否分段
                .setVerical_need_to_fragment(false)
                //竖向刻度值颜色
                .setVerical_unit_color(R.color.xiyou_gray)
                //竖向刻度 文案 颜色
                .setVerical_unit_lable_color(R.color.xiyou_gray)
                //竖向刻度 文案 颜色
                .setVerical_unit_lable_sub_color(R.color.xiyou_gray)
                //竖向刻度 凸出线是否显示
                .setVerical_kedu_line_show(false)
                //竖向刻度线 不显示
                .setVerical_line_show(false)

                //设置水平刻度
                .setListHorizontalKeduAndValueType(listHorizontal, 0, "1")
                //设置水平刻度凸出线
                .setHorizontal_kedu_line_show(true)

                //设置贝塞尔区域
                .setListPointRegion(listPointRegion)
                //设置区域颜色
                .setRegion_color(R.color.chart_view_region)

                //设置点的内容
                .setListPoint(listPoint)
                //是否平滑过渡，即贝塞尔曲线过度
                //.setIsSmoothPoint(true)
                //是否点线闭合,闭合则需要设置闭合区域颜色
                //.setIsFillPointRegion(true)
                //点线闭合区域颜色
                //.setRegion_connect_color(R.color.xiyou_white)

                //设置点和线的颜色
                .setPath_line_color(R.color.xiyou_pink)
                //点的内圆
                .setPoint_circle_color_interval(R.color.xiyou_white)
                //点的外援
                .setPoint_circle_color_outside(R.color.xiyou_pink)
                //内圆是否空心
                .setIsPointCircleIntervalStoke(false)
                //游标的单位文本
                .setIndicator_title_unit("kg")
                //游标颜色
                .setIndicator_Linecolor(R.color.xiyou_red)
                //游标外圈颜色
                .setIndicator_outside_circle_color(R.color.xiyou_orange)
                //游标标题颜色
                .setIndicator_title_color(R.color.xiyou_white)
                //游标是否跟着曲线一起上下移动
                .setIsIndicatorMoveWithPoint(false)
                //自定义游标背景
                // .setIndicatorBgRes(R.drawable.tree50)
                //设置游标半径，默认游标是圆形的
                .setIndicator_radius(100)

                //默认选中位置
                .setItemSelection(1)
        ;
        chartview.init(config);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

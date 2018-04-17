package com.othershe.calendarviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.othershe.calendarview.listener.CalendarViewAdapter;
import com.othershe.calendarview.weiget.CalendarView;
import com.othershe.calendarview.bean.DateBean;
import com.othershe.calendarview.listener.OnMultiChooseListener;
import com.othershe.calendarview.listener.OnPagerChangeListener;
import com.othershe.calendarview.weiget.MonthView;

import java.util.ArrayList;
import java.util.List;

public class MultiChooseActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView chooseDate;
    private List<DateBean> mDotList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choose);
        DateBean dateBean = new DateBean();
        dateBean.setSolar(2018,5,1);
        DateBean dateBean2 = new DateBean();
        dateBean2.setSolar(2018,5,4);
        DateBean dateBean3 = new DateBean();
        dateBean3.setSolar(2018,5,9);
        mDotList.add(dateBean);
        mDotList.add(dateBean2);
        mDotList.add(dateBean3);
        MonthView.setmDotList(mDotList);
        final TextView title = (TextView) findViewById(R.id.title);

        final StringBuilder sb = new StringBuilder();

        chooseDate = (TextView) findViewById(R.id.choose_date);

        List<String> list = new ArrayList<>();
        list.add("2017.11.11");
        list.add("2017.11.12");
        list.add("2017.12.22");
        list.add("2017.12.25");

        calendarView = (CalendarView) findViewById(R.id.calendar);
//        calendarView.setOnCalendarViewAdapter(R.layout.item_layout, new CalendarViewAdapter() {
//            @Override
//            public TextView[] convertView(View view, DateBean date) {
//                TextView solarDay = (TextView) view.findViewById(R.id.solar_day);
//                TextView lunarDay = (TextView) view.findViewById(R.id.lunar_day);
//                return new TextView[]{solarDay, lunarDay};
//            }
//        });
        calendarView
                .setStartEndDate("2017.1", "2019.12")
                .setDisableStartEndDate("2017.10.7", "2019.10.7")
                .setInitDate("2017.11")
                .setMultiDate(list)
                .init();

        title.setText(2017 + "年" + 11 + "月");

        for (String d : list) {
            sb.append("选中：" + d + "\n");
        }
        chooseDate.setText(sb.toString());

        calendarView.setOnMultiChooseListener(new OnMultiChooseListener() {
            @Override
            public void onMultiChoose(View view, DateBean date, boolean flag) {
                String d = date.getSolar()[0] + "." + date.getSolar()[1] + "." + date.getSolar()[2] + ".";
                if (flag) {//选中
                    sb.append("选中：" + d + "\n");
                } else {//取消选中
                    sb.append("取消：" + d + "\n");
                }
                chooseDate.setText(sb.toString());

                //test
                if (flag) {
                    for (DateBean db : calendarView.getMultiDate()) {
                        Log.e("date:", "" + db.getSolar()[0] + db.getSolar()[1] + db.getSolar()[2]);
                    }
                }
            }
        });

        calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
            @Override
            public void onPagerChanged(int[] date) {
                title.setText(date[0] + "年" + date[1] + "月");
            }
        });
    }

    public void lastMonth(View view) {
        calendarView.lastMonth();
    }

    public void nextMonth(View view) {
        calendarView.nextMonth();
    }
}

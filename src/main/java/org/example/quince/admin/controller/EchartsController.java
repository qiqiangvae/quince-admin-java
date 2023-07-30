package org.example.quince.admin.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.example.quince.admin.vo.Response;
import org.icepear.echarts.Bar;
import org.icepear.echarts.Pie;
import org.icepear.echarts.charts.pie.PieDataItem;
import org.icepear.echarts.charts.pie.PieSeries;
import org.icepear.echarts.origin.chart.pie.PieDataItemOption;
import org.icepear.echarts.render.Engine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author quince
 */
@RestController
@RequestMapping("/charts")
public class EchartsController {
    @RequestMapping("/dashboard")
    public Response<List<JSONObject>> dashboard() {
        List<JSONObject> dataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Bar bar = new Bar()
                    .setLegend()
                    .setTooltip("item")
                    .addXAxis(new String[]{"Matcha Latte", "Milk Tea", "Cheese Cocoa", "Walnut Brownie"})
                    .addYAxis()
                    .addSeries("2015", generate(random))
                    .addSeries("2016", generate(random))
                    .addSeries("2017", generate(random));
            Engine engine = new Engine();
            JSONObject jsonObject = JSON.parseObject(engine.renderJsonOption(bar));
            dataList.add(jsonObject);
        }
        for (int i = 0; i < 3; i++) {
            PieSeries series = new PieSeries();
            series.setName("饼图");
//            series.setRadius(new String[]{"40%", "70%"});

            PieDataItemOption[] items = new PieDataItemOption[5];
            for (int i1 = 0; i1 < items.length; i1++) {
                PieDataItem data = new PieDataItem();
                data.setName(i1);
                data.setValue(random.nextInt(0, 100));
                items[i1] = data;
            }
            series.setData(items);
            Pie bar = new Pie()
                    .setLegend()
                    .setTooltip("item")
                    .addSeries(series);
            Engine engine = new Engine();
            JSONObject jsonObject = JSON.parseObject(engine.renderJsonOption(bar));
            dataList.add(jsonObject);
        }
        Collections.shuffle(dataList);
        return new Response<>(0, "OK", dataList);
    }

    private static Object[] generate(Random random) {
        return IntStream.range(0, 4).map(operand -> random.nextInt(0, 100)).boxed().toArray();
    }
}

package com.atguigu.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ConstantObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UTFDataFormatException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wx
 * @create 2020-06-29 14:03
 */
public class JsonArrayToStructArray extends GenericUDF {

    private List<List<Object>> result = new ArrayList<List<Object>>();

    // 初始化 默认列名和默认列属性
    public ObjectInspector initialize(ObjectInspector[] arguments) throws UDFArgumentException {

        if (arguments.length < 3) {
            throw new UDFArgumentException("json_array_to_struct_array需要至少3个参数");
        }

        for (int i = 0; i < arguments.length; i++) {
            if (!"string".equals(arguments[i].getTypeName())) {
                throw new UDFArgumentException("json_array_to_struct_array的第" + (i + 1) + "个参数应为string类型");
            }
        }


        // 创建列名
        List<String> fieldNames = new ArrayList<String>();
        // 创建列类型
        List<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();

        // 遍历数据类型取出类型参数
        for (int i = 1 + (arguments.length - 1) / 2; i < arguments.length; i++) {

            if (!(arguments[i] instanceof ConstantObjectInspector)) {
                throw new UDFArgumentException("参数错误");
            }
            //a_id:String
            String s = ((ConstantObjectInspector) arguments[i]).getWritableConstantValue().toString();

            //切分
            String[] split = s.split(":");

            //添加默认列名
            fieldNames.add(split[0]);

            //添加列类型
            if ("string".equals(split[1])) {
                fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
            } else if ("boolean".equals(split[1])) {
                fieldOIs.add(PrimitiveObjectInspectorFactory.javaBooleanObjectInspector);
            } else if ("tinyint".equals(split[1])) {
                fieldOIs.add(PrimitiveObjectInspectorFactory.javaByteObjectInspector);
            } else if ("smallint".equals(split[1])) {
                fieldOIs.add(PrimitiveObjectInspectorFactory.javaShortObjectInspector);
            } else if ("int".equals(split[1])) {
                fieldOIs.add(PrimitiveObjectInspectorFactory.javaIntObjectInspector);
            } else if ("bigint".equals(split[1])) {
                fieldOIs.add(PrimitiveObjectInspectorFactory.javaLongObjectInspector);
            } else if ("float".equals(split[1])) {
                fieldOIs.add(PrimitiveObjectInspectorFactory.javaFloatObjectInspector);
            } else if ("double".equals(split[1])) {
                fieldOIs.add(PrimitiveObjectInspectorFactory.javaDoubleObjectInspector);
            } else {
                throw new UDFArgumentException("json_array_to_struct_array 不支持" + split[1] + "类型");
            }


        }


        return ObjectInspectorFactory.getStandardListObjectInspector(ObjectInspectorFactory.getColumnarStructObjectInspector(fieldNames, fieldOIs));

    }

    //对每一行数据进行处理
    //UDF("[{},{},{}]",     a_id,item,item_type,ts,    a_id:String,item:String...)
    public Object evaluate(DeferredObject[] arguments) throws HiveException {

        // 获取输入的数据
        DeferredObject data = arguments[0];

        // 判断数据是否为空
        if (data.get() == null) {
            return null;
        }

        // 获取真正的数据信息 =>"[{},{},{}]" , 一行数据
        String line = data.get().toString();

        // 清空集合
        result.clear();

        // 将一行数据转换成JSON数组
        JSONArray jsonArray = new JSONArray(line);

        // 遍历json数组 获取其值传入到数组中
        for (int i = 0; i < jsonArray.length(); i++) {
            // 获取json对象
            JSONObject json = jsonArray.getJSONObject(i);
            // 新建 struct 存储 列名数据
            List<Object> struct = new ArrayList<Object>();

            // 遍历arguments 获取对应数据
            for (int j = 1; j < 1 + (arguments.length - 1) / 2; j++) {

                // 获取 对应列名的值
                String key = arguments[j].get().toString();

                // 判断json中是否含有对应数据
                if (json.has(key)) {
                    struct.add(json.getString(key));
                } else {
                    struct.add(null);
                }
            }
            result.add(struct);

        }
        return result;
    }

    //执行计划
    public String getDisplayString(String[] children) {
        return getStandardDisplayString("JsonArrayToStructArray", children, ",");
    }
}

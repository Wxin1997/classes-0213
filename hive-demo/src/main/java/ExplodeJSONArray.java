import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wx
 * @create 2020-06-27 21:35
 */
public class ExplodeJSONArray extends GenericUDTF {

    /**
     * 声明炸裂数据默认的列名和类型
     *
     * @param argOIs
     * @return
     * @throws UDFArgumentException
     */
    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {

        // 列名集合
        List<String> fieldNames = new ArrayList<String>();
        // 列类型校验器集合
        List<ObjectInspector> fieldOIs = new ArrayList<ObjectInspector>();
        fieldNames.add("action");
        fieldOIs.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);

        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldNames, fieldOIs);
    }

    // 遍历每一行数据做炸裂操作
    public void process(Object[] args) throws HiveException {

        if (args.length <= 0) {
            return;
        }
        if (args[0] == null) {
            return;
        }
        String input = args[0].toString();
        JSONArray jsonArray = new JSONArray(input);

        for (int i = 0; i < jsonArray.length(); i++) {
            ArrayList<Object> objects = new ArrayList<Object>();
            objects.add(jsonArray.getString(i));
            forward(objects);
        }

    }

    public void close() throws HiveException {

    }
}

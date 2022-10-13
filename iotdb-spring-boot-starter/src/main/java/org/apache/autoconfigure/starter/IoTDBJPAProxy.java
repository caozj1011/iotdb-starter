package org.apache.autoconfigure.starter;

import org.apache.iotdb.session.Session;
import org.apache.iotdb.tsfile.file.metadata.enums.TSDataType;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IoTDBJPAProxy {

    public void save(Object t) {
        try {
            Field[] declaredFields = pojoClass.getDeclaredFields();
            String device = "default";
            List<String> sensors = new ArrayList<>();
            List<TSDataType> types = new ArrayList<>();
            List<Object> values = new ArrayList<>();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                String typeName = declaredField.getType().getTypeName();
                Object fileValue = declaredField.get(t);
                Device deviceAnno = declaredField.getDeclaredAnnotation(Device.class);
                if (deviceAnno != null) {
                    device = deviceAnno.value()+"."+fileValue;
                    continue;
                }
                values.add(fileValue);
                Sensor sensorAnno = declaredField.getDeclaredAnnotation(Sensor.class);
                if (sensorAnno != null) {
                    String sensor = sensorAnno.value();
                    sensors.add(sensor);
                    getTSDataType(types, typeName);
                }
            }
            session.insertRecord(device,System.currentTimeMillis(),sensors,types,values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void getTSDataType(List<TSDataType> types, String typeName) {
        switch (typeName) {
            case "java.lang.String":
                types.add(TSDataType.TEXT);
            break;
            case "java.lang.Integer":
            case "int":
                types.add(TSDataType.INT32);
                break;
            case "java.lang.Long":
            case "long":
                types.add(TSDataType.INT64);
                break;
            case "java.lang.Double":
            case "double":
                types.add(TSDataType.DOUBLE);
                break;
            case "java.lang.Float":
            case "float":
                types.add(TSDataType.FLOAT);
                break;
            case "java.lang.Boolean":
            case "boolean":
                types.add(TSDataType.BOOLEAN);
                break;
            default:
                throw new RuntimeException("Invalid data type");
        }
    }

    Session session;
    Class pojoClass;

    public IoTDBJPAProxy(Session session, Class pojoClass) {
        this.session = session;
        this.pojoClass = pojoClass;
    }


}

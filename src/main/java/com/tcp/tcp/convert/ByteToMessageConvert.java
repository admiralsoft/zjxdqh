package com.tcp.tcp.convert;

import com.tcp.log.CustomerLogger;
import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;
import com.tcp.tcp.base.message.BaseMessage;
import com.tcp.tcp.base.message.BaseMessageBody;
import com.tcp.tcp.convert.util.ConvertUtil;
import com.tcp.util.DataUtil;
import com.tcp.util.DateUtil;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tcp.tcp.convert.util.ConvertUtil.toPrimitives;

/**
 * 通信协议 转换工具
 * @author yorking
 */
public class ByteToMessageConvert {

    /**
     * 将 电桩通信协议的 byte数组转换成  class对象
     * @param bytes 信协议的 byte数组
     * @param dataClzz  转换结果中data对象对应的class
     * @param <T>
     * @return
     * @throws Exception
     */
    public static  <T extends BaseMessageBody,Y extends BaseMessage> Y convert(List<Byte> bytes, Class<T> dataClzz) throws Exception {
        if (bytes == null || bytes.isEmpty()) return null;
        return (Y) convert(bytes, new Index(0, bytes.size() - 1), BaseMessage.class, dataClzz);
    }


    /**
     * 解析 数据 包中的 数据内容
     * @param bytes     数据内容的byte[]
     * @param dataClzz  转换对象的class类型
     * @param <T>
     * @return
     */
    public static  <T extends BaseMessageBody> T unWrapConvert(byte[] bytes, Class<T> dataClzz){
        if (bytes == null ) return null;
        try {
            T result = convert(Arrays.asList(ConvertUtil.toObjects(bytes)), new Index(32, bytes.length - 5), dataClzz, null);
            //输出 指令 日志
            CustomerLogger.printCommandLogger(ByteToMessageConvert.unWrapConvertCmd(bytes), ByteToMessageConvert.unWrapConvertDeviceNo(bytes), result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拆分数据包中的 命令指令
     * @param req
     * @return
     */
    public static int unWrapConvertCmd(byte[] req) {
        return ((req[24] & 0xFF) << 8 | (req[25] & 0xFF));
    }

    /**
     * 拆分数据 包中的 设备编号
     * @param req
     * @return
     */
    public static String unWrapConvertDeviceNo(byte[] req) {
        String pileNum = "";// 获取桩号
        for (int i = 8; i < 24; i++) {
            pileNum += DataUtil.byteAsciiToString(req[i]);
        }
        return pileNum;
    }


    public static  <T,Y extends BaseMessageBody> T convert(List<Byte> bytes, Index index, Class<T> clzz, Class<Y> dataClzz) throws Exception {
        List<Field> fields = ConvertUtil.getAnnoFields(clzz);
        if (fields == null || fields.isEmpty())return null;
        T t = clzz.newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            Data ann = field.getDeclaredAnnotation(Data.class);
            Class<?> type = field.getType();
            if (ann ==null) continue;
            int order = ann.order();
            DataByteTypEnum byteType = ann.byteType();
            int len = ann.byteLen();
            Object value = null;

            try {
                if (ann.byteLenAttr().length() > 0) {
                    Field f = clzz.getDeclaredField(ann.byteLenAttr());
                    f.setAccessible(true);
                    len = (int) ReflectionUtils.getField(f, t);
                }

                switch (byteType) {
                    case ChildData:
                        if (clzz == BaseMessage.class && dataClzz != null) {
                            value = convert(bytes, index, dataClzz, null);
                        } else {
                            value = convert(bytes, index, field.getType(), null);
                        }
                        break;
                    case ChildDataList:
                        if(field.getType() == List.class){
                            Class<?> gClazz = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                            value = convertList(bytes, index, gClazz, len);
                        }
                        break;
                    case IPv4:
                        StringBuffer sb = new StringBuffer();
                        sb.append(DataUtil.byteAsciiToString(bytes.subList(index.getStart(), index.getStart() + 1).get(0))).append(".");
                        sb.append(DataUtil.byteAsciiToString(bytes.subList(index.getStart(), index.getStart() + 2).get(0))).append(".");
                        sb.append(DataUtil.byteAsciiToString(bytes.subList(index.getStart(), index.getStart() + 3).get(0))).append(".");
                        sb.append(DataUtil.byteAsciiToString(bytes.subList(index.getStart(), index.getStart() + 4).get(0)));
                        value = sb.toString();
                        index.setStart(index.getStart() + 4);
                        break;
                    case Byte:
                        value = bytes.subList(index.getStart(), index.getStart() + len).get(0);
                        index.setStart(index.getStart() + 1);
                        break;
                    case Number:
                        value = DataUtil.byte2Number(toPrimitives(bytes.subList(index.getStart(), index.getStart() + len).toArray(new Byte[len])), len);
                        index.setStart(index.getStart() + len);
                        break;
                    case Decimal4:
                        value = DataUtil.byte2Number(toPrimitives(bytes.subList(index.getStart(), index.getStart() + len).toArray(new Byte[len])), len);
                        value = new BigDecimal((Integer) value).divide(new BigDecimal(10000)).setScale(4,BigDecimal.ROUND_HALF_UP);
                        if (!type.getName().equalsIgnoreCase(BigDecimal.class.getName())) {
                            value = ((BigDecimal) value).doubleValue();
                        }
                        index.setStart(index.getStart() + len);
                        break;
                    case Decimal2:
                        value = DataUtil.byte2Number(toPrimitives(bytes.subList(index.getStart(), index.getStart() + len).toArray(new Byte[len])), len);
                        value = new BigDecimal((Integer) value).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
                        if (!type.getName().equalsIgnoreCase(BigDecimal.class.getName())) {
                            value = ((BigDecimal) value).doubleValue();
                        }
                        index.setStart(index.getStart() + len);
                        break;
                    case Decimal1:
                        value = DataUtil.byte2Number(toPrimitives(bytes.subList(index.getStart(), index.getStart() + len).toArray(new Byte[len])), len);
                        value = new BigDecimal((Integer) value).divide(new BigDecimal(10)).setScale(1,BigDecimal.ROUND_HALF_UP);
                        if (!type.getName().equalsIgnoreCase(BigDecimal.class.getName())) {
                            value = ((BigDecimal) value).doubleValue();
                        }
                        index.setStart(index.getStart() + len);
                        break;
                    case DATE_YMD:
                        int i = 0;
                        String date = bytes.get(index.getStart() + (i++)) +
                                bytes.get(index.getStart() + (i++)) + "-" +
                                bytes.get(index.getStart() + (i++)) + "-" +
                                bytes.get(index.getStart() + (i));
                        value=DateUtil.parse(date, DateUtil.FORMAT_SHORT);
                        index.setStart(index.getStart() + 4);
                        break;
                    case DATE_HMS:
                        i = 0;
                        value = String.format("%02d:%02d:%02d", bytes.get(index.getStart() + (i++)), bytes.get(index.getStart() + (i++)), bytes.get(index.getStart() + (i)));
                        index.setStart(index.getStart() + 3);
                        break;
                    case ASCII:
                        byte[] by = toPrimitives(bytes.subList(index.getStart(), index.getStart() + len).toArray(new Byte[len]));
                        if (len > 1) {
                            value = DataUtil.byteAsciiToString(by);
                        } else {
                            value = DataUtil.byteAsciiToString(by[0]);
                        }
                        index.setStart(index.getStart() + len);
                        break;
                    case BCDCODE:
                        byte[] cdCode = toPrimitives(bytes.subList(index.getStart(), index.getStart() + len).toArray(new Byte[len]));
                        value = DataUtil.bcdToString(cdCode);
                        break;
                }
                if (value != null) {
                    ReflectionUtils.setField(field, t, value);
                }
            } catch (Exception e) {
                System.err.println(field.getGenericType().toString() + "."+ field.getName() + " ByteToMessageConvert 转换发生异常");
                throw e;
            }
        }

        return t;
    }


    public static  <T> List<T> convertList(List<Byte> bytes, Index index, Class<T> clzz, int childLengh) throws Exception {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < childLengh; i++) {
            list.add(convert(bytes, index, clzz, null));
        }
        return list;
    }

//    public static void main(String[] args) {
//        Byte[] bts = {1, 4, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 0, 0, 8, -82, 0, 0, 8, -71, 0, 0, 4, 87, 0, 0, 0, 11, 0, 0, 8, -92, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 0, 0, 0, 0, 0, -87, -70, -122, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 98, 99, 99, 99, 99, 0, 0, 11, 53, 3, -78, -10, 76, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 98, 2, 0, 0, 101, 14, 18, 78, -15, -57, 48, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 120, 0, 0, -54, 28, 36, -99, -29, -114, 48, 48, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 121, 20, 104, 116, 116, 112, 58, 47, 47, 97, 115, 100, 102, 115, 102, 115, 102, 100, 46, 99, 111, 109};
//        List<Byte> byts = Arrays.asList(bts);
//        System.out.println(byts.subList(1,2));
//        ByteToMessageConvert c = new ByteToMessageConvert();
//        try {
//            StopLightReq stopLight = c.convert(byts, new Index(0, byts.size() - 1), StopLightReq.class);
//            System.out.println(stopLight);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

class Index{

    public Index(){

    }

    public Index(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    private Integer start;
    private Integer end;

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
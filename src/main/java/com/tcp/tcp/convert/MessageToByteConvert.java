package com.tcp.tcp.convert;

import com.tcp.tcp.convert.anno.Data;
import com.tcp.tcp.convert.anno.DataByteTypEnum;
import com.tcp.tcp.base.message.BaseMessage;
import com.tcp.tcp.convert.command.BaseCommand;
import com.tcp.tcp.convert.util.ConvertUtil;
import com.tcp.util.DataUtil;
import com.tcp.util.DateUtil;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 实体信息 转换成  byte数组
 * @author Yorking
 */
public class MessageToByteConvert {


    public static byte[] convertWrapReport(Object msg, String deviceNo, int code) {
        try {
            byte[] head = BaseCommand.getSendHead(deviceNo, code, 1);
            List<Byte> data = new ArrayList<>();
            data.addAll(Arrays.asList(ConvertUtil.toObjects(ConvertUtil.subBytes(head,0,BaseCommand.headLength))));
            data.addAll(convert(msg));
            System.arraycopy(ConvertUtil.toPrimitives(data), 0, head, 0,data.size());
            return BaseCommand.getData(head, data.size());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
     

    public static List<Byte> convert(Object msg) throws Exception {
        List<Byte> bs = new ArrayList<>();
        Class clzz = msg.getClass();
        List<List<Byte>> list = new ArrayList<>();

        List<Field> fields = ConvertUtil.getAnnoFields(clzz);
        if (fields == null || fields.isEmpty())return null;
        for (Field field : fields) {
            Data annotation = field.getDeclaredAnnotation(Data.class);
            if (annotation == null) continue;
            int len = annotation.byteLen();
            int order = annotation.order();
            try {
                if (annotation.byteLenAttr().length() > 0) {
                    Field f = clzz.getDeclaredField(annotation.byteLenAttr());
                    f.setAccessible(true);
                    len = (int) ReflectionUtils.getField(f, msg);
                }
                DataByteTypEnum byteType = annotation.byteType();
                Class<?> type = field.getType();

                field.setAccessible(true);
                Object obj = ReflectionUtils.getField(field, msg);
                List<Byte> fieldBytes = null;

                if (obj == null) {
                    fieldBytes = new ArrayList<>();
                    for (int i = 0; i < len; i++) {
                        fieldBytes.add((byte) 0);
                    }
                }
                switch (byteType) {
                    case ChildData:
                        fieldBytes = convert(obj);

                        if (field.getDeclaringClass() == BaseMessage.class && field.getName().equalsIgnoreCase("data")) {
                            // 设置 dataLength 报文数据长度
                            list.set(1,Arrays.asList(ConvertUtil.toObjects(DataUtil.intToBytes(fieldBytes.size()))));
                        }

                        break;
                    case ChildDataList:
                        if (obj instanceof List) {
                            fieldBytes = convert((List)obj);
                        }
                        break;
                    case Byte:
                        fieldBytes = Arrays.asList((Byte)obj);
                        break;
                    case IPv4:
                        if (obj != null) {
                            String[] o = ((String) obj).split("\\.");
                            if (o.length == 4) {
                                int i = 0;
                                byte[] ips = {
                                        DataUtil.StringToAsciiByte(o[i++], 1)[0],
                                        DataUtil.StringToAsciiByte(o[i++], 1)[0],
                                        DataUtil.StringToAsciiByte(o[i++], 1)[0],
                                        DataUtil.StringToAsciiByte(o[i++], 1)[0]
                                };
                                fieldBytes =Arrays.asList(ConvertUtil.toObjects(ips));
                            }
                        }
                        break;
                    case Number:
                        fieldBytes = Arrays.asList(ConvertUtil.toObjects(DataUtil.number2byte((Number) obj, len)));
                        break;
                    case Decimal4:
                        if (type.getName().equalsIgnoreCase(BigDecimal.class.getName())) {
                            fieldBytes = Arrays.asList(ConvertUtil.toObjects(DataUtil.number2byte(((BigDecimal) obj).multiply(new BigDecimal(10000)).intValue(), len)));
                        } else {
                            fieldBytes = Arrays.asList(ConvertUtil.toObjects(DataUtil.number2byte((int) ((Double) obj * 10000), len)));
                        }
                        break;
                    case Decimal2:
                        if (type.getName().equalsIgnoreCase(BigDecimal.class.getName())) {
                            fieldBytes = Arrays.asList(ConvertUtil.toObjects(DataUtil.number2byte(((BigDecimal) obj).multiply(new BigDecimal(100)).intValue(), len)));
                        } else {
                            fieldBytes = Arrays.asList(ConvertUtil.toObjects(DataUtil.number2byte((int) ((Double) obj * 100), len)));
                        }
                        break;
                    case Decimal1:
                        if (type.getName().equalsIgnoreCase(BigDecimal.class.getName())) {
                            fieldBytes = Arrays.asList(ConvertUtil.toObjects(DataUtil.number2byte(((BigDecimal) obj).multiply(new BigDecimal(10)).intValue(), len)));
                        } else {
                            fieldBytes = Arrays.asList(ConvertUtil.toObjects(DataUtil.number2byte((int) ((Double) obj * 10), len)));
                        }
                        break;
                    case DATE_YMD:
                        Date date = (Date) obj;
                        String dateFormat = DateUtil.format(date);
                        fieldBytes = Arrays.asList(Byte.parseByte(dateFormat.substring(0, 2)),
                                Byte.parseByte(dateFormat.substring(2, 4)),
                                Byte.parseByte(dateFormat.substring(5, 7)),
                                Byte.parseByte(dateFormat.substring(8, 10)));
                        break;
                    case DATE_HMS:
                        String hms = ((String) obj);
                        if (hms != null && hms.length() > 4) {
                            String[] split = hms.split(":");
                            fieldBytes = Arrays.asList(Byte.parseByte(split[0]), Byte.parseByte(split[1]), Byte.parseByte(split[2]));
                        } else {
                            fieldBytes =Arrays.asList((byte)0,(byte)0,(byte)0);
                        }
                        break;
                    case ASCII:
                        if (obj == null) {
                            fieldBytes = new ArrayList<>();
                            for (int i = 0; i < len; i++) {
                                fieldBytes.add((byte) 0);
                            }
                        } else {
                            fieldBytes = Arrays.asList(ConvertUtil.toObjects(DataUtil.StringToAsciiByte((String) obj, len)));
                        }
                        break;
                    case BCDCODE:
                        byte[] bytes = DataUtil.stingTOBCD((String) obj);
                        fieldBytes = new ArrayList<>();
                        fieldBytes.addAll(Arrays.asList(ConvertUtil.toObjects(bytes)));
                        if (bytes.length < len) {
                            for (int i = bytes.length; i < len; i++) {
                                fieldBytes.add(0, (byte) 0);
                            }
                        }
                        break;
                }
                if (fieldBytes != null) {
                    list.add(order, fieldBytes);
                }
            } catch (Exception e) {
                System.err.println(field.getDeclaringClass().getName() + "."+ field.getName() + " MessageToByteConvert 转换发生异常");
                throw e;
            }
        }
        for (List<Byte> l : list) {
//            System.out.println(l);
            bs.addAll(l);
        }
        return bs;
    }

    public static List<Byte> convert(List msgs) throws Exception {
        if (msgs != null && msgs.size() > 0) {
            List<Byte> bs = new ArrayList<>();
            for (Object msg : msgs) {
                bs.addAll(convert(msg));
            }
            return bs;
        }
        return null;
    }



//    public static void main(String[] args) {
//        StopLightReq sl = new StopLightReq();
//
//        sl.setPileNum(1);
//        sl.setAccountBalance(22.22d);
//        sl.setChargingValue(22.12d);
//        sl.setFreeBalance(11.11d);
//        sl.setMeterValue(0.11d);
//        sl.setOrderNo("1111111111");
//        sl.setStopAccount("222222222222");
//        sl.setPrePaidBalance(22.33d);
//        sl.setStopType(4);
//
//        StopLightReqChild c = new StopLightReqChild();
//        c.setNumber(11123334l);
//        c.setStr("aaaaaaaaaaaabcccc");
//        sl.setChild(c);
//
//        StopLightReqChild c2 = new StopLightReqChild();
//        c2.setNumber(12322323232332l);
//        c2.setStr("bbbbbbbbbbbbbbbbbbbbbb");
//        sl.setChild2(c2);
//
//
//        StopLightReqChild s = new StopLightReqChild();
//        s.setNumber(111111111111111l);
//        s.setStr("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//
//        StopLightReqChild s1 = new StopLightReqChild();
//        s1.setNumber(222222222222222l);
//        s1.setStr("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
//
//        sl.setChilds(new ArrayList<>());
//        sl.getChilds().add(s);
//        sl.getChilds().add(s1);
//        sl.setListLen(2);
//
//        sl.setUrl("http://asdfsfsfd.com");
//        sl.setUrlLen(sl.getUrl().length());
//        try {
//            List<Byte> convert = convert(sl);
//            System.out.println(convert);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}

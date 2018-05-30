package com.jeffrey.util.bytes;

/**
 * 基本数据类型与字节间的转换
 *
 * @author Jeffrey.Liu
 * @date 2018-05-30 8:51
 **/
public class ByteConvert {
    /**
     * int转字节
     * @param id
     * @return
     */
    public static byte[] int2Bytes(int id) {
        byte[] arr = new byte[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = (byte) ((id >> i * 8) & 0xff);
        }
        return arr;
    }

    /**
     * long转字节
     * @param id
     * @return
     */
    public static byte[] long2Bytes(long id) {
        byte[] arr = new byte[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (byte) ((id >> i * 8) & 0xff);
        }
        return arr;
    }

    /**
     * 字节转int
     * @param arr
     * @return
     */
    public static int bytes2Int(byte[] arr) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            value += (arr[i] & 0xff) << i * 8;
        }
        return value;
    }

    /**
     * 字节转long
     * @param arr
     * @return
     */
    public static long bytes2Long(byte[] arr) {
        long value = 0L;
        for (int i = 0; i < 8; i++) {
            value += (arr[i] & 0xff) << i * 8;
        }
        return value;
    }

    /**
     * String转byte
     * @param str
     * @return
     */
    public static byte[] str2Bytes(String str){
        return str.getBytes();
    }

    /**
     * byte转字符
     * @param arr
     * @return
     */
    public static String bytes2Str(byte[] arr){
        return new String(arr);
    }

    public static void main(String[] args) {
        byte[] arr = int2Bytes(8143);
        System.out.println(arr[0] + "," + arr[1] + "," + arr[2] + "," + arr[3]);
        int value = bytes2Int(arr);
        System.out.println(value);
        byte[] arr_long = long2Bytes(1600L);
        System.out.println(arr_long[0] + "," + arr_long[1] + "," + arr_long[2] + "," + arr_long[3] + "," + arr_long[4] + "," + arr_long[5] + "," + arr_long[6] + "," + arr_long[7]);
        long value_long = bytes2Long(arr_long);
        System.out.println(value_long);
        String describe = "我很牛逼";
        byte[] arr_str = str2Bytes(describe);
        System.out.println(arr_str);
        System.out.println(new String(arr_str));
    }
}

package com.jeffrey.util.bytes;

import java.io.*;

/**
 * 对象转换成字符生成文件，读取文件恢复成对象
 *
 * @author Jeffrey.Liu
 * @date 2018-05-30 8:54
 **/
public class Object2ByteAndFile {
    /**
     * 把对象转换成字节
     * @param obj
     * @return
     * @throws IOException
     */
    public static byte[] getBytesFromObject(Serializable obj) throws IOException {
        if (obj==null){
            return null;
        }
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(obj);
        return bo.toByteArray();
    }

    /**
     * 把字节数组保存为一个文件
     * @param bytes
     * @param outputFile
     * @return
     */
    public static File getFileFromBytes(byte[] bytes, String outputFile){
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (stream!=null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 把bytes转换成对象
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object getObjectFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        if (bytes==null || bytes.length==0){
            return null;
        }
        ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(bi);
        return oi.readObject();
    }

    /**
     * 文件转换成字节
     * @param file
     * @return
     */
    public static byte[] getBytesFromFile(File file){
        if (file==null){
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n=stream.read(b))!=-1){
                out.write(b,0,n);
            }
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee employee = new Employee();
        employee.setAge(20);
        employee.setName("Jeffrey.Liu");
        employee.setJob("enginner");
        byte[] bytes = getBytesFromObject(employee);
        File file = getFileFromBytes(bytes,"E:\\employee.txt");
        Employee employee1 = (Employee) getObjectFromBytes(getBytesFromFile(file));
        System.out.println(employee1.getName());
    }
}

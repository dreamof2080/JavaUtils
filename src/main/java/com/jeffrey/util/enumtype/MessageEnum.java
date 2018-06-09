package com.jeffrey.util.enumtype;

/**
 * 使用枚举表述常量
 * @author Jeffrey.Liu
 * @date 2018-6-9
 */
public enum  MessageEnum {
    ERROR(-1,"出现错误"),
    FAILED(0,"失败"),
    SUCCESS(1,"成功");

    private int state;
    private String msg;

    MessageEnum(int state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    public static MessageEnum stateOf(int index){
        for (MessageEnum messageEnum:values()){
            if (messageEnum.getState() == index){
                return messageEnum;
            }
        }
        return null;
    }
}

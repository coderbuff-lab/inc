package com.coderbuff.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * RESTful 消息对象
 * Created by OKevin on 2019-07-18 22:31
 */
@Setter
@Getter
@ToString
public class Message<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 数据对象
     */
    private T data;

    public Message(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Message(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Message success() {
        return new Message<>(0, "成功");
    }

    public static<T> Message message(T data) {
        return new Message<>(0, "成功", data);
    }

    public static Message failure() {
        return new Message<>(-1, "失败");
    }
}

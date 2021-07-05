package com.miku.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    /**
     * 邮件发送方
     */
    private String form;
    /**
     * 接收方
     */
    private String to;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String mainContent;

}

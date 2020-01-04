package com.example.game.msg;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Author: zhangat
 * @Date: 2020-1-2 0002 13:52
 * @DESCIBE
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MsgDto implements Serializable {

    private String name;

    private String msgType;

    private String content;
}

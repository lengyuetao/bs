package com.example.game.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zhangat
 * @Date: 2020-1-2 0002 13:54
 * @DESCIBE
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RespMsg implements Serializable {

    private String name;

    private String content;
}

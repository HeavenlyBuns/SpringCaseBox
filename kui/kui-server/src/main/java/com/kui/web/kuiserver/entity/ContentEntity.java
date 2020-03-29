package com.kui.web.kuiserver.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * 内容实体
 */
@ToString(callSuper = true)
@Data
@AllArgsConstructor
public class ContentEntity {
    private Integer id;
    private String title;
    private String tag;
    private String content;
    private LocalDate createdTime;
    private LocalDate updatedTime;

}

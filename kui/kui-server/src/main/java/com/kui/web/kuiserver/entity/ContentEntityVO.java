package com.kui.web.kuiserver.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;

@ApiModel("内容实体")
@Data
@ToString(callSuper = true)
public class ContentEntityVO{
    @ApiModelProperty(value = "类别",required = true)
    @NotNull
    private String category;

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "标题",required = true)
    @NotNull
    private String title;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "内容",required = true)
    @NotNull
    private String content;

    @ApiModelProperty(value = "创建时间")
    private LocalDate createdTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDate updatedTime;

    public ContentEntity toContentEntity(){
        ContentEntity entity = new ContentEntity(id,title,tag,content,LocalDate.now(ZoneId.of("GMT"))
                ,LocalDate.now(ZoneId.of("GMT")));
        return entity;
    }

}

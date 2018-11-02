package com.kbss.admin.cms.controller.resp.school;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelInfoResp {
    @ApiModelProperty(value = "层次ID", required = true)
    private Long levelId;
    
    @ApiModelProperty(value = "层次编码", required = true)
    private String levelCode;

    @ApiModelProperty(value = "层次名称", required = true)
    private String levelName;

    @ApiModelProperty(value = "层次简称", required = true)
    private String shortName;

    @ApiModelProperty(value = "状态", required = true)
    private Integer state;

    @ApiModelProperty(value = "备注", required = true)
    private String memo;
}

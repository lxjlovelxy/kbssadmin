package com.kbss.admin.cms.controller.req.school;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelAddReq {
	
    @ApiModelProperty(value = "层次编码", required = true)
    @NotBlank(message = "层次编码不能为空")
    @Length(min = 1, max = 30)
    private String levelCode;
    
    @ApiModelProperty(value = "层次拼音简写", required = true)
    @NotBlank(message = "拼音简写不能为空")
    @Length(min = 1, max = 50)
    private String shortName;
    
    @ApiModelProperty(value = "层次名称", required = true)
    @NotBlank(message = "层次名称不能为空")
    @Length(min = 1, max = 50)
    private String levelName;
    
    @ApiModelProperty(value = "状态 0:停用 1:启用", required = true)
    @NotNull(message = "状态不为空")
    private Integer state;
    
    @ApiModelProperty(value = "备注")
    @Length(max = 1000)
    private String memo;
}

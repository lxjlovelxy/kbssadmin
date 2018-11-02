package com.kbss.admin.cms.controller.resp.school;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
*
* @ClassName   类名：LevelListResp 
* @Description 功能说明：
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年7月4日
* @author      创建人：兰秀杰
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年7月4日   兰秀杰   创建该类功能。
*
***********************************************************************
*</p>
*/
@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LevelListResp {
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

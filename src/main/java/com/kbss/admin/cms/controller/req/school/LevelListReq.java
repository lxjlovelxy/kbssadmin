package com.kbss.admin.cms.controller.req.school;

import com.kbss.admin.cms.controller.req.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
*
* @ClassName   类名：LevelListReq 
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
public class LevelListReq  {
	
	@ApiModelProperty(value = "拼音简写")
    private String shortName;

    @ApiModelProperty(value = "层次名称")
    private String levelName;
}

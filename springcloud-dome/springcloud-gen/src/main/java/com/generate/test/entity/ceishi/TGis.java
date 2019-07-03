package com.generate.test.entity.ceishi;

import java.io.Serializable;

import com.terran4j.commons.api2doc.annotations.ApiComment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 地区信息表
 * </p>
 *
 * @author me
 * @since 2019-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@ApiModel(value="TGis对象", description="地区信息表")
public class TGis implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiComment(value = "主键id")
    private Integer id;

    @ApiComment(value = "父级id")
    private String pid;

    @ApiComment(value = "分类（0：省，1：市，2：区）")
    private String deep;

    @ApiComment(value = "简称")
    private String areaName;

    @ApiComment(value = "首字母")
    private String pinyinPrefix;

    @ApiComment(value = "拼音")
    private String pinyin;

    private Long extId;

    @ApiComment(value = "全称")
    private String extName;

    @ApiComment(value = "状态（0：隐藏，1：展示）")
    private Integer isType;

    @ApiComment(value = "是否热门城市（0：否，1：是）")
    private Integer isHot;


}

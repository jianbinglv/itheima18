package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemParamWtihCatNameMapper {

    List<com.taotao.pojo.ItemParamWtihCatName> getItemParamWtihCatName();

}
package com.jiexun.mapper;

import com.jiexun.domain.OlpBlockInfoVo;
import com.jiexun.domain.UserInfo;

import java.util.List;

/**
 * Created by dengtianguang on 2018/4/3.
 */
public interface OlpBlockInfoMapper {

    List<OlpBlockInfoVo> getOlpBlockInfo();

    int insertUserInfo(UserInfo userInfo);
}

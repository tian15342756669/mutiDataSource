package com.jiexun.service;

import com.jiexun.domain.OlpBlockInfoVo;
import com.jiexun.domain.UserInfo;

import java.util.List;

/**
 * Created by dengtianguang on 2018/4/3.
 */
public interface OlpBlockService {

    List<OlpBlockInfoVo> getOlpBlockInfo(String userId);

    int insertUserInfo (UserInfo userInfo);
}

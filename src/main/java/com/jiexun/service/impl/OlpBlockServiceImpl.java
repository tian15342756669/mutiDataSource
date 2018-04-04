package com.jiexun.service.impl;

import com.jiexun.domain.OlpBlockInfoVo;
import com.jiexun.domain.UserInfo;
import com.jiexun.mapper.OlpBlockInfoMapper;
import com.jiexun.service.OlpBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dengtianguang on 2018/4/3.
 */

@Service
public class OlpBlockServiceImpl implements OlpBlockService {

    @Autowired
    private OlpBlockInfoMapper olpBlockInfoMapper;

    public List<OlpBlockInfoVo> getOlpBlockInfo(String userId) {
        List<OlpBlockInfoVo> list = null;

        list = olpBlockInfoMapper.getOlpBlockInfo();

        return list;
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        return olpBlockInfoMapper.insertUserInfo(userInfo);
    }
}

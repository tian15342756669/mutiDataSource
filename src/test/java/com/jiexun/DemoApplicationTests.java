package com.jiexun;

import com.jiexun.domain.OlpBlockInfoVo;
import com.jiexun.domain.UserInfo;
import com.jiexun.service.OlpBlockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private OlpBlockService olpBlockService;

	@Test
	public void contextLoads() {

		UserInfo user = new UserInfo();
		user.setUserAge("22222");
		user.setUserId("22222");
		user.setUserName("22222");
		user.setUserAddress("22222ßßßßßß");
		int insertRet2 = olpBlockService.insertUserInfo(user);

		if (insertRet2==1){
			System.out.println("用户信息入库成功！！！！！！");
		}

		List<OlpBlockInfoVo> list = olpBlockService.getOlpBlockInfo("");

		if (list != null && list.size() >0){
			for (OlpBlockInfoVo vo:list){
				System.out.println("======="+vo.getBlockId()+
				"===========" + vo.getBlockName()+
				"===========" + vo.getZfbAppId());
			}
		}

		List<OlpBlockInfoVo> list2 = olpBlockService.getOlpBlockInfo("");

		if (list2 != null && list2.size() >0){
			for (OlpBlockInfoVo vo:list2){
				System.out.println("+++++++++"+vo.getBlockId()+
						"+++++++++++" + vo.getBlockName()+
						"+++++++++++" + vo.getZfbAppId());
			}
		}

		UserInfo userInfo = new UserInfo();
		userInfo.setUserAge("1111");
		userInfo.setUserId("11111");
		userInfo.setUserName("111111");
		userInfo.setUserAddress("1111111111");
		int insertRet = olpBlockService.insertUserInfo(userInfo);

		if (insertRet==1){
			System.out.println("用户信息入库成功！！！！！！");
		}

	}

}

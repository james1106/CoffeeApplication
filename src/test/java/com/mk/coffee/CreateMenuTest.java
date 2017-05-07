package com.mk.coffee;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class CreateMenuTest extends BaseTestService {
    private final String oneUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxb4de448e44d78c61&redirect_uri=http%3a%2f%2fhuihuisuiyue.apps.mugglecoding.com%2findex&response_type=code&scope=snsapi_base#wechat_redirec";
    private final String meUrl="http://huihuisuiyue.apps.mugglecoding.com/memberCenter";
    @Autowired
    private WxMpService wxMpService;

    @Test
    public void menuCreate() throws WxErrorException {
        WxMenu wxMenu = new WxMenu();
        List<WxMenuButton> wxMenuButtons = new ArrayList<>();
        //第一个菜单
        WxMenuButton wxMenuButton1 = new WxMenuButton();
        wxMenuButton1.setName("喝咖啡");
        wxMenuButton1.setType("view");
        wxMenuButton1.setUrl(oneUrl);

        //第二个菜单
        WxMenuButton wxMenuButton2 = new WxMenuButton();
        wxMenuButton2.setName("E乐饮");

        List<WxMenuButton> subButtons=new ArrayList<>();
        WxMenuButton subButton1=new WxMenuButton();
        subButton1.setName("投资合作");
        subButton1.setType("view");
        subButton1.setUrl("http://mp.weixin.qq.com/s/KR5P6KOB8V43rx81rBk9cw");
        subButtons.add(subButton1);


        WxMenuButton subButton2=new WxMenuButton();
        subButton2.setName("关于我们");
        subButton2.setType("view");
        subButton2.setUrl("http://mp.weixin.qq.com/s/WCt1tMoobDjHFNRAnATdrA");
        subButtons.add(subButton2);


        WxMenuButton subButton3=new WxMenuButton();
        subButton3.setName("如何取咖啡");
        subButton3.setType("click");
        subButton3.setKey("unrealized");
        subButtons.add(subButton3);
        wxMenuButton2.setSubButtons(subButtons);


        //第三个菜单
        WxMenuButton wxMenuButton3 = new WxMenuButton();
        wxMenuButton3.setName("我的");
        wxMenuButton3.setType("view");
        wxMenuButton3.setUrl(meUrl);


        //添加菜单
        wxMenuButtons.add(wxMenuButton1);
        wxMenuButtons.add(wxMenuButton2);
        wxMenuButtons.add(wxMenuButton3);
        //设置菜单
        wxMenu.setButtons(wxMenuButtons);
        //创建菜单
        wxMpService.getMenuService().menuCreate(wxMenu);
    }
}

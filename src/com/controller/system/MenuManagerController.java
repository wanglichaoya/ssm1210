package com.controller.system;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.po.MenuPo;
import com.po.RolePo;
import com.po.common.CommboxPo;
import com.service.ISystemService;
import com.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * describe: 菜单管理控制器
 *
 * @author 王立朝
 * @date 2019/09/16
 */
@Controller
@RequestMapping("/system")
public class MenuManagerController {

    @Resource
    private ISystemService isystemService;

    @RequestMapping("/getParentMenu")
    @ResponseBody
    public JSONArray getParentMenu() {
        List<CommboxPo> provincePos1 = isystemService.getParentMenu();
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(provincePos1);
        return jsonArray;
    }

    /**
     * 新增菜单
     **/
    @RequestMapping("/addMenu")
    public String addMenu(MenuPo menuPo, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        int mPid = menuPo.getMPid();
        String MName = menuPo.getMName();
        Boolean flag = false;

        //验证是否有同一个父级菜单下有多个重名的菜单
        List<MenuPo> menuPos = isystemService.check();
        System.out.println("菜单个数为： " + menuPos.size());
        if(menuPos.size()>0){
            for (int i=0;i<menuPos.size();i++){
                int getMPid = menuPos.get(i).getMPid();
                String getMName = menuPos.get(i).getMName();
                if(mPid == getMPid && MName.equals(getMName)){
                    flag = true;
                    jsonObject.put("error", "true");
                    ResponseUtil.Write(response, jsonObject);

                }
            }

        }
        if(flag == false){
            System.out.println("测试是否执行了");
            int result = isystemService.addMenu(menuPo);
            if (result > 0) {
                jsonObject.put("success", "addSucc");
                ResponseUtil.Write(response, jsonObject);
            } else {
                jsonObject.put("error", "addError");
                ResponseUtil.Write(response, jsonObject);
            }
        }


        return null;
    }
}

package com.aowin.controller;


import com.aowin.model.Syuser;
import com.aowin.model.Vender;
import com.aowin.service.VenderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;


@RestController
@RequestMapping("/main/vender")
public class VenderController {

    final
    VenderService venderService;

    public VenderController(VenderService venderService) {
        this.venderService = venderService;
    }

    @RequestMapping("/getVender")
    @ResponseBody
    public PageInfo<Vender> getVender(Integer pageNum,String keysS,String typeS,String zxbjS){
        Map<String,Object> map = new HashMap<>();
        map.put("keysS",keysS);
        map.put("typeS",typeS);
        map.put("zxbjS",zxbjS);
        PageHelper.startPage(pageNum,8);
        Page<Vender> page = (Page<Vender>) venderService.getAllVender(map);
        return page.toPageInfo();
    }


    @RequestMapping("/getVenderList")
    @ResponseBody
    public List<Vender> getVenderList(){

        return venderService.getVenderList();
    }

    @RequestMapping("/venderDetails")
    @ResponseBody
    public Vender venderDetailsPage(Integer vender_id){
        return venderService.getVenderById(vender_id);
    }

    @RequestMapping("/addVender")
    @ResponseBody
    public String addVender(@Validated Vender vender, BindingResult br, HttpSession session) throws Exception {
        Syuser syuser = (Syuser) session.getAttribute("syuser");
        vender.setUser_id(syuser.getUserId());
        if(br.hasErrors()){
            return null;
        }
        Vender vender1 = new Vender();
        vender1.setVender_name(vender.getVender_name().trim());
        System.out.println(vender.getVender_name().trim());
        System.out.println("是否存在相同名字："+(venderService.selectVenderByCode(vender1)==1));
        if(venderService.selectVenderByCode(vender1)==1){
            return "3";
        }
        vender1.setProduct_license(vender.getProduct_license().trim());
        System.out.println("是否存在生产许可证："+(venderService.selectVenderByCode(vender1)==1));
        if(venderService.selectVenderByCode(vender1)==1){
            return "4";
        }
        vender1.setBussiness_registration_no(vender.getBussiness_registration_no().trim());
        System.out.println("是否存在工商注册号："+(venderService.selectVenderByCode(vender1)==1));
        if(venderService.selectVenderByCode(vender1)==1){
            return "5";
        }

        int i = venderService.addVender(vender);

        return i+"";
    }

    @RequestMapping("/updateVender")
    @ResponseBody
    public String updateVender(@Validated Vender vender, BindingResult br, HttpSession session) throws Exception {
        Vender oriVender = venderService.getVenderById(vender.getVender_id());
        Syuser syuser = (Syuser) session.getAttribute("syuser");
        vender.setUser_id(syuser.getUserId());
        if(br.hasErrors()){
            return null;
        }
        Vender vender1 = new Vender();
        vender1.setVender_name(vender.getVender_name());
        if(venderService.selectVenderByCode(vender1)==1 && !vender.getVender_name().equals(oriVender.getVender_name())){
            return "3";
        }
        vender1.setProduct_license(vender.getProduct_license());
        System.out.println(venderService.selectVenderByCode(vender1)==1 );
        System.out.println(!vender.getProduct_license().equals(oriVender.getProduct_license()));
        if(venderService.selectVenderByCode(vender1)>1 && !vender.getProduct_license().equals(oriVender.getProduct_license())){
            return "4";
        }
        vender1.setBussiness_registration_no(vender.getBussiness_registration_no()); //先判断值是否重复，接着判断是否是原来的值，都满足则return,上同
        if(venderService.selectVenderByCode(vender1)>1 && !vender.getBussiness_registration_no().equals(oriVender.getBussiness_registration_no())){
            return "5";
        }

        int i = venderService.updateVender(vender);

        return i+"";
    }
    @RequestMapping("/cancelVender")
    @ResponseBody
    public String cancelVender(Integer vender_id, HttpSession session) throws Exception {

        Syuser syuser = (Syuser) session.getAttribute("syuser");
        Map<String,Object> map = new HashMap<>();
        map.put("vender_id",vender_id);
        map.put("user_id",syuser.getUserId());
        int i = venderService.cancelVender(map);
        return i+"";
    }

}

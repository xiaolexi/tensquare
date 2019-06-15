package com.pinyougou.manager.controller;

import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Specification;
import com.pinyougou.service.SpecificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
    @com.alibaba.dubbo.config.annotation.Reference(timeout = 10000)
    private SpecificationService specificationService;

    /*分页查询品牌*/
    @GetMapping("/findByPage")
    public PageResult findByPage(Specification specification, Integer page, Integer rows){
        /*get请求中文转码*/
        if (specification!=null && StringUtils.isNoneBlank(specification.getSpecName())){
            try {
                specification.setSpecName(new String(specification.getSpecName()
                .getBytes("ISO8859-1"),"UTF-8"
                ));

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return specificationService.findByPage(specification,page,rows);
    }





}

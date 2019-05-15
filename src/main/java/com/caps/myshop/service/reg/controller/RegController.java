package com.caps.myshop.service.reg.controller;

import org.caps.myshop.commons.domain.TbUser;
import org.caps.myshop.commons.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caps
 */
@RestController
@RequestMapping(value = "reg")
public class RegController {

    @Autowired
    private TbUserMapper tbUserMapper;

    /**
     * 根据 ID 测试查询用户信息
     * @param id
     * @return
     */
    @GetMapping(value = {"{id}"})
    public String reg(@PathVariable long id) {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(id);
        return tbUser.getUsername();
    }
}

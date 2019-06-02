package org.caps.myshop.service.reg.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.caps.myshop.commons.domain.TbUser;
import org.caps.myshop.commons.dto.AbstractBaseResult;
import org.caps.myshop.commons.dto.BaseResultFactory;
import org.caps.myshop.commons.mapper.TbUserMapper;
import org.caps.myshop.commons.service.TbUserService;
import org.caps.myshop.commons.validator.BeanValidator;
import org.caps.myshop.commons.web.AbstractBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author caps
 */
@RestController
@RequestMapping(value = "reg")
public class RegController extends AbstractBaseController<TbUser> {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    /**
     * 根据 ID 测试查询用户信息
     * @param tbUser
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "参数为实体类，注意用户名和邮箱不要重复")
    @PostMapping(value = "user")
    public AbstractBaseResult reg(@ApiParam(name = "tbUser", value = "用户模型") TbUser tbUser) {
        String validator = BeanValidator.validator(tbUser);
        //校验不为空
        if (StringUtils.isNotBlank(validator)){
            return error(validator,null);
        }

        //密码不为空
        if (StringUtils.isBlank(tbUser.getPassword())){
            return error("密码不能为空",null);
        }

        // 验证用户名是否重复
        if (!tbUserService.unique("username", tbUser.getUsername())) {
            return error("用户名已存在", null);
        }

        // 验证邮箱是否重复
        if (!tbUserService.unique("email", tbUser.getEmail())) {
            return error("邮箱重复，请重试", null);
        }

        // 注册用户
        try {
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            TbUser user = tbUserService.save(tbUser);
            response.setStatus(HttpStatus.CREATED.value());
            return success(request.getRequestURI(), user);

        } catch (Exception e) {
            // 这里补一句，将 RegService 中的异常抛到 Controller 中，这样可以打印出调试信息
            return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "注册邮件发送失败", e.getMessage());
        }

    }
}

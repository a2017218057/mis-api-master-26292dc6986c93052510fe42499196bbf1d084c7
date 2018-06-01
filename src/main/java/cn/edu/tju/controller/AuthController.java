package cn.edu.tju.controller;

import cn.edu.tju.dao.LoadInfoRepo;
import cn.edu.tju.dao.UserRepo;
import cn.edu.tju.dto.ErrorReporter;
import cn.edu.tju.dto.ResponeUserData;
import cn.edu.tju.model.LoadInfo;
import cn.edu.tju.service.LoginService;
import cn.edu.tju.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;


@RestController
public class AuthController {

    @Autowired
    protected LoginService loginService;

    @Autowired
    protected UserRepo userRepo;


    @RequestMapping(value = "/leave/auth/islogin",method = RequestMethod.POST)
    public ErrorReporter index(String username, String password){

        String msg;
        if(loginService.isLogin()) {
            msg = "hello " + ((User)loginService.getHttpSession().getAttribute("user")).getId();
            return new ErrorReporter(0,msg);
        } else {
            msg = "not log in";
            return new ErrorReporter(1,msg);
        }

    }

    @RequestMapping(value = "/leave/auth/login",method = RequestMethod.POST)
    public ErrorReporter doLogin(String username, String password){
        //System.out.println(username);
        return loginService.login(username, password);
    }
    @RequestMapping(value = "/leave/auth/register",method = RequestMethod.POST)
    public ErrorReporter doRegister(String username, String password){
        //System.out.println(username);
        if(userRepo.exists(username)){
            return new ErrorReporter(1,"账号已存在");
        }
        else {
            long time = System.currentTimeMillis();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = simpleDateFormat.format(time);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashPass = passwordEncoder.encode(password);
            System.out.println("加密密码："+hashPass);

            User user = new User(username,hashPass,1,str);
            user.setId(username);
            user.setPassword(hashPass);
            user.setPrevilege(1);
            user.setRegistertime(str);
            userRepo.save(user);
            ResponeUserData data = new ResponeUserData(username,hashPass,1,str);
            return new ErrorReporter(0,"success",data );
        }

    }
    /*@RequestMapping(value = "/leave/auth/reg",method = RequestMethod.POST)
    public ErrorReporter doReg(String username, String password){
        return loginService.reg(username, password);
    }*/

    @RequestMapping(value = "/leave/auth/logout",method = RequestMethod.POST)
    public ErrorReporter doLogout(String username){
        return loginService.logout();
    }

}

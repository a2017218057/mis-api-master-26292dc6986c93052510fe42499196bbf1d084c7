package cn.edu.tju.service;

import cn.edu.tju.dao.LoadInfoRepo;
import cn.edu.tju.dao.UserRepo;
import cn.edu.tju.dto.ResponseNameData;
import cn.edu.tju.model.LoadInfo;
import cn.edu.tju.model.User;
import cn.edu.tju.dto.ErrorReporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Iterator;

@Service
public class LoginService {

    @Autowired
    protected UserRepo userRepo;

    @Autowired
    protected HttpSession httpSession;

    @Autowired
    protected LoginService loginService;

    @Autowired
    protected LoadInfoRepo loadInfoRepo;
    //HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

    public void saveInfo(LoadInfo info){
        loadInfoRepo.save(info);
    }
    public ErrorReporter login(String username, String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashPass = passwordEncoder.encode(password);
        //System.out.println(hashPass);
        if (userRepo.exists(username)) {
            User user = userRepo.findOne(username);
            System.out.println("用户存在"+user.getPassword());
            if (passwordEncoder.matches(password, user.getPassword())) {
                //System.out.println("密码正确");
                //System.out.println(user);
                //System.out.println(user.getId());
                httpSession.setAttribute("user", user);
                ResponseNameData responseNameData = new ResponseNameData(username);
                return new ErrorReporter(0, "success",responseNameData);
            }else {
                return new ErrorReporter(1, "password error");
            }
        } else {
            return new ErrorReporter(2, "no account");
        }
    }

    /*public ErrorReporter reg(String username, String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
        if ( !userRepo.exists(username) ) {
            User user = new User(username,password);
            userRepo.save(user);
            return new ErrorReporter(0, "success");
        } else {
            return new ErrorReporter(3, "duplication error");
        }
    }*/

    public boolean isLogin(){

        if (httpSession.getAttribute("user")!=null){
            System.out.println("登录");
            return true;
        }else {
            System.out.println("没登录");
            return false;
        }
    }

    public ErrorReporter logout() {
        if (httpSession.getAttribute("user") == null){
            return  new ErrorReporter(4, "not login");
        }
        httpSession.setAttribute("user", null);
        return new ErrorReporter(0, "success");
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }
}

package cn.edu.tju.controller;

import cn.edu.tju.dao.LeaveAppRepo;
import cn.edu.tju.dao.LoadInfoRepo;
import cn.edu.tju.dao.StaffRepo;
import cn.edu.tju.dto.*;
import cn.edu.tju.model.LeaveApplication;
import cn.edu.tju.model.LoadInfo;
import cn.edu.tju.model.Staff;
import cn.edu.tju.model.User;
import cn.edu.tju.service.LoginService;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import java.io.*;

import java.net.*;
import java.net.URLEncoder;
import java.net.URLDecoder;

@RestController
public class InfoController {

    @Autowired
    protected LoginService loginService;

    @Autowired
    protected HttpSession httpSession;

    @Autowired
    protected LeaveAppRepo leaveAppRepo;

    @Autowired
    protected StaffRepo staffRepo;
    @Autowired
    protected LoadInfoRepo loadInfoRepo;

   /* @RequestMapping("/leave/review/todoList")
    public ErrorReporter todoList (String username, int page, int pageSize) {

        if ( !loginService.isLogin()) {
            return new ErrorReporter(4, "not login");
        }

        User curUser = (User) httpSession.getAttribute("user");
        Staff curStaff = staffRepo.findOne(curUser.getId());

        int total = leaveAppRepo.countByManagerIdAndStatus(curStaff.getId(), 2);

        Pageable pageable = new PageRequest(page - 1, pageSize);
        List<LeaveApplication> las = leaveAppRepo.findByManagerIdAndStatusOrderByApplyTimeDesc(curStaff.getId(), 2, pageable);
        List<ResponseLeaveApplication> list = new ArrayList<>();
        for (LeaveApplication e:las) {
            list.add(new ResponseLeaveApplication(e));
        }
        ResponseListData data = new ResponseListData(page, pageSize, total, curStaff.getId(), list);
        return new ErrorReporter(0, "success", data);
    }*/

    @RequestMapping("/leave/load/doneList")
    public ErrorReporter doneList(String username, int page, int pageSize) {

        if ( !loginService.isLogin()) {
            System.out.println("没有登录");
            return new ErrorReporter(4, "not login");
        }
        else {
            User curUser = (User) httpSession.getAttribute("user");
            System.out.println("进来"+curUser);
            //Staff curStaff = staffRepo.findOne(curUser.getId());
            //System.out.println("当前用户" + curUser.getId());
            System.out.println(username);
            long total = loadInfoRepo.count();
            System.out.println("一共"+total);
            Pageable pageable = new PageRequest(page - 1, pageSize);
            List<LoadInfo> las = loadInfoRepo.findByifcheckOrderByLoadtimeDesc(true, pageable);
            List<ResponseLoadInfo> list = new ArrayList<>();
             for (LoadInfo e : las) {
                list.add(new ResponseLoadInfo(e));
                //System.out.println(e);
            }

            ResponseListData data = new ResponseListData(page, pageSize, total, username, list);
            return new ErrorReporter(0, "success", data);
        }
    }
    @RequestMapping("/leave/load/selfList")
    public ErrorReporter selfList(String username, int page, int pageSize) {

        if ( !loginService.isLogin()) {
            System.out.println("没有登录");
            return new ErrorReporter(4, "not login");
        }
        else {

            User curUser = (User) httpSession.getAttribute("user");
            System.out.println("进来"+curUser);
            //Staff curStaff = staffRepo.findOne(curUser.getId());
            //System.out.println("当前用户" + curUser.getId());
            System.out.println(username);
            long total = loadInfoRepo.countById(curUser.getId());
            System.out.println("一共"+total);
            Pageable pageable = new PageRequest(page - 1, pageSize);
            List<LoadInfo> las = loadInfoRepo.findById(curUser.getId(), pageable);
            List<ResponseLoadInfo> list = new ArrayList<>();
            for (LoadInfo e : las) {
                list.add(new ResponseLoadInfo(e));
                //System.out.println(e);
            }

            ResponseListData data = new ResponseListData(page, pageSize, total, username, list);
            return new ErrorReporter(0, "success", data);
        }
    }
    @RequestMapping("/leave/searchinfo/searchlist")
    public ErrorReporter searchinfo(String name,String tag,String username, int page, int pageSize)
    {
        if ( !loginService.isLogin()) {
            System.out.println("没有登录");
            return new ErrorReporter(4, "not login");
        }
        else
        {
            Pageable pageable = new PageRequest(page - 1, pageSize);
            if(name!=""&&tag!=""){

                long total = loadInfoRepo.countByNameContainingAndTagContainingAndIfcheck(name,tag,true);
                System.out.println("total:"+total);
                List<LoadInfo> las = loadInfoRepo.findByNameContainingAndTagContainingAndIfcheck(name,tag,true,pageable);
                List<ResponseLoadInfo> list = new ArrayList<>();
                for (LoadInfo e : las){
                    list.add(new ResponseLoadInfo(e));
                }
                ResponseListData data = new ResponseListData(page, pageSize, total, username, list);
                return new ErrorReporter(0, "success", data);
            }
            else if(name!=""&&tag==""){
                long total = loadInfoRepo.countByNameContainingAndIfcheck(name,true);
                System.out.println("一共是："+total);

                List<LoadInfo> las = loadInfoRepo.findByNameContainingAndIfcheck(name,true,pageable);
                List<ResponseLoadInfo> list = new ArrayList<>();
                for (LoadInfo e : las){
                    list.add(new ResponseLoadInfo(e));
                }
                ResponseListData data = new ResponseListData(page, pageSize, total, username, list);
                return new ErrorReporter(0, "success", data);
            }
            else if(name==""&&tag!=""){
                long total = loadInfoRepo.countByTagContainingAndIfcheck(tag,true);
                System.out.println("一共是："+total);

                List<LoadInfo> las = loadInfoRepo.findByTagContainingAndIfcheck(tag,true,pageable);
                List<ResponseLoadInfo> list = new ArrayList<>();
                for (LoadInfo e : las){
                    list.add(new ResponseLoadInfo(e));
                }
                ResponseListData data = new ResponseListData(page, pageSize, total, username, list);
                return new ErrorReporter(0, "success", data);
            }
            //long total = 10;
            return new ErrorReporter(0, "fail");

        }

    }
    @RequestMapping("/leave/searchinfo/searchtags")
    public ErrorReporter searchtags(String tag_seq,String username, int page, int pageSize)
    {
        if ( !loginService.isLogin()) {
            System.out.println("没有登录");
            return new ErrorReporter(4, "not login");
        }
        else
        {
            Pageable pageable = new PageRequest(page - 1, pageSize);
            //long total = 10;
            long total = loadInfoRepo.countByTagLike(tag_seq);
            System.out.println("一共是："+total);

            List<LoadInfo> las = loadInfoRepo.findByTagContainingAndIfcheck(tag_seq,true,pageable);
            List<ResponseLoadInfo> list = new ArrayList<>();
            for (LoadInfo e : las){
                list.add(new ResponseLoadInfo(e));
            }
            ResponseListData data = new ResponseListData(page, pageSize, total, username, list);
            return new ErrorReporter(0, "success", data);
        }

    }
    @RequestMapping("/leave/add/addinfo")
    public ErrorReporter addpic(String name, String dynasty, String place, String type, String pathdoc, String pathpreview, Boolean ifcheck,String tag_seq,Boolean ifcheckdown,Boolean ispic,String pathmovie)
    {
        //String root = "img/";
        System.out.println("文件名称："+pathdoc);


        //pathdoc = pathdoc.replace("%2B","+");
        //URLDecoder.decode(pathdoc,"UTF-8");

        /*String docname = pathdoc.substring(0,pathdoc.lastIndexOf("."));
        String doctype = pathdoc.substring(pathdoc.lastIndexOf("."));
        BCryptPasswordEncoder filepathEncoder = new BCryptPasswordEncoder();
        pathdoc = "doc/"+filepathEncoder.encode(docname)+doctype;

        String previewname = pathpreview.substring(0,pathpreview.lastIndexOf("."));
        String previewtype = pathpreview.substring(pathpreview.lastIndexOf("."));
        pathpreview = "preview/"+filepathEncoder.encode(previewname)+previewtype;
        System.out.println(pathpreview);*/
        pathdoc = "doc/"+pathdoc;
        pathpreview = "preview/"+pathpreview;

        User curUser = (User)httpSession.getAttribute("user");
        //LoadInfo info = new LoadInfo(null,name,dynasty,type,place,null,null,null,curUser.getId());
        Date day = new Date();

        long time = System.currentTimeMillis();
        //String t = String.valueOf(time/1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = simpleDateFormat.format(time);
        //System.out.println(str);

        //System.out.println(s);
        LoadInfo info = new LoadInfo(name,dynasty,type,place,str,curUser.getId(),null,pathdoc,pathpreview,true,tag_seq,ifcheckdown,ispic,pathmovie);
        info.setName(name);
        info.setDynasty(dynasty);
        info.setPlace(place);
        info.setType(type);
        info.setLoadtime(str);
        info.setPathdoc(pathdoc);
        info.setPathpic(pathpreview);
        info.setIfcheck(ifcheck);
        info.setTag(tag_seq);
        info.setIfcheckdown(ifcheckdown);
        info.setIspic(ispic);
        info.setPathmovie(pathmovie);
        //loginService.saveInfo(info);
        loadInfoRepo.save(info);
        return new ErrorReporter(0,"success");
    }

    @RequestMapping("/leave/add/uploadpic")
    public ErrorReporter uploadpic(@RequestParam("file") MultipartFile file){
        String path = "src/main/resources/static/img/";
        //System.out.println("上传");
        if (!file.isEmpty()) {
            try {

                String tcatpath = httpSession.getServletContext().getRealPath("/");
                System.out.println(tcatpath);

                //String path = "src/main/resources/static/img/";
                File f = new File(path+file.getOriginalFilename());

                byte[] bytes = file.getBytes();
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(f));
                //file.transferTo(f);
                System.out.println(file.getOriginalFilename());
                out.write(bytes);
                out.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ErrorReporter(1,"fail");
            } catch (IOException e) {
                e.printStackTrace();
                return new ErrorReporter(1,"fail");
            }

            return new ErrorReporter(0,"success");

        } else {
            return new ErrorReporter(2,"null");
        }


    }
    @RequestMapping("/leave/add/uploaddoc")
    public ErrorReporter uploaddoc(@RequestParam("file") MultipartFile file,String filepath){
        String path = "src/main/resources/static/doc/";

        System.out.println("文件路径"+filepath);
        //System.out.println("上传");
        if (!file.isEmpty()) {
            try {
                /*BCryptPasswordEncoder filepathEncoder = new BCryptPasswordEncoder();
                String filename = filepath.substring(0,filepath.lastIndexOf("."));
                String filetype = filepath.substring(filepath.lastIndexOf("."));
                filepath = filepathEncoder.encode(filename)+filetype;*/
                String tcatpath = httpSession.getServletContext().getRealPath("/");
                System.out.println(tcatpath);

                //String path = "src/main/resources/static/img/";
                File f = new File(path+filepath);

                byte[] bytes = file.getBytes();
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(f));
                //file.transferTo(f);
                System.out.println(file.getOriginalFilename());
                out.write(bytes);
                out.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ErrorReporter(1,"fail");
            } catch (IOException e) {
                e.printStackTrace();
                return new ErrorReporter(1,"fail");
            }

            return new ErrorReporter(0,"success");

        } else {
            return new ErrorReporter(2,"null");
        }


    }
    @RequestMapping("/leave/add/uploadpreview")
    public ErrorReporter uploadpreview(@RequestParam("file") MultipartFile file,String filepath){
        String path = "src/main/resources/static/preview/";


        //System.out.println("上传");
        if (!file.isEmpty()) {
            try {
                /*BCryptPasswordEncoder filepathEncoder = new BCryptPasswordEncoder();
                String filename = filepath.substring(0,filepath.lastIndexOf("."));
                String filetype = filepath.substring(filepath.lastIndexOf("."));
                filepath = filepathEncoder.encode(filename)+filetype;
                System.out.println("预览路径"+filepath);*/

                String tcatpath = httpSession.getServletContext().getRealPath("/");
                System.out.println(tcatpath);
                System.out.println("预览");
                //String path = "src/main/resources/static/img/";
                File f = new File(path+filepath);

                byte[] bytes = file.getBytes();
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(f));
                //file.transferTo(f);
                System.out.println(file.getOriginalFilename());
                out.write(bytes);
                out.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ErrorReporter(1,"fail");
            } catch (IOException e) {
                e.printStackTrace();
                return new ErrorReporter(1,"fail");
            }

            return new ErrorReporter(0,"success");

        } else {
            return new ErrorReporter(2,"null");
        }


    }
    @RequestMapping("/leave/download/doc")
    public ResponseEntity<byte[]> downloaddoc(String pathdoc, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String rootpath = "http://localhost:8080/";
        String r = "F:/GitHub/mis-api-master-26292dc6986c93052510fe42499196bbf1d084c7/src/main/resources/static/";
        pathdoc = pathdoc.replace("%2B","+");
        File file=new File(r+pathdoc);
        //int pos = pathpic.indexOf("/");
        System.out.println(file.getName());
        //String filename = pathpic.substring(pos+1);
        //System.out.println("截取后的文件名"+file.getName());
        HttpHeaders headers = new HttpHeaders();
        Boolean flag= request.getHeader("User-Agent").indexOf("like Gecko")>0;
        String dfilename ;
        if (request.getHeader("User-Agent").toLowerCase().indexOf("msie") >0||flag) {
            dfilename = URLEncoder.encode(file.getName(), "UTF-8");//IE浏览器
        }else {
            dfilename = new String (file.getName().replaceAll(" ", "").getBytes("UTF-8"),"ISO-8859-1");
            System.out.println("文件名"+dfilename);
        }
        byte[] bt = FileUtils.readFileToByteArray(file);
        headers.add("Content-Disposition", "attachment;filename="+dfilename);

        //headers.setContentDispositionFormData("attachment",dfilename);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        HttpStatus httpStatus = HttpStatus.OK;
        return new ResponseEntity<byte[]>(bt,headers,httpStatus);
    }


    @RequestMapping("/leave/review/action")
    public ErrorReporter action(int id, int status, String reviewReason) {

        if ( !loginService.isLogin()) {
            return new ErrorReporter(4, "not login");
        }

        User curUser = (User) httpSession.getAttribute("user");
        Staff curStaff = staffRepo.findOne(curUser.getId());

        LeaveApplication la;
        if (leaveAppRepo.exists(id)) {
            la = leaveAppRepo.findOne(id);
        } else {
            return new ErrorReporter(19, "application id don't exist");
        }

        if ( !la.getManagerId().equals(curStaff.getId())) {
            return new ErrorReporter(23, "no authority for this leave application");
        }

        if (la.getStatus() != 2) {
            return new ErrorReporter(24, "could not review this leave application, has reviewed or hasn't publish");
        }

        if (status != 3 && status != 4) {
            return new ErrorReporter(25, "wrong status");
        }

        Staff applicantStaff = staffRepo.findOne(la.getApplicantId());
        Gson gson = new Gson();
        int[] leaveDetail = gson.fromJson(applicantStaff.getLeaveDetail(), int[].class);
        LocalDate initialDay = new LocalDate(2016,1,1);
        LocalDate startDay = new LocalDate(la.getStartTime()*1000L);
        LocalDate endDay = new LocalDate(la.getEndTime()*1000L);
        int startDayIndex = Days.daysBetween(initialDay, startDay).getDays();
        int endDayIndex = Days.daysBetween(initialDay, endDay).getDays();

        if (status == 4) {  // not approved
            for (int i = startDayIndex; i <= endDayIndex; i++) {
                if(leaveDetail[i] >= 100) {
                    leaveDetail[i] -= 100;
                }
            }
            if (la.getType() == 1) {
                int annualLeft = applicantStaff.getAnnualLeft();
                for (int i = startDayIndex; i <= endDayIndex; i++) {
                    if(leaveDetail[i] == 0) {
                        annualLeft ++;
                    }
                }
                applicantStaff.setAnnualLeft(annualLeft);
            }
        } else {    // status == 3, approved
            if (Arrays.asList(1,2,3,4,5,6,7).contains(la.getType())) {
                for (int i = startDayIndex; i <= endDayIndex; i++) {
                    if (leaveDetail[i] == 100) {
                        leaveDetail[i] = la.getType();
                    }
                }
            } else if (la.getType() == 10) {
                for (int i = startDayIndex; i <= endDayIndex; i++) {
                    if (leaveDetail[i] == 109 || leaveDetail[i] == 108) {
                        leaveDetail[i] -= 90;
                        // 18, 19 in leave detail means work at holiday or weekends
                    }
                }
            } else {
                return new ErrorReporter(13, "unknown type");
            }
        }
        applicantStaff.setLeaveDetail(gson.toJson(leaveDetail));
        staffRepo.save(applicantStaff);

        la.setStatus(status);
        la.setReviewReason(reviewReason);
        int curTime = (int) (System.currentTimeMillis() / 1000L);
        la.setReviewTime(curTime);
        leaveAppRepo.save(la);

        return new ErrorReporter(0, "success");
    }
}

package cn.test.bookms.controller;

import cn.test.bookms.entity.MsBook;
import cn.test.bookms.entity.MsMembers;
import cn.test.bookms.entity.MsRentBookHistory;
import cn.test.bookms.service.MsBookService;
import cn.test.bookms.service.MsMemberService;
import cn.test.bookms.util.CreateMemberNo;
import cn.test.bookms.util.TimeUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {

    @Autowired
    private MsBookService msBookService;

    @Autowired
    private MsMemberService msMemberService;

    @RequestMapping(value = "/addmember")
    public String addMember(String username,String password){

        MsMembers member = new MsMembers();
        member.setUsername(username);
        member.setPassword(password);
        Date now = TimeUtil.getSqlDate();
        member.setCreateTime(now);
        String memberNo = String.valueOf(CreateMemberNo.memberNo());
        member.setMemberNo(memberNo);
        member.setLevel(0);
        msMemberService.instertMember(member);
        return "redirect:/login";
    }


    @RequestMapping(value = "/rentbook")
    public void rentBook(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String id1 = request.getParameter("id");
       int bookId = Integer.parseInt(id1);
       int id = CreateMemberNo.memberNo();
        MsBook msBook = msBookService.selectByID(bookId);
        MsMembers member =(MsMembers) session.getAttribute("member");
        String memberNo =member.getMemberNo();
        int count = msMemberService.noRevertCount(bookId,memberNo);
        Map<String,Object> data = new HashMap<String,Object>();
        if(count>0){
            data.put("code","0001");
        }else{
            MsRentBookHistory msRentBookHistory = new MsRentBookHistory();
            msRentBookHistory.setId(id);
            msRentBookHistory.setBookId(bookId);
            msRentBookHistory.setAuthor(msBook.getAuthor());
            msRentBookHistory.setCategoryId(msBook.getCategoryId());
            msRentBookHistory.setIsbn(msBook.getIsbn());
            Date rentTime = TimeUtil.getSqlDate();
            msRentBookHistory.setRentTime(rentTime);
            Date revertTime = TimeUtil.getNTime(30);
            msRentBookHistory.setRevertTime(revertTime);
            msRentBookHistory.setTitle(msBook.getTitle());
            msRentBookHistory.setMemberNo(member.getMemberNo());
            int revertDay = TimeUtil.daysBetween(new Date(),rentTime);
            float  price = Float.parseFloat(msBook.getPrice());
            float fee = revertDay*price;
            String revertfee = String.valueOf(fee);
            msRentBookHistory.setRevertDay(String.valueOf(revertDay));
            msRentBookHistory.setRevertFee(revertfee);
            msRentBookHistory.setStatus("0");
            msMemberService.insertRecord(msRentBookHistory);
            data.put("code","0000");

        }
        String jsonResult = JSONObject.toJSONString(data);
        try{
            //接下来发送数据
            /*设置编码，防止出现乱码问题*/
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(jsonResult);
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @RequestMapping(value = "/revertbook")
    public String revertBook(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                         int currentPage, Model model, HttpSession httpSession){
        MsMembers member= (MsMembers)httpSession.getAttribute("member");
        String memberNo = member.getMemberNo();

        model.addAttribute("pageMsg",msMemberService.revertBookList(memberNo,currentPage));
        return "revertBook";
    }


    @RequestMapping(value = "/showmemberrecords")
    public String selectCountRecords(@RequestParam(value="currentPage",defaultValue="1",required=false)
                                             int currentPage, Model model, HttpSession httpSession){

        MsMembers member= (MsMembers)httpSession.getAttribute("member");
        String memberNo = member.getMemberNo();
        model.addAttribute("pageMsg",msMemberService.selectRecords(memberNo,currentPage));
        return "showMemberRecords";
    }


    @RequestMapping(value = "/updatestatus")
    public void updateStatus(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        String id1 = request.getParameter("id");
        int id = Integer.parseInt(id1);
        MsMembers member = (MsMembers)session.getAttribute("member");
        String memberNo = member.getMemberNo();
        msMemberService.updateStatus(id,memberNo);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("code","0000");
        String jsonResult = JSONObject.toJSONString(data);
        try{
            //接下来发送数据
            /*设置编码，防止出现乱码问题*/
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(jsonResult);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

package cn.blake.mvc;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ImageGenController {
	@RequestMapping(value="/toImg")
    public String toImg(){
        return "image/image";
    }


    //��¼��ȡ��֤��
    @RequestMapping("/getSysManageLoginCode")
    @ResponseBody
    public String getSysManageLoginCode(HttpServletResponse response,
            HttpServletRequest request) {
        response.setContentType("image/jpeg");// ������Ӧ����,������������������ΪͼƬ
        response.setHeader("Pragma", "No-cache");// ������Ӧͷ��Ϣ�������������Ҫ���������
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Set-Cookie", "name=value; HttpOnly");//����HttpOnly����,��ֹXss����
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response,"imagecode");// ���ͼƬ����
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //��֤����֤
    @RequestMapping(value = "/checkimagecode")
    @ResponseBody
    public String checkTcode(HttpServletRequest request,HttpServletResponse response) {
        String validateCode = request.getParameter("validateCode");
        String code = null;
        //1:��ȡcookie�������֤����Ϣ
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("imagecode".equals(cookie.getName())) {
                code = cookie.getValue();
                break;
            }
        }
        //1:��ȡsession��֤�����Ϣ
        //String code1 = (String) request.getSession().getAttribute("");
        //2:�ж���֤���Ƿ���ȷ
        if(isEmpty(validateCode) && validateCode.equals(code)){
            return "ok";    

        }
        return "error";
        //������û�н�����ĸ��Сģ������֤��������Ȥ�������ȥ��һ�£�
    }
    public boolean isEmpty(String code){
    	if(code==null){
    		return false;
    	}
    	return true;
    }
}

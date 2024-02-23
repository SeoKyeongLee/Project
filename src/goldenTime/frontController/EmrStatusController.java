package goldenTime.frontController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import goldenTime.command.EmrCommand;
import goldenTime.command.EmrInfoCommand;
import goldenTime.command.EmrRecommandCommand;
import goldenTime.command.RealTimeCommand;

import goldenTime.dto.EmrInfoDto;
import goldenTime.dto.RealTimeEmrDto;



/**
 * Servlet implementation class EmrStatusController
 */
@WebServlet("/emstcon/*")
public class EmrStatusController extends HttpServlet {

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      actionDo(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      actionDo(request, response);
   }
   public void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      System.out.println("EmrStatusController actionDo");
      
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=utf-8");
      
//      String viewPage=null;
      EmrCommand command=null;
     
      String uri=request.getRequestURI();
      String conPath = request.getContextPath();
      String com = uri.substring(conPath.length());
      System.out.println("com = "+com);
      
      if(com.equals("/emstcon/emrInfo")) {
         System.out.println("emrInfo/emstcon");
        
         
         command=new EmrInfoCommand();
         command.execute(request, response);
         List<EmrInfoDto> dataList = (List<EmrInfoDto>) request.getAttribute("list");

         JSONObject json = new JSONObject();
         json.put("data", dataList);

           PrintWriter out = response.getWriter();
           out.print(json);
           out.flush();
      }else if(com.equals("/emstcon/realTimeEmr")) {
         System.out.println("realTimeEmr");
         
         command=new RealTimeCommand();
         command.execute(request, response);
       
         List<RealTimeEmrDto> dataList = (List<RealTimeEmrDto>) request.getAttribute("list");
         JSONObject json = new JSONObject();
         json.put("data", dataList);

           PrintWriter out = response.getWriter();
           out.print(json);
           out.flush();
        
      }else if (com.equals("/emstcon/distanceToER")) {
			System.out.println("distanceToER");
			command = new EmrRecommandCommand();
			command.execute(request, response);
		}
      
      
   }
}
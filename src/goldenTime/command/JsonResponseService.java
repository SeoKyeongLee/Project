package goldenTime.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import goldenTime.dto.ActualDto;
import goldenTime.dto.CongestionDto;

import goldenTime.dto.NeedDto;


public class JsonResponseService implements EmrCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject json = new JSONObject();

        ArrayList<ActualDto> actualList = (ArrayList<ActualDto>) request.getAttribute("actualList");
        ArrayList<NeedDto> needList = (ArrayList<NeedDto>) request.getAttribute("needList");
        ArrayList<CongestionDto> congestList = (ArrayList<CongestionDto>) request.getAttribute("congestionList");

        
        json.put("actualData", actualList);
        json.put("needData", needList);
        json.put("congestData", congestList);


        try (PrintWriter out = response.getWriter()) {
            out.print(json.toString());
            out.flush(); // 버퍼를 비우고 즉시 클라이언트에게 보냄
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}

package goldenTime.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import goldenTime.dao.EmrStatusDao;
import goldenTime.dto.RealTimeEmrDto;

public class EmrRecommandCommand implements EmrCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Execute RecommandCommand");

        EmrStatusDao dao = new EmrStatusDao();

        // 응급실 추천 결과를 담을 리스트 생성
        List<Map<String, Object>> results = new ArrayList<>();

        // 클라이언트에서 받은 거리 정보를 담을 JSONArray 선언
        JSONArray distancesArray;

        // DB에서 응급실의 현재 상태를 포함하는 리스트 가져오기
        List<RealTimeEmrDto> emergency = dao.realTimeList();

        // 클라이언트에서 JSON 데이터를 읽고 JSONArray로 파싱
        try {
            BufferedReader reader = request.getReader();
            StringBuilder jsonInput = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonInput.append(line);
            }

            System.out.println("받은 JSON 데이터: " + jsonInput.toString());
            distancesArray = new JSONArray(jsonInput.toString());
            System.out.println("distanceArray : " + distancesArray);
        } catch (JSONException e) {
        	e.printStackTrace();
            handleJsonException(response, "유효하지 않은 JSON 데이터");
            return;
        } catch (Exception e) {
        	e.printStackTrace();
            handleInternalError(response, "요청 처리 중 오류가 발생했습니다");
            return;
        }

        // 받은 거리 데이터를 기반으로 응급실 추천 결과를 계산하고 리스트에 저장
        try {
        	System.out.println("execute jsonObject ");

        	for (int i = 0; i < distancesArray.length(); i++) {
        	    JSONObject jsonObject = distancesArray.getJSONObject(i);
        	    String key1 = jsonObject.getString("title");
        	    double distance = Double.parseDouble(jsonObject.getString("distance"));

        	    // Save the result as Map.Entry
        	    Map<String, Object> entry = new HashMap<>();

        	    for (RealTimeEmrDto emer : emergency) {
        	        if (key1.equals(emer.getName())) {
        	            // Congestion -> emer.getHvec() / emer.getHperyn() (lower congestion, higher congestion)
        	            // The lower the result value, the better
        	            double congestion = calculateCongestion(emer);
        	            double result = ((distance * 50) - (50 * congestion));
        	            entry.put("hospital", key1);
        	            entry.put("result", result);
        	            entry.put("cong", congestion);
        	            entry.put("distance", distance);
        	            results.add(entry);
        	            System.out.println("Hospital: " + key1 + " Distance: " + distance + " Congestion: " + congestion + ", Score: " + result);
        	        }
        	    }
        	
            }

            // 결과 값을 result 필드를 기준으로 내림차순으로 정렬
            results.sort(Comparator.comparingDouble(entry -> (Double) entry.get("result")));

            // 결과 리스트에서 최소 3개를 선택
            List<Map<String, Object>> limitedList = results.stream()
                    .limit(3)
                    .collect(Collectors.toList());

            // key-value 쌍을 저장할 JSONArray 생성
            JSONArray limitedListJson = new JSONArray(limitedList);

            // 정렬된 결과를 콘솔에 출력하거나 필요한 대로 사용
            System.out.println("정렬된 데이터: " + limitedListJson);

            // 데이터 전송 설정
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(limitedListJson.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleJsonException(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        response.getWriter().write(message);
    }

    private void handleInternalError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType("application/json");
        response.getWriter().write(message);
    }

    // 혼잡도를 계산합니다. 0.0으로 변경할 필요가 없습니다.
    static double calculateCongestion(RealTimeEmrDto emergencyRoom) {
        return (double) emergencyRoom.getHvec() / emergencyRoom.getHperyn(); // 이용 가능한 병상 수 / 표준 병상 수
    }
}
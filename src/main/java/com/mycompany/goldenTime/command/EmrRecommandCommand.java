package com.mycompany.goldenTime.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.goldenTime.dao.ERepositoryImpl;
import com.mycompany.goldenTime.model.EDistanceToERVO;
import com.mycompany.goldenTime.model.ERealTimeVO;

@Component
public class EmrRecommandCommand {
	
	@Autowired
	ERepositoryImpl repository;
	
	public List<Object> emrRecommand(List<EDistanceToERVO> distancesToER) {
		List<ERealTimeVO> list = repository.realTimeList();
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		
		for(EDistanceToERVO distanceVO : distancesToER) {
			String hospitalName = distanceVO.getTitle();
			double distance = distanceVO.getDistance();
			
			for(ERealTimeVO realTimeVO : list) {
				if(hospitalName.equals(realTimeVO.getName())) {
					Map<String, Object> entry = new HashMap<String, Object>();
    	            double congestion = calculateCongestion(realTimeVO);
    	            double result = ((distance * 50) - (50 * congestion));
    	            entry.put("hospital", hospitalName);
    	            entry.put("result", result);
    	            entry.put("cong", congestion);
    	            entry.put("distance", distance);
    	            results.add(entry);
    	            System.out.println("Hospital: " + hospitalName + " Distance: " + distance + " Congestion: " + congestion + ", Score: " + result);
				}
			}
		}
		
		for (Map<String, Object> result : results) {
			System.out.println(result.get("hospital"));
		}
		System.out.println();

		results.sort(new Comparator<Map<String, Object>>() {
		    @Override
		    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		        Double result1 = (Double) o1.get("result");
		        Double result2 = (Double) o2.get("result");

		        return result2.compareTo(result1); // 내림차순 정렬
		    }
		});
		

		
        // 결과 리스트에서 최소 3개를 선택
        List<Object> limitedList = results.stream()
                .limit(3)
                .collect(Collectors.toList());
        
        return limitedList;
	}
	
    // 혼잡도를 계산합니다. 0.0으로 변경할 필요가 없습니다.
    public double calculateCongestion(ERealTimeVO emergencyRoom) {
        return (double) emergencyRoom.getHvec() / emergencyRoom.getHperyn(); // 이용 가능한 병상 수 / 표준 병상 수
    }


}

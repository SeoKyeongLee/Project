package com.mycompany.goldenTime.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.mycompany.goldenTime.model.CDataVO;
import com.mycompany.goldenTime.model.CRegressionVO;



public interface CRepository {
	
	CDataVO getNationwideData(@Param("month") int month);
	CRegressionVO getNationwideRegression();
	CDataVO getRegionData(Map<String, Object> parameter);
	CRegressionVO getRegionRegression(@Param("region") String region);

}

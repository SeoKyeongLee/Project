package com.mycompany.goldenTime.dao;

import java.util.List;

import com.mycompany.goldenTime.model.CDataVO;
import com.mycompany.goldenTime.model.CRegressionVO;


public interface CRepository {
	
	CDataVO getNationwideData(int month);
	CRegressionVO getNationwideRegression();
	CDataVO getRegionData(int month, String region);
	CRegressionVO getRegionRegression(String region);

}

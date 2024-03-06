package com.mycompany.goldenTime.dao;

import java.util.List;

import com.mycompany.goldenTime.model.EActualVO;
import com.mycompany.goldenTime.model.ECongestionVO;
import com.mycompany.goldenTime.model.ENeedVO;
import com.mycompany.goldenTime.model.ERealTimeVO;
import com.mycompany.goldenTime.model.EmrInfoVO;

public interface ERepository {
	public List<EActualVO> actualList();
	public List<ENeedVO> needList();
	public List<ECongestionVO> congestionList();
	public List<ERealTimeVO> realTimeList();
	public List<EmrInfoVO> emrInfo(String area);

}

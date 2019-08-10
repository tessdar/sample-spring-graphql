package com.example.resolver;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.util.MessageReturn;
import com.common.util.MessageTrans;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.service.SampleService;
import com.example.vo.EmpSaveVo;

import graphql.schema.DataFetchingEnvironment;

@Component
public class Mutation implements GraphQLMutationResolver {

	private String rtnMsg = null;

	@Autowired
	private SampleService service;
	
	@Autowired
	private MessageTrans messageTrans;
	
	@Autowired
	private MessageReturn messageReturn;
	
	public Map<String, Object> setEmp(List<EmpSaveVo> empSaveVos, DataFetchingEnvironment dataFetEnv) {
		
		try {
			rtnMsg = service.setEmp(empSaveVos);
		} catch (Exception e) {
			rtnMsg = e.getMessage();
		}		
				
		return messageReturn.getResolvResp(messageTrans.getMapLang(rtnMsg), rtnMsg);
	}

}

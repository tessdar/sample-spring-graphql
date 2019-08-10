package com.example.resolver;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.service.SampleService;
import com.example.vo.DepChartVo;
import com.example.vo.DepListVo;
import com.example.vo.EmpListVo;
import com.example.vo.JobChartVo;
import com.example.vo.JobListVo;

import graphql.schema.DataFetchingEnvironment;

@Component
public class Query implements GraphQLQueryResolver {

	@Autowired
	private SampleService service;

	public List<EmpListVo> empList(Long departmentId, DataFetchingEnvironment dataFetEnv) {
		return service.getEmpList(departmentId);
	}

	public List<DepListVo> depList(DataFetchingEnvironment dataFetEnv) {
		return service.getDepList();
	}

	public DepListVo depOne(Long departmentId, DataFetchingEnvironment dataFetEnv) {
		return service.getDepOne(departmentId);
	}

	public List<JobListVo> jobList(DataFetchingEnvironment dataFetEnv) {
		return service.getJobList();
	}

	public JobListVo jobOne(String jobId, DataFetchingEnvironment dataFetEnv) {
		return service.getJobOne(jobId);
	}

	public List<DepChartVo> depChart(DataFetchingEnvironment dataFetEnv) {
		return service.getDepChart();
	}

	public List<JobChartVo> jobChart(DataFetchingEnvironment dataFetEnv) {
		return service.getJobChart();
	}

}

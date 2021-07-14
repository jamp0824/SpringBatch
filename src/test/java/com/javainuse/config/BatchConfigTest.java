package com.javainuse.config;

import javax.batch.runtime.context.StepContext;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;

public class BatchConfigTest {
	
	@Mock
	private JobBuilderFactory jobBuilderfactory;
	
	@Mock
	private StepBuilderFactory stepBuilderFactory;
	
	@InjectMocks
	private BatchConfig batchConfig;
	
	@Mock
	private JobBuilder jobBuilder;
	
	@Mock
	private StepBuilder stepBuilder;
	
	@Mock
	private TaskletStepBuilder taskletStepBuilder;
	
	@Mock
	private SimpleJobBuilder simpleJobBuilder;
	
	@Mock
	private Job job;
	
	@Mock
	private JobLauncher jobLauncher;
	
	@Mock
	private JobExecution jobExecution;
	
	@Mock
	private StepExecution stepExecution;
	
	@Mock
	private StepContext stepContext;
	
	@Mock
	private SimpleJobLauncher simpleJobLauncher;
	
	@Mock
	private Tasklet tasklet;
	
	@Mock
	private JobFlowBuilder jobFlowerBuilder;
	
	@Before
	public void setUp() throws Exception{
		batchConfig = new BatchConfig();
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void test() throws Exception{
		Mockito.when(jobBuilderfactory.get(Mockito.anyString())).thenReturn(jobBuilder);
		Mockito.when(jobBuilder.incrementer(Mockito.any())).thenReturn(jobBuilder);
		Mockito.when(jobBuilder.listener(Mockito.any())).thenReturn(jobBuilder);
		
		batchConfig.processJob();
	}

}

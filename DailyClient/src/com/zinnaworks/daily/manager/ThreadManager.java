package com.zinnaworks.daily.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextArea;

import com.zinnaworks.daily.dao.Single;
import com.zinnaworks.daily.service.PoolDailyService;
import com.zinnaworks.daily.vo.DailyVO;

//쓰레드 풀을 싱글톤으로 만들어 client마다 공유하게 만들었다.
public class ThreadManager {

	private static ThreadManager threadInstance;

	private ExecutorService executorService;

	/*private String type;

	private JTextArea tarea;*/
	
	private ThreadManager() {
		executorService = Executors.newFixedThreadPool(5);
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public static ThreadManager getInstance() {

		if (threadInstance == null) {
			threadInstance = new ThreadManager();
		}

		return threadInstance;
	}

	public void releaseInstance() {
		
		if (threadInstance != null) {
			//threadInstance.executorService.awaitTermination(timeout, unit);
			threadInstance.executorService.shutdown();
		}
		
		Single.releaseInstance();
		
		threadInstance.executorService = null;
		threadInstance = null;
	}
	//밑에 두개처럼 사용시 ThtreadManager.setType(type).setType(tarea);로 두개다 한번에 설정가능
	/*public ThreadManager setType(String type) {
		this.type = type;
		return this;
	}

	public ThreadManager setTarea(JTextArea tarea) {
		this.tarea = tarea;
		return this;
	}*/

	/*public void clickButton(String Command, DailyVO dailyVO) {
		executorService.execute(new PoolDailyService(Command, type, tarea, dailyVO));
	}*/
	
	public void clickButton(Thread thread) {
		executorService.execute(thread);
	}
}

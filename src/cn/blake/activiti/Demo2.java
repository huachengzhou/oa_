package cn.blake.activiti;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.task.Task;

import cn.blake.shoa.domain.Form;
import cn.blake.shoa.domain.TaskView;
import util.ProcessEngineUtil;

public class Demo2 {
	static ProcessEngine engine = ProcessEngineUtil.getProcessEngine();
	public static void main(String[] args) {
		List<TaskView> taskViews = new ArrayList<TaskView>();
		List<Task> tasks = engine.getTaskService().createTaskQuery()
				.list();
		for (Task task : tasks) {
			System.out.println(task.getId()+" "+task.getProcessDefinitionId());
			Form form = (Form) engine.getRuntimeService()
					.getVariable(task.getExecutionId(),"form");
			System.out.println(form.getTitle());
		}
	}
}

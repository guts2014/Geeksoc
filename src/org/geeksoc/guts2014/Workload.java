package org.geeksoc.guts2014;

import java.util.LinkedList;
import java.util.Queue;

public class Workload {

	private JobFactory jf;
	public Queue<Job> emails = new LinkedList<Job>();
	public Queue<Job> phones = new LinkedList<Job>();
	public Queue<Job> texts = new LinkedList<Job>();
	public Queue<Job> socials = new LinkedList<Job>();
	private WorkPacket global = new WorkPacket();

	public Workload(JobFactory jf) {
		this.jf = jf;
	}

	public void update() {
		while (!jf.getJobQueue().isEmpty()) {
			Job j = jf.getJobQueue().poll();
			switch (j.getJobType()) {
			case Email:
				emails.add(j);
				break;
			case Phone:
				phones.add(j);
				break;
			case Text:
				texts.add(j);
				break;
			case SocialMedia:
				socials.add(j);
				break;
			}
		}
		if(!emails.isEmpty()&&emails.peek().workDone(global.getWorkDone(JobType.Email))){
			emails.poll();
		}
		if(!phones.isEmpty()&&phones.peek().workDone(global.getWorkDone(JobType.Phone))){
			phones.poll();
		}
		if(!texts.isEmpty()&&texts.peek().workDone(global.getWorkDone(JobType.Text))){
			texts.poll();
		}
		if(!socials.isEmpty()&&socials.peek().workDone(global.getWorkDone(JobType.SocialMedia))){
			socials.poll();
		}
		
	}
	
	public void submit(WorkPacket wp){
		global.combine(wp);
	}

}

package org.geeksoc.guts2014;

import java.util.ArrayList;
import java.util.HashMap;

import org.geeksoc.guts2014.controls.Slider;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Section extends WorkerSpace {

	private Workspace ws;
	public HashMap<JobType, Integer> priority = new HashMap<JobType, Integer>();
	private Slider eslider, sslider, pslider, tslider;
	// To count up in milliseconds
	private int deltaCounter;

	public Section(Workspace ws) {
		this.ws = ws;
		eslider = new Slider();
		sslider = new Slider();
		pslider = new Slider();
		tslider = new Slider();
	}

	public void update(GameContainer cont, int delta) {
		updatePriorities();

		deltaCounter += delta;
		if (deltaCounter >= 1000) {
			
			if (JobFactory.isRunning) {
				WorkPacket wp = calculateWorkDone();
				ws.submitWork(wp);
				// Add cash for job
				ArrayList<Employee> roomWorkers;
				roomWorkers = this.getWorkers();
				for(Employee employee:roomWorkers){
					Main.cash -= employee.getWage();
				}
			}
			deltaCounter = 0;
		}

		eslider.update(cont.getInput());
		sslider.update(cont.getInput());
		pslider.update(cont.getInput());
		tslider.update(cont.getInput());
	}

	private void updatePriorities() {

		if (eslider.getValue() + pslider.getValue() + sslider.getValue()
				+ tslider.getValue() > 100) {
			eslider.setValue(eslider.getValue() - 1);
			pslider.setValue(pslider.getValue() - 1);
			sslider.setValue(sslider.getValue() - 1);
			tslider.setValue(tslider.getValue() - 1);
		}

		priority.put(JobType.Email, eslider.getValue());
		priority.put(JobType.SocialMedia, sslider.getValue());
		priority.put(JobType.Phone, pslider.getValue());
		priority.put(JobType.Text, tslider.getValue());
	}

	private WorkPacket calculateWorkDone() {
		WorkPacket sp = new WorkPacket();
		for (Employee e : this.workers) {
			WorkPacket wp = e.work(priority);
			// WorkPacket wp = new WorkPacket();
			sp.combine(wp);
		}
		return sp;
	}

	public void render(Graphics g, int x, int y) {

		g.setColor(Color.blue);
		eslider.render(g, x + 10, y + 10);
		g.setColor(Color.green);
		sslider.render(g, x + 10, y + 25);
		g.setColor(Color.orange);
		pslider.render(g, x + 10, y + 40);
		g.setColor(Color.red);
		tslider.render(g, x + 10, y + 55);

	}

}

package fr.istic.evc.pattern;

public interface Subject {
	void attach(Observer o);
	void detach(Observer o);
	void myNotify();
}

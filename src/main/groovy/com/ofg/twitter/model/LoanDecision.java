package com.ofg.twitter.model;

public class LoanDecision {

	public LoanDecision(Person person, String decision) {
		super();
		this.person = person;
		this.decision = decision;
	}
	
	private Person person;
	private String decision;
	
	public LoanDecision() {
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	
	
	
	
}

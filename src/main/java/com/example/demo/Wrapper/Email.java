package com.example.demo.Wrapper;

public class Email {
	
	private Long id_p;
	private Long id_m;
	private String emailS;
	private String emailC;
	private String subject;
	private String memo;
	private int numero;
	
	
	
	public Email(Long id_p, Long id_m,String emailS,String emailC, String subject, String memo) {
		super();
		this.id_p = id_p;
		this.id_m = id_m;
		this.emailS = emailS;
		this.emailC = emailC;
		this.subject = subject;
		this.memo = memo;
	}
	
	public Email(Long id_p, Long id_m,String emailS,String emailC, String subject, String memo,int numero) {
		super();
		this.id_p = id_p;
		this.id_m = id_m;
		this.emailS = emailS;
		this.emailC = emailC;
		this.subject = subject;
		this.memo = memo;
		this.numero = numero;
	}
	
	public Email(){
		
	}
	
	
	public Long getId_p() {
		return id_p;
	}
	public void setId_p(Long id_p) {
		this.id_p = id_p;
	}
	public Long getId_m() {
		return id_m;
	}
	public void setId_m(Long id_m) {
		this.id_m = id_m;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getEmailS() {
		return emailS;
	}

	public void setEmailS(String emailS) {
		this.emailS = emailS;
	}

	public String getEmailC() {
		return emailC;
	}

	public void setEmailC(String emailC) {
		this.emailC = emailC;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	
	
	
	

}

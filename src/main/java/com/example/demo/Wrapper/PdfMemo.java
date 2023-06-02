package com.example.demo.Wrapper;

public class PdfMemo {
	private Long id_p;
	private Long id_m;
	private String subject;
	private String memo;
	
	
	
	public PdfMemo(Long id_p, Long id_m, String subject, String memo) {
		super();
		this.id_p = id_p;
		this.id_m = id_m;
		this.subject = subject;
		this.memo = memo;
	}
	
	public PdfMemo(){
		
	}
	
	public PdfMemo(Long id_p , String subject, String memo) {
		super();
		this.id_p = id_p;
		this.subject = subject;
		this.memo = memo;
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
	
	
}

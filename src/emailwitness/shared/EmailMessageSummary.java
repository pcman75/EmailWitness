package emailwitness.shared;

import java.io.Serializable;
import java.util.List;

public class EmailMessageSummary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2110712499963965339L;
	private List<String> to;
	private List<String> cc;
	private List<String> bcc;
	private String subject;
	private String message;
	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public List<String> getCc() {
		return cc;
	}
	public void setCc(List<String> cc) {
		this.cc = cc;
	}
	public List<String> getBcc() {
		return bcc;
	}
	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

package emailwitness.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import emailwitness.shared.EmailMessageSummary;

public interface EmailListServiceAsync {

	void getEmailList(AsyncCallback<List<EmailMessageSummary>> asyncCallback);
	
}

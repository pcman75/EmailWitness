package emailwitness.server;

import java.io.IOException;
import javax.servlet.http.*;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class EmailWitnessServlet extends HttpServlet {
	public static final Logger _log = Logger.getLogger(EmailWitnessServlet.class.getName());

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {

			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);
			MimeMessage message = new MimeMessage(session, req.getInputStream());

			Entity mailEntity = createEmailMessage(message);
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			datastore.put(mailEntity);
		}
		catch (Exception ex) {
			_log.log(Level.WARNING, "Failed to receive email : " + ex.getMessage(), ex);
			ex.printStackTrace();
		}
	}

	private Entity createEmailMessage(MimeMessage message) throws MessagingException, IOException {
		Entity emailMessage = new Entity("EmailMessage");
		
		//Extract out the important fields from the Mime Message
		String subject = message.getSubject();
		String from = getAsCSV(message.getFrom());
		String to = getAsCSV(message.getRecipients(RecipientType.TO));
		String cc = getAsCSV(message.getRecipients(RecipientType.CC));
		String body = getContentAsString(message); 
				
				
		_log.log(Level.INFO, "Received email from " + from + " to: " + to + " , cc: " + cc + " with Subject: " + subject);
		
		if(!isNullOrEmpty(subject))
			emailMessage.setProperty("subject", subject);
		
		if(!isNullOrEmpty(from))
			emailMessage.setProperty("from", from);
		
		if(!isNullOrEmpty(to))
			emailMessage.setProperty("to", to);
		
		if(!isNullOrEmpty(cc))
			emailMessage.setProperty("cc", cc);

		if(!isNullOrEmpty(cc))
			emailMessage.setProperty("body", body);
		
		return emailMessage;
	}

	//Prefer text/plain over html or something else
	private String getContentAsString(Part message) throws MessagingException, IOException {
		String messageText = null;
		Object content = message.getContent();
		if (content instanceof String) {
			messageText = (String)content;
		}
		else if (content instanceof Multipart) {
			Multipart multipart = (Multipart)content;
			int count = multipart.getCount();
			for (int i = 0; i < count; i++) {
				BodyPart part = multipart.getBodyPart(i);
				Object content2 = part.getContent();
				if (content2 instanceof String) {
					messageText = (String)content2;
					
					//Prefer text/plain over html or something else
					if(part.getContentType().contains("text/plain"))
						break;
				}
			}
		}
		return messageText;
	}

	private String getAsCSV(Address[] adresses) {
		String csv = null;
		if(adresses != null) {
			for (Address address : adresses) {
				if(csv == null) {
					csv = "";
				}
				else {
					csv += ","; 
				}
				csv += address.toString();
			}
		}
		return csv;
	}

	private boolean isNullOrEmpty(String s) {
		return s == null || s.length() == 0;
	}
}

//@SuppressWarnings("serial")
//public class EmailWitnessServlet extends HttpServlet {
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws IOException {
//		resp.setContentType("text/plain");
//		resp.getWriter().println("Hello, world");
//	}
//}


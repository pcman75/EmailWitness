/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package emailwitness.server;

import java.util.ArrayList;
import java.util.List;

import emailwitness.client.EmailListService;
import emailwitness.shared.EmailMessageSummary;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class EmailListServiceImpl extends RemoteServiceServlet implements EmailListService {

	@Override
	public List<EmailMessageSummary> getEmailList() {
		ArrayList<EmailMessageSummary> emails = new ArrayList<EmailMessageSummary>();
		EmailMessageSummary mail = new EmailMessageSummary();
		
		ArrayList<String> to = new ArrayList<String>();
		to.add("manoliu@gmail.com");
		mail.setTo(to);
		emails.add(mail);
		
		return emails; 
	}
}

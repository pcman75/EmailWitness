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
package emailwitness.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import emailwitness.shared.EmailMessageSummary;

@RemoteServiceRelativePath("EmailListService")
public interface EmailListService extends RemoteService {
	
	List<EmailMessageSummary> getEmailList();
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static EmailListServiceAsync instance;
		public static EmailListServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(EmailListService.class);
			}
			return instance;
		}
	}
}

package io.onedev.server.web.page.project.pullrequests.detail.activities.activity;

import java.util.Date;

import org.apache.wicket.Component;
import org.apache.wicket.model.LoadableDetachableModel;

import io.onedev.server.OneDev;
import io.onedev.server.manager.PullRequestManager;
import io.onedev.server.model.PullRequest;
import io.onedev.server.util.facade.UserFacade;
import io.onedev.server.web.page.project.pullrequests.detail.activities.PullRequestActivity;
import io.onedev.server.web.util.DeleteCallback;
import io.onedev.server.util.userident.UserIdent;

@SuppressWarnings("serial")
public class PullRequestOpenedActivity implements PullRequestActivity {

	private final Long requestId;
	
	public PullRequestOpenedActivity(PullRequest request) {
		requestId = request.getId();
	}
	
	@Override
	public Component render(String componentId, DeleteCallback deleteCallback) {
		return new PullRequestOpenedPanel(componentId, new LoadableDetachableModel<PullRequest>() {

			@Override
			protected PullRequest load() {
				return getRequest();
			}
			
		});
	}

	public PullRequest getRequest() {
		return OneDev.getInstance(PullRequestManager.class).load(requestId);
	}
	
	@Override
	public Date getDate() {
		return getRequest().getSubmitDate();
	}

	@Override
	public String getAnchor() {
		return null;
	}

	@Override
	public UserIdent getUser() {
		return UserIdent.of(UserFacade.of(getRequest().getSubmitter()), getRequest().getSubmitterName());
	}

}

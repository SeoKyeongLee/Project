package goldenTime.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MCommand {
	void execute(HttpServletRequest request, HttpServletResponse response);
}

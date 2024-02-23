package goldenTime.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DCommand {
	void execute(HttpServletRequest request, HttpServletResponse response);
}

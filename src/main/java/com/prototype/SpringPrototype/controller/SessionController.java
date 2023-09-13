/*
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    private final Sess sessionManager;

    public SessionController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @GetMapping("/createSession")
    public String createSession(HttpSession httpSession) {
        //Session session = sessionManager.createSession();
        httpSession.setAttribute("sessionId", session.getId());
        return "Session created with ID: " + session.getId();
    }

    @GetMapping("/setAttribute")
    public String setAttribute(HttpSession httpSession) {
        String sessionId = (String) httpSession.getAttribute("sessionId");
        sessionManager.setAttribute(sessionId, "username", "John");
        return "Session attribute set.";
    }

    @GetMapping("/getAttribute")
    public String getAttribute(HttpSession httpSession) {
        String sessionId = (String) httpSession.getAttribute("sessionId");
        Object username = sessionManager.getAttribute(sessionId, "username");
        return "Username from session: " + username;
    }

    @GetMapping("/invalidateSession")
    public String invalidateSession(HttpSession httpSession) {
        String sessionId = (String) httpSession.getAttribute("sessionId");
        sessionManager.invalidateSession(sessionId);
        return "Session invalidated.";
    }
}
*/

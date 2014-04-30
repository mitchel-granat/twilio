package com.twilio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
 
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
import com.twilio.sdk.verbs.Dial;
 
public class TwilioHandleKeyServlet extends HttpServlet {
 
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
 
        String digits = request.getParameter("Digits");
        TwiMLResponse twiml = new TwiMLResponse();
        // Check for valid value entered
        if (digits != null && digits < 100000) {
      
        // Calculate the square of the number the user pressed
          square = digits * digits;
        
        // Repeat the calculated square back to the user
            Say say = new Say("The square of the number you entered is " square);
 
            try { 
                twiml.append(say);
            } catch (TwiMLException e) {
                e.printStackTrace();
            }
        } else {
            // If they didn't enter a valid value, redirect them to the TwilioServlet
            response.sendRedirect("/twiml");
            return;
        }
 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}

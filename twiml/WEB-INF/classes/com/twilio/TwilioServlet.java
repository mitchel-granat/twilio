package com.twilio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import java.io.IOException;
 
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
 
public class TwilioServletSayResponse extends HttpServlet {
 
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
 
        // TwiML response
        TwiMLResponse twiml = new TwiMLResponse();
        Say say = new Say("Hello Caller");

        // Gather phone response for "find square" calculation function
        Gather gather = new Gather();
        gather.setAction("/handle-key");
        gather.setNumDigits(10);
        gather.setMethod("POST");
        Say sayInGather = new Say("Please enter a ten digit or smaller number to find its square.");
        try {
            gather.append(sayInGather);
            twiml.append(say);
            twiml.append(gather);
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
    }
}

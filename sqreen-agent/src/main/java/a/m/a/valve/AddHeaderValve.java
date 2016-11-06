package a.m.a.valve;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddHeaderValve extends ValveBase {

    static {
        System.out.println("### LOADED " + AddHeaderValve.class.getName());
    }

    public void invoke(Request request, Response response) throws IOException, ServletException {
        System.out.println("### " + getClass().getSimpleName() + ".invoke()");
        response.addHeader("X-Instrumented-By", "sqreen");
        getNext().invoke(request, response);
    }
}

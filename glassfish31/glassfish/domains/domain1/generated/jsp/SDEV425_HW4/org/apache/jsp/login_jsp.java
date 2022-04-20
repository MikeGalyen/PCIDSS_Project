package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/WEB-INF/jspf/menus.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      response.setHeader("X-Powered-By", "JSP/2.3");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>SDEV425 Login</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link href=\"styles.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"main\">\n");
      out.write("            ");
      out.write("\n");
      out.write("\n");
      out.write("<header>\n");
      out.write("    <h1>SDEV425 Final Project</h1>\n");
      out.write("    <nav>\n");
      out.write("        <!-- Menu items -->\n");
      out.write("        <table class=\"menus\">\n");
      out.write("            <tr>\n");
      out.write("                <td><a href=\"index.jsp\">Home</a></td> \n");
      out.write("                <td> <a href=\"login.jsp\">Sign In</a></td>\n");
      out.write("                <td> <a href=\"ShowAccount\">Your Account</a></td>\n");
      out.write("                <td> <a href=\"logout.jsp\">Sign Out</a></td>\n");
      out.write("            </tr>\n");
      out.write("        </table>\n");
      out.write("    </nav>\n");
      out.write("</header>\n");
      out.write("\n");
      out.write("            <p></p>\n");
      out.write("            <p></p>\n");
      out.write("            <h2>Login</h2>\n");
      out.write("\n");
      out.write("            ");
 if (session.getAttribute("UMUCUserEmail") == null) {
            
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            <form action=\"Authenticate\" method=\"post\">\n");
      out.write("                <table class=\"center\">\n");
      out.write("                    <tr>\n");
      out.write("                    <td>Email: </td><td><input type=\"text\"  name=\"emailAddress\"  size=\"50\" autofocus> </td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>\n");
      out.write("                            Password: </td><td><input type=\"password\" name=\"pfield\" size=\"50\" autocomplete=\"off\"></td>\n");
      out.write("                    </tr>\n");
      out.write("                    <tr>\n");
      out.write("                        <td>\n");
      out.write("                            &nbsp;\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            <input type=\"submit\" name=\"SignIn\" value=\"Sign In\">\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                </table>\n");
      out.write("                <p></p>\n");
      out.write("                <!-- Print Error Message if any -->\n");
      out.write("                ");
 String e = (String) request.getAttribute("ErrorMessage");
                    if (e != null) {
                        out.print(e);
                    }
                
      out.write("\n");
      out.write("\n");
      out.write("            </form>\n");
      out.write("            ");

                } else {

                    request.setAttribute("ErrorMessage", "You are already logged in.");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
                    dispatcher.forward(request, response);                    
                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

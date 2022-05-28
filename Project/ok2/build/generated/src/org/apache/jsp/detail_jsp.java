package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class detail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n");
      out.write("        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Book Details Page</title>\n");
      out.write("        <link href=\"css/detail.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/style.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <aside class=\"col-sm-6 border-right\">\n");
      out.write("                    <article class=\"gallery-wrap\"> \n");
      out.write("                        <div class=\"img-big-wrap\">\n");
      out.write("                            <div> <a href=\"photo/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getImg()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"><img src=\"photo/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getImg()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></a></div>\n");
      out.write("                        </div> <!-- slider-product.// -->\n");
      out.write("                        <div class=\"img-small-wrap\">\n");
      out.write("                            <div class=\"item-gallery\"> <img src=\"photo/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getImg()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"> </div>\n");
      out.write("                            <div class=\"item-gallery\"> <img src=\"photo/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getImg()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"> </div>\n");
      out.write("                            <div class=\"item-gallery\"> <img src=\"photo/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getImg()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"> </div>\n");
      out.write("                            <div class=\"item-gallery\"> <img src=\"photo/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getImg()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"> </div>\n");
      out.write("                        </div> <!-- slider-nav.// -->\n");
      out.write("                    </article> <!-- gallery-wrap .end// -->\n");
      out.write("                </aside>\n");
      out.write("                <div class=\"details col-sm-6\">\n");
      out.write("                    <h3 class=\"product-title\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getBookName()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</h3>\n");
      out.write("                    <p class=\"product-description\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getDescription()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</p>\n");
      out.write("                    <h4 class=\"price\">current price: <span>$");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getPrice()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span></h4>\n");
      out.write("                    <p class=\"vote\"><strong>91%</strong> of buyers enjoyed this book!</p>\n");
      out.write("                    <h5>Author:\n");
      out.write("                        <span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getAuthor()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\n");
      out.write("                    </h5>\n");
      out.write("                    <h5>Year:\n");
      out.write("                        <span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getYear()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\n");
      out.write("                    </h5>\n");
      out.write("                    <button ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${user.getRole() eq 1 ? \"hidden\" : \"\"}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(">\n");
      out.write("                        <a href=\"DispatchServlet?btAction=Add&id=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.getBookId()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">add to cart</a>\n");
      out.write("                    </button>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>           \n");
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

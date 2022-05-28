package servlet;

import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import model.Account;
import model.Category;
import model.Product;

@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)

public class UpdateServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "/photo";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();
            Account u = (Account) session.getAttribute("user");
            if (u != null && u.getRole() == 1) {
                String image = uploadFile(request);
                String productId = request.getParameter("id");
                String productName = request.getParameter("name");
                String description = request.getParameter("description");
                String unit = request.getParameter("unit");
                String Year = request.getParameter("year");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");
                String categoryID = request.getParameter("category");
                ProductDAO dao = new ProductDAO();
                CategoryDAO cdao = new CategoryDAO();
                Category category = cdao.getCategoryById(categoryID);

                if (productName.isEmpty()) {
                    Product product = dao.getProductById(productId);
                    productName = product.getProductName();
                }
                if (description.isEmpty()) {
                    Product product = dao.getProductById(productId);
                    description = product.getDescription();
                }
                if (unit.isEmpty()) {
                    Product product = dao.getProductById(productId);
                    unit = product.getUnit();
                }
                if (Year.isEmpty()) {
                    Product product = dao.getProductById(productId);
                    Year = String.valueOf(product.getYear());
                }
                if (quantity.isEmpty()) {
                    Product product = dao.getProductById(productId);
                    quantity = String.valueOf(product.getQuantity());
                }
                if (price.isEmpty()) {
                    Product product = dao.getProductById(productId);
                    price = String.valueOf(product.getPrice());
                }
                if (image.isEmpty()) {
                    Product product = dao.getProductById(productId);
                    image = product.getImg();
                }

                Product product = new Product(productId, description, Float.parseFloat(price), productName, unit, Integer.parseInt(Year), Integer.parseInt(quantity), image, category);
                dao.update(product);
                int i = 1;
                if (request.getParameter("i")!=null) {
                    i = Integer.parseInt(request.getParameter("i"));
                };

                response.sendRedirect("AdminServlet?i=" + i);
            } else {
                request.setAttribute("message", "Login first.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("photo");
            fileName = (String) getFileName(filePart);
            int lengh = fileName.indexOf(".");
            for (int iIndex = lengh; iIndex >= 0; iIndex--) {
                if (fileName.charAt(iIndex) == 92) {
                    fileName = fileName.substring(iIndex + 1);
                    break;
                }
            }

            String applicationPath = request.getServletContext().getRealPath("");
            String basePath = applicationPath + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (Exception e) {
            fileName = "";
        }
        return fileName;
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

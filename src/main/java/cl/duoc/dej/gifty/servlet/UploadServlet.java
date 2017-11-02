package cl.duoc.dej.gifty.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author LC1300XXXX
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("hola");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DiskFileItemFactory df = new DiskFileItemFactory();
            ServletContext sc = getServletConfig().getServletContext();
            File repository = (File) sc.getAttribute("javax.servlet.context.tempdir");
            df.setRepository(repository);
            ServletFileUpload su = new ServletFileUpload(df);
            List<FileItem> fileItems = su.parseRequest(req);

            for (FileItem fi : fileItems) {
                if (fi.isFormField()) {

                } else {
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contextType = fi.getContentType();
                    long fileSize = fi.getSize();
                    boolean isInMemory = fi.isInMemory();
                    Logger.getLogger("UploadServlet").log(Level.INFO, "procesando la subida del archivo" + fileName);
                    fi.write(new File("c:\\" + fileName));
                }
            }
        } catch (Exception e) {
            Logger.getLogger("UploadServlet").log(Level.SEVERE, e.getMessage());
        }

    }
}

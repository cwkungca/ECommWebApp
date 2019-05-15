package com.ecomm.src.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecomm.src.dao.Category;
import com.ecomm.src.dao.CategoryImp;
import com.ecomm.src.dao.ImgEntity;
import com.ecomm.src.dao.Product;
import com.ecomm.src.dao.ProductImp;
import com.ecomm.src.service.CategoryFacade;
import com.ecomm.src.service.ProductFacade;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/ImgServlet")
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
        ServletContext context = getServletContext();
        String entityName = request.getParameter("part");
        Optional<String> optEntityNo = Optional.ofNullable(request.getParameter("num"));
        Optional<? extends ImgEntity> optEntity = Optional.empty();
        OutputStream out = response.getOutputStream();
        
        if(entityName.equals("cat") && optEntityNo.isPresent()) {
            optEntity = new CategoryFacade(new Category(), new CategoryImp()).find(optEntityNo.get());
            
        } else if (entityName.equals("pro")) {
            optEntity = new ProductFacade(new Product(), new ProductImp()).find(optEntityNo.get());
        }
        
        if(optEntity.isPresent()) {
            Optional<InputStream> optInStream = Optional.ofNullable(optEntity.get().getImg());
            
            if(optInStream.isPresent()) {
                response.setContentType(context.getMimeType(optEntity.get().getImgFormate()));
                
                byte[] buf = new byte[1024];
                int count = 0;
                while ((count = optInStream.get().read(buf)) >= 0) {
                    out.write(buf, 0, count);
                }
            }
        }
        out.close();
	}

}

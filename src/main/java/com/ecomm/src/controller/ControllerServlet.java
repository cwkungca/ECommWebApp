package com.ecomm.src.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userPath = req.getServletPath();
		
		//if category page is requested
		if(userPath.equals("/category")) {
			//TODO: Implement catory request
		
		//if cart page is requested
		} else if(userPath.equals("/viewCart")) {
			//TODO: Implement cart page request
			
			userPath = "/cart";
			
		//if checkout page is requested
		} else if(userPath.equals("/checkout")) {
			//TODO: Implement checkout page request
			
		//if user switch language
		}  else if(userPath.equals("/chooseLanguage")) {
			//TODO: Implement language request
			
		}
		
		//use RequestDispatcher to forward request insternally
		String url = "/WEB-INF/view" + userPath + ".jsp";
		
		try {
			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userPath = req.getServletPath();
		
		//if addToCart action is called
		if(userPath.equals("/addToCart")) {
			//TODO: Implement add product to cart action
		
		//if updateCart action is called
		} else if(userPath.equals("/updateCart")) {
			//TODO: Implement update cart action
			
		//if purchase action is called
		} else if(userPath.equals("/purchase")) {
			//TODO: Implement purchase action
			
			userPath = "/confirmation";
		}
		
		//use RequestDispatcher to forward request insternally
		String url = "/WEB-INF/view" + userPath + ".jsp";
		
		try {
			req.getRequestDispatcher(url).forward(req, res);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

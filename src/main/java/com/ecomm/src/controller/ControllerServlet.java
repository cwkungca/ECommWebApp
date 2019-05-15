package com.ecomm.src.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecomm.src.dao.Category;
import com.ecomm.src.dao.CategoryImp;
import com.ecomm.src.dao.Customer;
import com.ecomm.src.dao.CustomerImp;
import com.ecomm.src.dao.CustomerOrder;
import com.ecomm.src.dao.CustomerOrderImp;
import com.ecomm.src.dao.Delivery;
import com.ecomm.src.dao.Product;
import com.ecomm.src.dao.ProductImp;
import com.ecomm.src.model.CommerceAction;
import com.ecomm.src.model.CommerceActionImp;
import com.ecomm.src.model.ShoppingCart;
import com.ecomm.src.model.Validator;
import com.ecomm.src.service.CategoryFacade;
import com.ecomm.src.service.CustomerFacade;
import com.ecomm.src.service.OrderFacade;
import com.ecomm.src.service.ProductFacade;

/**
 * Servlet implementation class ControllerServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
	    super.init(servletConfig);
	    
	    // store category list in servlet context
	    getServletContext().setAttribute("categories", new CategoryFacade(new Category(), new CategoryImp()).findAll());
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPath = request.getServletPath();
		HttpSession session = request.getSession();
		Optional<Customer> optCustomer = Optional.ofNullable((Customer)session.getAttribute("user"));
		
		// if category page is requested
		if(userPath.equals("/category")) {
		    Optional<Category> optCategory = Optional.empty();
		    
		    // get Category ID from request
		    Optional<String> optCatId = Optional.ofNullable(request.getParameter("catid"));
		    
		    if(optCatId.isPresent()) {
		        optCategory = Optional.ofNullable(
		                new CategoryFacade(new Category(), new CategoryImp()).findAllByCat(optCatId.get()));
		        
		        if(optCategory.isPresent()) {
		            // place selected category in session scope
                    session.setAttribute("selectedCategory", optCategory.get());
		        }
		    }
		    
		// if cart page is requested
		} else if(userPath.equals("/viewCart")) {
		    Optional<String> optClear = Optional.ofNullable(request.getParameter("clear"));
		    
		    if(optClear.isPresent() && optClear.get().equals("true")) {
		        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		        cart.clear();
		    }
			userPath = "/cart";
			
		// if checkout page is requested
		} else if(userPath.equals("/checkout")) {
			
			if(optCustomer.isPresent()) {
				userPath = "/checkout";
		    } else {
		        userPath = "/login";
		    }
			
		// if user switch language
		} else if(userPath.equals("/chooseLanguage")) {
			//TODO: Implement language request
			
		} else if(userPath.equals("/login")) {
			System.out.println("------backurl referer 1 : " + request.getHeader("referer"));
		}
		
		// use RequestDispatcher to forward request internally
		String url = "/WEB-INF/view" + userPath + ".jsp";
		
		try {
		    request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    // ensures that user input is interpreted as 8-bit Unicode
	    request.setCharacterEncoding("UTF-8");
	    String userPath = request.getServletPath();
	    HttpSession session = request.getSession();
	    Optional<ShoppingCart> optCart = Optional.ofNullable((ShoppingCart) session.getAttribute("cart"));
	    Optional<Customer> optCustomer = Optional.ofNullable((Customer)session.getAttribute("user"));
	    Optional<String> optMessage = Optional.empty();
	    Validator validator = new Validator();
	    CommerceAction comAction = new CommerceActionImp();
		
		// if addToCart action is called
		if(userPath.equals("/addToCart")) {
		    Optional<Product> optProduct = Optional.empty();
		    
		    // if user is adding item to cart for first time create cart object and attach it to user session
		    if(!optCart.isPresent()) {
		        optCart = Optional.ofNullable(new ShoppingCart());
		        session.setAttribute("cart", optCart.get());
		    }
		    
		    // get user input from request
		    Optional<String> optProductId = Optional.ofNullable(request.getParameter("productId"));
            
            if(optProductId.isPresent()) {
                optProduct = new ProductFacade(new Product(), new ProductImp()).find(optProductId.get());
                
                if(optProduct.isPresent()) {
                    optCart.get().addItem(optProduct.get());
                }
            }
            userPath = "/category";
		    
		// if updateCart action is called
		} else if(userPath.equals("/updateCart")) {
		    // get input from request
            String productId = request.getParameter("productId");
            String quantity = request.getParameter("quantity");
            
            boolean isValidated = validator.validateQuantity(productId, quantity);
            
            if(isValidated) {
                Optional<Product> optProduct = new ProductFacade(new Product(), new ProductImp()).find(productId);
                
                if(optProduct.isPresent()) {
                    optCart.get().update(optProduct.get(), quantity);
                }
            }
            userPath = "/cart";
			
		// if purchase action is called
		} else if(userPath.equals("/purchase")) {
		    boolean isPurchased = false;
		    OrderFacade orderFacade = new OrderFacade(new CustomerOrder(), new CustomerOrderImp());
		    
		    if(optCustomer.isPresent()) {
		    	
		    	Delivery delivery = new Delivery(
		    				request.getParameter("name").trim(),
		    				request.getParameter("phone").trim(),
		    				request.getParameter("address").trim(),
		    				request.getParameter("cityRegion").trim(),
		    				request.getParameter("ccNumber").trim()
		    			);
		    	
		    	boolean isValidated = validator.validateForm(
		    				delivery.getName(),
		    				optCustomer.get().getEmail(),
		    				delivery.getPhone(),
		    				delivery.getAddress(),
		    				delivery.getCityRegion(),
		    				delivery.getCcNumber()
		    			);
		    	
		    	// check delivery information validated
		    	if(isValidated) {
		    		isPurchased = orderFacade.create(comAction.generateOrder(optCustomer.get(), optCart.get(), delivery)); 
			        
			        if(isPurchased) {
			            session.removeAttribute("cart");
			            userPath = "/confirmation";
			        } else {
			            optMessage = Optional.ofNullable("Something Going Wrong, Please try again, Thank you.");
			            session.setAttribute("message", optMessage.get());
			            userPath = "/cart";
			        }
			        
		    	} else {
		    		userPath = "/checkout";
		    	}
		        
		    } else {
		        userPath = "/login";
		    }
		
		// if login action is called
		} else if(userPath.equals("/login")) {
			System.out.println("------backurl referer : " + request.getHeader("referer"));

			session = request.getSession();
		    
		    // get Account and Password from request
            Optional<String> optAcc = Optional.ofNullable(request.getParameter("account"));
            Optional<String> optPwd = Optional.ofNullable(request.getParameter("password"));
            
            if(validator.validateInput(optAcc) && validator.validateInput(optPwd)) {
                optCustomer = new CustomerFacade(new Customer(), new CustomerImp())
                        .getLoginUser(optAcc.get(), optPwd.get());
                
                if(optCustomer.isPresent()) {
                    session.setAttribute("user", optCustomer.get());
                    
                } else {
                    optMessage = Optional.ofNullable("Input Wrong Account or Password!");
                }
            } else {
                optMessage = Optional.ofNullable("Input Wrong Account or Password!");
            }
            
            if(optMessage.isPresent()) {
                session.setAttribute("message", optMessage.get());
                userPath = "/login";
              
            } else {
                userPath = "/cart";
            }
        
        // if logout action is called
		} else if(userPath.equals("/logout")) {
			session.removeAttribute("user");
			session.removeAttribute("cart");
			session.removeAttribute("message");
			
			userPath = "/login";
		}
		
		// use RequestDispatcher to forward request internally
		String url = "/WEB-INF/view" + userPath + ".jsp";
		
		try {
		    request.getRequestDispatcher(url).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

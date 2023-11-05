package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Component
public class Rest_Control {
	
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public Inventory_Repo inventoryRepo;
	@Autowired
	public SearchJpa searchjpa;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/productdetails")
	public String manageProd() {
		return "productDet";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/inventory")
	public String inventory(Model model,@RequestParam(name = "key",required=false) String key) {
		List<Inventory_Entity> entity = (List<Inventory_Entity>) inventoryRepo.findAll();
		
		
		List<Inventory_Entity> prodEn = new ArrayList<>();
		
		if(key != null) {
			prodEn =  (List<Inventory_Entity>) searchjpa.findByNameIgnoreCase(key);
		}
		else {
			prodEn = (List<Inventory_Entity>) inventoryRepo.findAll();	
		}

		model.addAttribute("entities",entity);
		model.addAttribute("results", prodEn);
		
		return "inventory";
	}
	
	@PostMapping("/intro")
	public Object intro(@RequestParam String email, @RequestParam String password, RedirectAttributes att) {
	
		UserEntity entity = userRepository.validate(email, password);
		
		RedirectView redirect = new RedirectView();
		if(entity != null)
			return "intro";
		
		redirect.setUrl("/login");
		
		
			att.addFlashAttribute("err_msg", "Invalid Credentials...!");
	     return redirect;

	}
    
	@PostMapping("/process")
	public @ResponseBody RedirectView process(@RequestParam String name, @RequestParam String email,@RequestParam String password, RedirectAttributes redAtt) {
	
		UserEntity u = new 	UserEntity();
		
		u.setName(name);
		u.setEmail(email);
		u.setPassword(password);
	    userRepository.save(u);
	    
	   redAtt.addFlashAttribute("msg","User Saved...! Proceed to Login.");
	    
	    
	    RedirectView redirect = new RedirectView();
	    
	    redirect.setUrl("/login");
		
		return redirect;

	}
	
	@GetMapping("/track")
	public String track() {
		return "track";
	}
	
	
	@PostMapping("/added")
	public RedirectView getInvetory(@RequestParam String product_name, @RequestParam String units, @RequestParam double pricing, @RequestParam  String location, @RequestParam long order_number) {
		
		 Inventory_Entity inv = new Inventory_Entity();
		 
		 inv.setProduct_name(product_name);
		 inv.setUnits(units);
		 inv.setPricing(pricing);
		 inv.setLocation(location);
		 inv.setOrder_number(order_number);
		 inventoryRepo.save(inv);
		 
		 RedirectView redirect = new RedirectView();
		 redirect.setUrl("/productdetails");
		 
		 return redirect;
	}
	@GetMapping("/update")
	public String updateInv(Model model, @RequestParam long id) {
		Inventory_Entity invEn = inventoryRepo.findById(id).orElse(null);
		model.addAttribute("entity", invEn);
		return "update.html";
	}
	
	@PostMapping("/updateDet")
	public String updateData(@ModelAttribute Inventory_Entity updateEn) {
		
		Inventory_Entity existEnt = inventoryRepo.findById(updateEn.getProduct_name()).orElse(null);
		
		if(existEnt != null) {
			existEnt.setProduct_name(updateEn.getProduct_name());
			existEnt.setUnits(updateEn.getUnits());
			existEnt.setPricing(updateEn.getPricing());
			existEnt.setLocation(updateEn.getLocation());
			
			inventoryRepo.save(existEnt);
		}
		return "redirect:/inventory";    
		
	}
	
	
   @GetMapping("/data")
   public @ResponseBody Iterable<UserEntity> getUsers(){
	   
	   return userRepository.findAll();
   
   }
}

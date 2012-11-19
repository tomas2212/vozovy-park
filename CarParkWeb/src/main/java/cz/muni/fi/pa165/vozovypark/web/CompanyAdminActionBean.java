/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.vozovypark.web;

import cz.muni.fi.pa165.vozovypark.web.menu.Menu;
import cz.muni.fi.pa165.vozovypark.web.menu.MenuItem;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author andrej
 */
@UrlBinding("/company")
public class CompanyAdminActionBean implements ActionBean, LayoutPage{
    
    private ActionBeanContext context;
    @SpringBean(value="mainMenu")
    private Menu mainMenu;
    
    @SpringBean(value="companySubMenu")
    private Menu subMenu;

    @Override
    public void setContext(ActionBeanContext abc) {
        context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

   
    
    public Menu getMainMenu() {
        mainMenu.setActiveItemByUrl("/reservations");
        return mainMenu;
    }

    @Override
    public void setMainMenu(Menu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public Menu getSubMenu() {
        return subMenu;
    }

    @Override
    public void setSubMenu(Menu subMenu) {
        this.subMenu = subMenu;
    }
    
    
    
    @DefaultHandler
    public Resolution employees() {     
        this.subMenu.setActiveItemByName("companyAdmin.employees");
        return new ForwardResolution("/companyAdmin/employees.jsp");
    }
    
    public Resolution addEmployee(){
        this.subMenu.setActiveItemByName("companyAdmin.addEmployee");
        return new ForwardResolution("/companyAdmin/employees.jsp");
    }
    
}

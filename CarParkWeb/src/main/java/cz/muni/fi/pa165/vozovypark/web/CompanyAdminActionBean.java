package cz.muni.fi.pa165.vozovypark.web;

import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.service.CarService;
import cz.muni.fi.pa165.vozovypark.service.CompanyLevelService;
import cz.muni.fi.pa165.vozovypark.web.menu.Menu;
import java.util.List;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 *
 * @author tomas
 */
@UrlBinding("/company")
public class CompanyAdminActionBean implements ActionBean, LayoutPage {

    private ActionBeanContext context;
    @SpringBean(value = "mainMenu")
    private Menu mainMenu;
    @SpringBean(value = "companySubMenu")
    private Menu subMenu;
    @SpringBean(value = "CarService")
    private CarService carService;
    @SpringBean(value = "CompanyLevelService")
    private CompanyLevelService cls;

    @Override
    public void setContext(ActionBeanContext abc) {
        context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @Override
    public Menu getMainMenu() {
        mainMenu.setActiveItemByUrl("/company");
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

    public Resolution addEmployee() {
        this.subMenu.setActiveItemByName("companyAdmin.addEmployee");
        return new ForwardResolution("/companyAdmin/employees.jsp");
    }

    public Resolution companyLevels() {
        this.subMenu.setActiveItemByName("companyAdmin.companyLevels");
        return new ForwardResolution("/companyAdmin/companyLevels.jsp");
    }

    public Resolution addCompanyLevel() {
        this.subMenu.setActiveItemByName("companyAdmin.addCompanyLevel");
        return new ForwardResolution("/companyAdmin/addCompanyLevel.jsp");
    }

    public List<CompanyLevelDTO> getAllCompanyLevels() {
        return cls.getAllCompanyLevels();
    }
    
    private CompanyLevelDTO cld;

    public CompanyLevelDTO getCompanyLevel() {
        return cld;
    }

    public void setCompanyLevel(CompanyLevelDTO cld) {
        this.cld = cld;
    }

    public Resolution addCl() {
        cls.createCompanyLevel(cld.getName());
        return new RedirectResolution("/companyAdmin/companyLevels.jsp");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadClFromDatabase() {
        String ids = context.getRequest().getParameter("companyLevel.id");
        if (ids == null) {
            return;
        }
        cld = cls.getCompanyLevelById(Long.parseLong(ids));
    }

    public Resolution storno() {
        return new ForwardResolution("company/companyLevels.jsp");
    }
    
}
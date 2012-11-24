package cz.muni.fi.pa165.vozovypark.web;

import cz.muni.fi.pa165.vozovypark.DTO.CarDTO;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.service.CarService;
import cz.muni.fi.pa165.vozovypark.service.CompanyLevelService;
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
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author andrej
 */
@UrlBinding("/carPark")
public class CarParkAdminActionBean implements ActionBean, LayoutPage {

    private ActionBeanContext context;
    
    @SpringBean(value = "mainMenu")
    private Menu mainMenu;
    
    @SpringBean(value = "carParkSubMenu")
    private Menu subMenu;
    
    @SpringBean(value= "CarService")
    private CarService carService;
    
    @SpringBean(value= "CompanyLevelService")
    private CompanyLevelService cs;
    
    @Override
    public void setContext(ActionBeanContext abc) {
        context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    public Menu getMainMenu() {
        mainMenu.setActiveItemByUrl("/carPark");
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
    public Resolution release() {
        this.subMenu.setActiveItemByName("carPark.release");
        CarDTO car = new CarDTO();
        car.setBrand("sdf");
        CompanyLevelDTO createCompanyLevel = cs.createCompanyLevel("fofo");
        //carService.createCar(car);
        List<CarDTO> allCars = carService.getAllCars();
        return new ForwardResolution("/carAdmin/release.jsp");
    }

   
    
}

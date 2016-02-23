package cz.muni.fi.pa165.vozovypark.carparkrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.vozovypark.DTO.CompanyLevelDTO;
import cz.muni.fi.pa165.vozovypark.DTO.EmployeeDTO;
import cz.muni.fi.pa165.vozovypark.service.CompanyLevelService;
import cz.muni.fi.pa165.vozovypark.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomas on 20/2/2016.
 */
@WebServlet(urlPatterns = "/employees/*")
public class EmployeeRest extends HttpServlet{

    final static Logger log = LoggerFactory.getLogger(CarRest.class);

    CompanyLevelService companyLevelService;
    EmployeeService employeeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        Object obj = config.getServletContext().getAttribute("companyLevelService");
        companyLevelService = (CompanyLevelService) obj;
        obj = config.getServletContext().getAttribute("employeeService");
        employeeService = (EmployeeService) obj;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        String path = req.getPathInfo();
        if (path == null || path.equals("/")) {
            if (req.getParameter("companyLevel") != null) {
                long id = Long.valueOf(req.getParameter("companyLevel"));
                try {
                    mapper.writeValue(resp.getOutputStream(), employeeCollectionToMap(getEmployeesByCompanyLevel(id)));
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                    OperationStatus os = new OperationStatus();
                    os.setCausedBy(ex.getMessage());
                    os.setOperation("getEmployees");
                    os.setStatus("failed");
                    resp.setStatus(500);
                    mapper.writeValue(resp.getOutputStream(), os);
                }
            } else {
                try {
                    mapper.writeValue(resp.getOutputStream(), employeeCollectionToMap(getEmployees()));
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                    OperationStatus os = new OperationStatus();
                    os.setCausedBy(ex.getMessage());
                    os.setOperation("getEmployees");
                    os.setStatus("failed");
                    resp.setStatus(500);
                    mapper.writeValue(resp.getOutputStream(), os);
                }

            }
        } else {
            String pathArray[];
            pathArray = req.getPathInfo().split("/");
            if (pathArray[1] != null) {
                Long id = Long.parseLong(pathArray[1]);
                try {
                    EmployeeDTO dto = employeeService.getEmployeeById(id);
                    if (dto != null) {
                        mapper.writeValue(resp.getOutputStream(), dto);
                    } else {
                        resp.setStatus(404);
                    }
                } catch (Exception ex) {
                    log.debug(ex.getMessage(), ex);
                    OperationStatus os = new OperationStatus();
                    os.setOperation("getEmployees");
                    os.setStatus("failed");
                    os.setCausedBy(ex.getMessage());
                    resp.setStatus(500);
                    mapper.writeValue(resp.getOutputStream(), os);
                }
            }
        }


    }


    private Map<Long, EmployeeDTO> employeeCollectionToMap(Collection<EmployeeDTO> employees) {
        Map<Long, EmployeeDTO> map = new HashMap<Long, EmployeeDTO>();
        for (EmployeeDTO dto : employees) {
            map.put(dto.getId(), dto);
        }
        return map;
    }

    private Collection<EmployeeDTO> getEmployees() {
        List<EmployeeDTO> result = employeeService.getAllEmployees();
        return result;
    }

    private Collection<EmployeeDTO> getEmployeesByCompanyLevel(long id) {
        CompanyLevelDTO clDTO = companyLevelService.getCompanyLevelById(id);
        if (clDTO != null) {
            List<EmployeeDTO> employees = employeeService.getEmployeesByCompanyLevel(clDTO);
            return employees;
        } else {
            throw new IllegalArgumentException("Company level with given id does not exist");
        }
    }
}

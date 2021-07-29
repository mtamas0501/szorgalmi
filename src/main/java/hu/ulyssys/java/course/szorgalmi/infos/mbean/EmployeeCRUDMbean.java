package hu.ulyssys.java.course.szorgalmi.infos.mbean;

import hu.ulyssys.java.course.szorgalmi.infos.EmployeeService;
import hu.ulyssys.java.course.szorgalmi.infos.entity.Employee;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class EmployeeCRUDMbean implements Serializable {
    private List<Employee> list;
    private Employee selectedEmployee;

    private boolean inFunction;

    public boolean isInFunction() {
        return inFunction;
    }

    private EmployeeService employeeService;

    @Inject
    public EmployeeCRUDMbean(EmployeeService employeeService) {
        this.employeeService = employeeService;
        list = employeeService.getAll();
        selectedEmployee = new Employee();
    }

    public void initSave() {
        selectedEmployee = new Employee();
        inFunction = true;
    }

    public void initEdit(Employee employee) {
        selectedEmployee = employee;
        inFunction = true;
    }

    public void save() {
        if (selectedEmployee.getId() == null) {
            selectedEmployee.setId(System.currentTimeMillis());
            employeeService.add(selectedEmployee);
            list = employeeService.getAll();
            selectedEmployee = new Employee();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres hozzáadás"));
        } else {
            employeeService.update(selectedEmployee);
            list = employeeService.getAll();
            selectedEmployee = new Employee();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres módsítás"));
        }
        inFunction = false;
    }

    public void remove(Employee employee) {
        employeeService.remove(employee);
        list = employeeService.getAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres törlés"));
    }

    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
}

package hu.ulyssys.java.course.szorgalmi.infos.Impl;

import hu.ulyssys.java.course.szorgalmi.infos.AdressService;
import hu.ulyssys.java.course.szorgalmi.infos.EmployeeService;
import hu.ulyssys.java.course.szorgalmi.infos.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {

    public EmployeeServiceImpl() {

        for (int i = 0; i < 100; i++) {
            Employee employee = new Employee();
            employee.setId(Long.parseLong(i+""));
            employee.setFirstName("Example firstName");
            employee.setLastName("Example lastName");
            employee.setPosition("Example Position");
            add(employee);
        }
    }

    private List<Employee> list = new ArrayList<>();

    @Override
    public List<Employee> getAll() {
        return list;
    }

    @Override
    public void add(Employee employee) {
        list.add(employee);
    }

    @Override
    public void remove(Employee employee) {
        list.remove(employee);
    }

    @Override
    public void update(Employee employee) {
        for (Employee i : getAll()) {
            if (i.getId().equals(employee.getId())) {
                i.setFirstName(employee.getFirstName());
                i.setLastName(employee.getLastName());
                i.setPosition(employee.getPosition());
                break;
            }
        }
    }
}

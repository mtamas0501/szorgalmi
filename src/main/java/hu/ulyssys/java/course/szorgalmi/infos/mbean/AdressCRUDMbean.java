package hu.ulyssys.java.course.szorgalmi.infos.mbean;

import hu.ulyssys.java.course.szorgalmi.infos.AdressService;
import hu.ulyssys.java.course.szorgalmi.infos.entity.Adress;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AdressCRUDMbean implements Serializable {
    private List<Adress> list;
    private Adress selectedAdress;

    private boolean inFunction;

    public boolean isInFunction() {
        return inFunction;
    }

    private AdressService adressService;

    @Inject
    public AdressCRUDMbean(AdressService adressService) {
        this.adressService = adressService;
        list = adressService.getAll();
        selectedAdress = new Adress();
    }

    public void initSave() {
        selectedAdress = new Adress();
        inFunction = true;
    }

    public void initEdit(Adress adress) {
        selectedAdress = adress;
        inFunction = true;
    }

    public void save() {
        if (selectedAdress.getId() == null) {
            selectedAdress.setId(System.currentTimeMillis());
            adressService.add(selectedAdress);
            list = adressService.getAll();
            selectedAdress = new Adress();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres hozzáadás"));
        } else {
            adressService.update(selectedAdress);
            list = adressService.getAll();
            selectedAdress = new Adress();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres módsítás"));
        }
        inFunction = false;
    }

    public void remove(Adress adress) {
        adressService.remove(adress);
        list = adressService.getAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sikeres törlés"));
    }

    public List<Adress> getList() {
        return list;
    }

    public void setList(List<Adress> list) {
        this.list = list;
    }

    public Adress getSelectedAdress() {
        return selectedAdress;
    }

    public void setSelectedAdress(Adress selectedAdress) {
        this.selectedAdress = selectedAdress;
    }
}

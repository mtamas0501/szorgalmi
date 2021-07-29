package hu.ulyssys.java.course.szorgalmi.infos.Impl;

import hu.ulyssys.java.course.szorgalmi.infos.AdressService;
import hu.ulyssys.java.course.szorgalmi.infos.entity.Adress;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AdressServiceImpl implements AdressService{

    public AdressServiceImpl() {

        for (int i = 0; i < 100; i++) {
            Adress adress = new Adress();
            adress.setId(Long.parseLong(i+""));
            adress.setCountry("Example country");
            adress.setSettlement("Example settlement");
            adress.setPublicSpace("Example PublicStatement");
            adress.setAddressNumber(123);
            add(adress);
        }
    }

    private List<Adress> list = new ArrayList<>();

    @Override
    public List<Adress> getAll() {
        return list;
    }

    @Override
    public void add(Adress adress) {
        list.add(adress);
    }

    @Override
    public void remove(Adress adress) {
        list.remove(adress);
    }

    @Override
    public void update(Adress adress) {
        for (Adress i : getAll()) {
            if (i.getId().equals(adress.getId())) {
                i.setCountry(adress.getCountry());
                i.setSettlement(adress.getSettlement());
                i.setPublicSpace(adress.getPublicSpace());
                i.setAddressNumber(adress.getAddressNumber());
                break;
            }
        }
    }
}
